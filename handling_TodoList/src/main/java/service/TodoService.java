package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import dao.TodoDAO;
import dao.TodoDAOImp;
import vo.TodoVO;
import vo.TodoCompletedVO;
import vo.TodoRecurringVO;
import vo.TodoMissedVO;

public class TodoService {
    private TodoDAO todoDao;
    private SimpleDateFormat dateFormat;

    public TodoService() {
        todoDao = new TodoDAOImp();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    // 기본 CRUD 서비스
    public List<TodoVO> getSelectAll(String memberId) throws Exception {
        return todoDao.selectAllByMemberId(memberId);
    }

    public int setInsertTodo(TodoVO todoVo) throws Exception {
        int result = todoDao.insertTodo(todoVo);
        
        // 반복 일정인 경우 처리
        if ("Y".equals(todoVo.getIsRecurring()) && todoVo.getRepeatInterval() > 0) {
            // 날짜 파싱
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(todoVo.getStartDate());
            Date endDate = sdf.parse(todoVo.getEndDate());
            
            Calendar startCal = Calendar.getInstance();
            startCal.setTime(startDate);
            
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(endDate);
            
            // 시작 날짜부터 종료 날짜까지 반복
            while (!startCal.after(endCal)) {
                // 첫 번째 날은 이미 입력되었으므로 건너뜀
                if (!startCal.getTime().equals(startDate)) {
                    TodoVO recurringTodo = new TodoVO(
                        todoVo.getMemberId(), 
                        todoVo.getTitle(), 
                        todoVo.getDetail(), 
                        sdf.format(startCal.getTime()), 
                        sdf.format(endCal.getTime()), 
                        "Y", 
                        todoVo.getRepeatInterval()
                    );
                    
                    todoDao.insertTodo(recurringTodo);
                }
                
                // 반복 간격만큼 날짜 증가
                startCal.add(Calendar.DAY_OF_MONTH, todoVo.getRepeatInterval());
            }
            
            Map<String, Object> recurringParams = new HashMap<>();
            recurringParams.put("todoId", todoVo.getTodoId());
            recurringParams.put("memberId", todoVo.getMemberId());
            recurringParams.put("repeatType", "DAILY"); // 또는 사용자가 선택한 타입
            recurringParams.put("repeatInterval", todoVo.getRepeatInterval());
            recurringParams.put("repeatStart", todoVo.getStartDate());
            recurringParams.put("repeatEnd", todoVo.getEndDate());
            
            todoDao.insertTodoRecurring(recurringParams);
        }
        
        return result;
    }

    public int setUpdate(TodoVO todoVo) throws Exception {
        return todoDao.updateTodo(todoVo);
    }

    public int delete(int todoId, String memberId) throws Exception {
        return todoDao.deleteTodo(todoId, memberId);
    }

    // 일정 조회 서비스
    public List<TodoVO> getSelectTodayAll(String memberId) throws Exception {
        return todoDao.selectTodoToday(memberId);
    }

    public List<TodoVO> getSelectWeekAll(String memberId) throws Exception {
        return todoDao.selectTodoWeek(memberId);
    }

    public List<TodoVO> getSelectMonthAll(String memberId) throws Exception {
        return todoDao.selectTodoMonth(memberId);
    }

    public List<TodoVO> getSelectChoiceDate(String memberId, String startDate, String endDate) throws Exception {
        return todoDao.selectChoiceDate(memberId, startDate, endDate);
    }

    // 완료 관련 서비스
    public List<TodoCompletedVO> getSelectCompleteAll(String memberId) throws Exception {
        return todoDao.selectAllByCompleteTodo(memberId);
    }

    public int setUpdateTodoComplete(int todoId, String memberId) throws Exception {
        return todoDao.completeTodo(todoId, memberId);
    }

    public int revertTodoCompleted(int todoId, String memberId) throws Exception {
        return todoDao.revertTodo(todoId, memberId);
    }

    public int deleteCompleted(int todoId, String memberId) throws Exception {
        return todoDao.deleteCompletedTodo(todoId, memberId);
    }

    // 반복 일정 관련 서비스
    public List<TodoRecurringVO> getSelectRecurring(String memberId) throws Exception {
        return todoDao.selectRecurring(memberId);
    }

    public List<TodoRecurringVO> getRecurringTodos(int todoId) throws Exception {
        return todoDao.selectRecurringTodos(todoId);
    }

    public int insertRecurringTodo(int todoId, String memberId, String repeatType, 
                                 String repeatStart, String repeatEnd, int repeatInterval) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("todoId", todoId);
        params.put("memberId", memberId);
        params.put("repeatType", repeatType);
        params.put("repeatStart", repeatStart);
        params.put("repeatEnd", repeatEnd);
        params.put("repeatInterval", repeatInterval);
        
        return todoDao.insertTodoRecurring(params);
    }

    // 미완료 일정 관련 서비스
    public List<TodoMissedVO> getMissedTodos(String memberId) throws Exception {
        return todoDao.selectMissedTodos(memberId);
    }

    public void checkMissedTodos() throws Exception {
        todoDao.checkMissedTodos();
    }

    // 일정 자동 미완료 처리 서비스
    public void processDailyMissedTodos(String memberId) throws Exception {
        Calendar now = Calendar.getInstance();
        if (isJustAfterMidnight(now)) {
            int missedCount = todoDao.countTodayMissedTodos(memberId);
            if (missedCount > 0) {
                todoDao.insertMissedTodo(memberId);
            }
        }
    }

    public Map<String, Object> getAllMonthlyTodos(String memberId, String startDate, String endDate) throws Exception {
        Map<String, Object> result = new HashMap<>();
        
        // 파라미터 설정
        Map<String, Object> params = new HashMap<>();
        params.put("memberId", memberId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        
        // 1. 일반 할일 목록 조회 (미완료)
        List<TodoVO> regularTodos = todoDao.selectMonthlyTodos(params);  // selectTodoMonth에서 변경
        
        // 2. 완료된 할일 목록 조회
        List<TodoCompletedVO> completedTodos = todoDao.selectAllByCompleteTodo(memberId);
        
        // 3. 미완료 할일 목록 조회
        List<TodoMissedVO> missedTodos = todoDao.selectMissedTodos(memberId);
        
        // 결과 맵에 저장
        result.put("regularTodos", regularTodos);
        result.put("completedTodos", completedTodos);
        result.put("missedTodos", missedTodos);
        
        return result;
    }

    // 유틸리티 메서드
    private boolean isJustAfterMidnight(Calendar cal) {
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return hour == 0 && minute < 5; // 자정 후 5분 이내
    }

    // 날짜 관련 유틸리티 메서드
    public String getTodayDate() {
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    public String getDateAfterDays(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        return dateFormat.format(cal.getTime());
    }
    

public void checkMissedTodosByMember(String memberId) throws Exception {
    todoDao.insertMissedTodoByMember(memberId);
}
    
}
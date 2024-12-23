// TodoDAO.java
package dao;

import java.util.List;
import java.util.Map;

import vo.TodoVO;
import vo.TodoCompletedVO;
import vo.TodoRecurringVO;
import vo.TodoMissedVO;

public interface TodoDAO {
    // 기존 메서드들
    List<TodoVO> selectAllByMemberId(String memberId) throws Exception;
    List<TodoVO> selectTodoToday(String memberId) throws Exception;
    List<TodoVO> selectTodoWeek(String memberId) throws Exception;
    List<TodoVO> selectTodoMonth(String memberId) throws Exception;
    List<TodoVO> selectChoiceDate(String memberId, String startDate, String endDate) throws Exception;
    List<TodoCompletedVO> selectAllByCompleteTodo(String memberId) throws Exception;
    
    // 수정된 메서드들
    int insertTodo(TodoVO todo) throws Exception;
    int updateTodo(TodoVO todo) throws Exception;
    int deleteTodo(int todoId, String memberId) throws Exception;
    int deleteCompletedTodo(int todoId, String memberId) throws Exception;
    int completeTodo(int todoId, String memberId) throws Exception;
    int revertTodo(int todoId, String memberId) throws Exception;
    
    // 새로운 메서드들
    List<TodoRecurringVO> selectRecurring(String memberId) throws Exception;
    List<TodoRecurringVO> selectRecurringTodos(int todoId) throws Exception;
    List<TodoMissedVO> selectMissedTodos(String memberId) throws Exception;
    int insertTodoRecurring(Map<String, Object> params) throws Exception;
    int insertMissedTodo(String memberId) throws Exception;
    int countTodayMissedTodos(String memberId) throws Exception;
    void checkMissedTodos() throws Exception;
	void insertMissedTodoByMember(String memberId) throws Exception;
	List<TodoVO> selectMonthlyTodos(Map<String, Object> params) throws Exception;

}

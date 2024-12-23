package dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import mybatis.MyConfig;
import vo.TodoVO;
import vo.TodoCompletedVO;
import vo.TodoRecurringVO;
import vo.TodoMissedVO;

public class TodoDAOImp implements TodoDAO {
    
    private SqlSession sqlSession;
    
    public TodoDAOImp() {
        sqlSession = new MyConfig().getInstance();
    }
    
    @Override
    public List<TodoVO> selectAllByMemberId(String memberId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectAllByMemberId", memberId);
    }
    
    @Override
    public List<TodoVO> selectTodoToday(String memberId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectTodoToday", memberId);
    }
    
    @Override
    public List<TodoVO> selectTodoWeek(String memberId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectTodoWeek", memberId);
    }
    
    @Override
    public List<TodoVO> selectTodoMonth(String memberId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectTodoMonth", memberId);
    }
    
    @Override
    public List<TodoVO> selectChoiceDate(String memberId, String startDate, String endDate) throws Exception {
        Map<String, Object> params = Map.of(
            "memberId", memberId,
            "startDate", startDate,
            "endDate", endDate
        );
        return sqlSession.selectList("dao.TodoDAO.selectChoiceDate", params);
    }
    
    @Override
    public int insertTodo(TodoVO todo) throws Exception {
        return sqlSession.insert("dao.TodoDAO.insertTodo", todo);
    }
    
    @Override
    public int updateTodo(TodoVO todo) throws Exception {
        return sqlSession.update("dao.TodoDAO.updateTodo", todo);
    }
    
    @Override
    public int deleteTodo(int todoId, String memberId) throws Exception {
        Map<String, Object> params = Map.of(
            "todoId", todoId,
            "memberId", memberId
        );
        return sqlSession.delete("dao.TodoDAO.deleteTodo", params);
    }
    
    @Override
    public List<TodoCompletedVO> selectAllByCompleteTodo(String memberId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectAllByCompleteTodo", memberId);
    }
    
    @Override
    public int completeTodo(int todoId, String memberId) throws Exception {
        Map<String, Object> params = Map.of(
            "todoId", todoId,
            "memberId", memberId
        );
        return sqlSession.insert("dao.TodoDAO.insertTodoCompleted", params);  // insertCompleted -> insertTodoCompleted
    }
    
    @Override
    public int revertTodo(int todoId, String memberId) throws Exception {
        Map<String, Object> params = Map.of(
            "todoId", todoId,
            "memberId", memberId
        );
        sqlSession.insert("dao.TodoDAO.insertFromCompleted", params);
        return sqlSession.delete("dao.TodoDAO.deleteCompletedTodo", params);
    }
    
    @Override
    public int deleteCompletedTodo(int todoId, String memberId) throws Exception {
        Map<String, Object> params = Map.of(
            "todoId", todoId,
            "memberId", memberId
        );
        return sqlSession.delete("dao.TodoDAO.deleteCompletedTodo", params);
    }
    
    // 새로 추가된 메서드들
    @Override
    public List<TodoRecurringVO> selectRecurring(String memberId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectRecurring", memberId);
    }
    
    @Override
    public List<TodoRecurringVO> selectRecurringTodos(int todoId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectRecurringTodos", todoId);
    }
    
    @Override
    public List<TodoMissedVO> selectMissedTodos(String memberId) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectMissedTodos", memberId);
    }
    
    @Override
    public int insertTodoRecurring(Map<String, Object> params) throws Exception {
        return sqlSession.insert("dao.TodoDAO.insertTodoRecurring", params);
    }
    
    @Override
    public int insertMissedTodo(String memberId) throws Exception {
        return sqlSession.insert("dao.TodoDAO.insertMissedTodo", memberId);
    }
    
    @Override
    public int countTodayMissedTodos(String memberId) throws Exception {
        return sqlSession.selectOne("dao.TodoDAO.countTodayMissedTodos", memberId);
    }
    
    @Override
    public void checkMissedTodos() throws Exception {
        sqlSession.insert("dao.TodoDAO.insertMissedTodo");
    }
    
    @Override
    public void insertMissedTodoByMember(String memberId) throws Exception {
        sqlSession.insert("dao.TodoDAO.insertMissedTodoByMember", memberId);
    }

    @Override
    public List<TodoVO> selectMonthlyTodos(Map<String, Object> params) throws Exception {
        return sqlSession.selectList("dao.TodoDAO.selectMonthlyTodos", params);
    }
    
    
    
}
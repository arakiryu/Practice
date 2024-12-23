package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import mybatis.MyConfig;
import vo.BoardVO;

public class BoardDAOImp implements BoardDAO {
    private SqlSession sqlSession;
    
    public BoardDAOImp() {
        sqlSession = new MyConfig().getInstance();
    }
    
    @Override
    public List<BoardVO> selectAll() throws Exception {
        return sqlSession.selectList("dao.BoardDAO.selectAll");
    }
    
    @Override
    public BoardVO selectByBoardId(int boardId) throws Exception {
        return sqlSession.selectOne("dao.BoardDAO.selectByBoardId", boardId);
    }
    
    @Override
    public int insertBoard(BoardVO board) throws Exception {
        int result = sqlSession.insert("dao.BoardDAO.insertBoard", board);
        sqlSession.commit();//
    	return result;
        		
    }
    
    @Override
    public int updateBoard(BoardVO board) throws Exception {
        return sqlSession.update("dao.BoardDAO.updateBoard", board);
    }
    
    @Override
    public int deleteBoard(int boardId) throws Exception {
        return sqlSession.delete("dao.BoardDAO.deleteBoard", boardId);
    }
    
    @Override
    public void incrementViews(int boardId) throws Exception {
        sqlSession.update("dao.BoardDAO.incrementViews", boardId);
    }
    
    @Override
    public List<BoardVO> selectByMemberId(String memberId) throws Exception {
        return sqlSession.selectList("dao.BoardDAO.selectByMemberId", memberId);
    }
    
    @Override
    public List<BoardVO> selectViewPage(int startPage , int endPage)throws Exception{
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("startNo", startPage);   
        map.put("endNo", endPage);       
    	return sqlSession.selectList("dao.BoardDAO.selectviewPage" , map);
    }
    
    
    
    
}
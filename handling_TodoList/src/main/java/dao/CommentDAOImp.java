package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.MyConfig;
import vo.CommentVO;

public class CommentDAOImp implements CommentDAO {

	private SqlSession sqlSession;

	public CommentDAOImp() {
		sqlSession = new MyConfig().getInstance();
	}

	@Override
	public List<CommentVO> selectBoardCmt(String board_Id) throws Exception {

		try {
			return sqlSession.selectList("selectCmtBoardNo", board_Id);
		} finally {
			sqlSession.clearCache(); // 캐시 클리어로 최신 데이터 보장
		}
	}

	@Override
	public int insertParentCmt(CommentVO vo) throws Exception {
		try {
			int result = sqlSession.insert("insertBoardParentCmt", vo);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		}
	}

	@Override
	public int insertChildCmt(CommentVO vo) throws Exception {
		try {
			int result = sqlSession.insert("insertBoardChildCmt", vo);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		}
	}

	@Override
	public int UpdateCmt(CommentVO vo) throws Exception {
		try {
			int result = sqlSession.update("updateCmt", vo);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		}
	}

	@Override
	public int DeleteCmt(int comment_no) throws Exception {
		try {
			int result = sqlSession.delete("deleteCmt", comment_no);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		}
	}

	@Override
	public int deleteAllByBoardId(String board_id) throws Exception {
		try {
			int result = sqlSession.delete("deleteAllByBoardId", board_id);
			sqlSession.commit();
			return result;
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		}
	}

}

package dao;

import java.util.List;

import vo.CommentVO;

public interface CommentDAO {

	public List<CommentVO> selectBoardCmt(String board_Id) throws Exception;

	public int insertParentCmt(CommentVO vo) throws Exception;

	public int insertChildCmt(CommentVO vo) throws Exception;

	public int UpdateCmt(CommentVO vo) throws Exception;

	public int DeleteCmt(int comment_no) throws Exception;
	
	int deleteAllByBoardId(String board_id) throws Exception;

}

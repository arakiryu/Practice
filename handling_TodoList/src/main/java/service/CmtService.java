package service;

import java.util.List;
import dao.CommentDAO;
import dao.CommentDAOImp;
import vo.CommentVO;

public class CmtService {
    
    private CommentDAO commentDAO;
    
    public CmtService() {
        commentDAO = new CommentDAOImp();
    }
    
    // 게시글의 댓글 목록 조회
    public List<CommentVO> getBoardComments(String board_Id) throws Exception {
        return commentDAO.selectBoardCmt(board_Id);
    }
    
    // 부모 댓글 작성
    public boolean addParentComment(CommentVO commentVO) throws Exception {
        return commentDAO.insertParentCmt(commentVO) > 0;
    }
    
    // 대댓글 작성
    public boolean addChildComment(CommentVO commentVO) throws Exception {
        return commentDAO.insertChildCmt(commentVO) > 0;
    }
    
    // 댓글 수정
    public boolean updateComment(CommentVO commentVO) throws Exception {
        return commentDAO.UpdateCmt(commentVO) > 0;
    }
    
    // 댓글 삭제
    public boolean deleteComment(int comment_no) throws Exception {
        return commentDAO.DeleteCmt(comment_no) > 0;
    }
    
    // 게시글 관련 모든 댓글 삭제
    public boolean deleteAllCommentsByBoardId(String board_id) throws Exception {
        return commentDAO.deleteAllByBoardId(board_id) >= 0;
    }
    
}
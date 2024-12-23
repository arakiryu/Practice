package dao;

import java.util.List;
import vo.BoardVO;

public interface BoardDAO {
    // 게시글 목록 조회 (전체 조회)
    List<BoardVO> selectAll() throws Exception;
    
    // 게시글 상세 조회
    BoardVO selectByBoardId(int boardId) throws Exception;
    
    // 게시글 등록
    int insertBoard(BoardVO board) throws Exception;
    
    // 게시글 수정
    int updateBoard(BoardVO board) throws Exception;
    
    // 게시글 삭제
    int deleteBoard(int boardId) throws Exception;
    
    // 조회수 증가
    void incrementViews(int boardId) throws Exception;
    
    // 특정 회원의 게시글 목록 조회
    List<BoardVO> selectByMemberId(String memberId) throws Exception;
    
    //페이징 
	List<BoardVO> selectViewPage(int startPage, int endPage) throws Exception;
}
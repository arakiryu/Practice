package service;

import java.util.List;

import dao.BoardDAO;
import dao.BoardDAOImp;
import vo.BoardVO;

public class BoardService {

	private BoardDAO board;

	public BoardService() {
		board = new BoardDAOImp();
	}

	public List<BoardVO> selectAll() throws Exception {
		List<BoardVO> boardList = board.selectAll();
		return boardList;
	}

	public BoardVO selectByBoardId(int no) throws Exception {
		BoardVO vo = board.selectByBoardId(no);
		return vo;

	}

	public int insertBoard(BoardVO vo) throws Exception {
		return board.insertBoard(vo);
	}

	public int updateBoard(BoardVO vo) throws Exception {
		return board.updateBoard(vo);
	}

	public int deleteBoard(int boardId) throws Exception {
		return board.deleteBoard(boardId);
	}

	
	public List<BoardVO> selectByMemberId(String memberId) throws Exception {
		List<BoardVO> boardList = board.selectByMemberId(memberId);
		return boardList;
	}
	
	public List<BoardVO> slectByPage(int startPage , int endPage) throws Exception{
		List<BoardVO> boardList = board.selectViewPage(startPage, endPage);
		return boardList;
	}

}

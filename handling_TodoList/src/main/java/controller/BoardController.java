package controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.BoardService;
import service.CmtService;
import vo.BoardVO;
import vo.CommentVO;
import vo.MemberVO;
import vo.TodoVO;

public class BoardController implements Controller {

	// boardDAO는 그냥 지할일을 mybatis에게 떠넘긴것 뿐이다

	private BoardService service;
	private CmtService cmtService;

	public BoardController() {
		service = new BoardService();
		cmtService = new CmtService();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod(); // get요청인지 post요청인지 확인 하는 부분 HTTP 메서드를 확인하는 용도 | GET: 데이터 조회 (게시글 목록 보기, 상세
												// 보기) ,POST: 데이터 생성/수정 (글쓰기, 수정하기)|
		String action = request.getParameter("action"); // URL 예시들: /board/board.do?action=list -> 게시글 목록
														// /board/board.do?action=view -> 게시글 상세보기

		// 로그인 체크
		HttpSession session = request.getSession(); // 세션객체에 로그인 되어있는 정보가 있는지 확인하는 로직이지 뭐
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember"); // 로그인 되어있다면 그정보를 담는거지 loginMember 뭐에 쓸지
																				// 알 순 없지만?

		// 목록 조회
		if ("list".equals(action) || action == null) {// 요청이 들어올때 list만 들어오나 보다
			List<BoardVO> boardList = service.selectAll();
			request.setAttribute("boardList", boardList);
			return "/board/board.jsp";
		}
		
		
		// 처리 페이징
		if ("pageing".equals(action) || action == null) {// 요청이 들어올때 list만 들어오나 보다
			// 현재 페이지 번호를 가져옴
		    String pageStr = request.getParameter("page");
			int currentPage = (pageStr != null) ? Integer.parseInt(pageStr) : 1;
			
			
			int pageSize = 20;//20개씩 표현할거라 
			
			int startNo = (currentPage-1) * pageSize + 1; 
			int endNo = currentPage * pageSize;
			
			
			List<BoardVO> boardList = service.selectAll();
			
			int totalPost = boardList.size();
			int totalPages = (int)Math.ceil((double)totalPost/pageSize); 
			
			List<BoardVO> pageBoardList = service.slectByPage(startNo, endNo);
			request.setAttribute("pageBoardList", pageBoardList);
		    request.setAttribute("currentPage", currentPage);
		    request.setAttribute("totalPages", totalPages);
			
			return "/board/boardMainList.jsp";
		}

		// 상세 조회 (일단 이거 수정할거
		if ("view".equals(action)) {
			int boardId = Integer.parseInt(request.getParameter("boardId"));
			String cmtBoardId = Integer.toString(boardId);
			List<CommentVO> cmtList = cmtService.getBoardComments(cmtBoardId);
			BoardVO board = service.selectByBoardId(boardId);
			
			request.setAttribute("cmtList", cmtList);
			request.setAttribute("board", board);
			return "/board/boardView.jsp";
		}

		// 글쓰기 폼
		if ("writeForm".equals(action)) {
			if (loginMember == null) {
				request.setAttribute("msg", "로그인이 필요합니다.");
				return "/login/loginForm.jsp";
			}
			request.setAttribute("loginMember", loginMember);
			return "/board/boardWrite.jsp";
		}

		// 글쓰기 처리
		if ("write".equals(action) && "POST".equals(method)) {
			if (loginMember == null) {
				request.setAttribute("msg", "로그인이 필요합니다.");
				return "/login/loginForm.jsp";
			}

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVO board = new BoardVO();
			board.setTitle(title);
			board.setContent(content);
			board.setMemberId(loginMember.getId());

			service.insertBoard(board);

			// 글 등록 후 목록을 다시 조회해서 보여주기
			List<BoardVO> boardList = service.selectAll();
			request.setAttribute("boardList", boardList);
			return "/board/board.jsp";
		}
		// 수정 폼
		if ("editForm".equals(action)) {
			if (loginMember == null) {
				request.setAttribute("msg", "로그인이 필요합니다.");
				return "login/login.jsp";
			}

			int boardId = Integer.parseInt(request.getParameter("boardId"));

			BoardVO vo = service.selectByBoardId(boardId);

			// 작성자 체크
			// 현재 vo객체의 멤버아이디가 로그인된 아이디가 아니라면
			// 즉 현재 선택된 보드 번호의 아이디 란 뜻
			if (!vo.getMemberId().equals(loginMember.getId())) {
				request.setAttribute("msg", "수정 권한이 없습니다.");
				return "/board/boardView.jsp?boardId=" + boardId;
			}

			request.setAttribute("boardVO", vo);
			return "/board/editForm.jsp";

		}

		// 수정
		if ("edit".equals(action)) {

			int boardId = Integer.parseInt(request.getParameter("boardId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardVO board = new BoardVO();
			board.setBoardId(boardId); // 이 부분이 누락
			board.setTitle(title);
			board.setContent(content);

			service.updateBoard(board);
			return "/board/board.do?action=view&boardId=" + boardId;// 성공했을때 페이지를 하나 만들어놓자 중복되는게 많을거같다 (하나로 만들어놓을?까)

		}

		// 삭제
		if ("delete".equals(action)) {
		    if (loginMember == null) {
		        request.setAttribute("msg", "로그인이 필요합니다");
		        return "/login/loginForm.jsp";
		    }

		    int boardId = Integer.parseInt(request.getParameter("boardId"));
		    String strBoardId = String.valueOf(boardId);
		    
		    BoardVO vo = service.selectByBoardId(boardId);

		    if (!vo.getMemberId().equals(loginMember.getId())) {
		        request.setAttribute("msg", "삭제 권한이 없습니다.");
		        return "/board/boardView.jsp?boardId=" + boardId;
		    }

		    try {
		        // 댓글 먼저 삭제
		        cmtService.deleteAllCommentsByBoardId(strBoardId);
		        // 게시글 삭제
		        service.deleteBoard(boardId);
		        
		        return "/board/board.do?action=list";
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("msg", "게시글 삭제 중 오류가 발생했습니다.");
		        return "/board/boardView.jsp?boardId=" + boardId;
		    }
		    
		}


		return null;	
	}
}


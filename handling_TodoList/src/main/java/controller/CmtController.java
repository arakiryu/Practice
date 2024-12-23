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

public class CmtController implements Controller {
	private CmtService cmtService;

	public CmtController() {

		cmtService = new CmtService();

	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String method = request.getMethod();
		String action = request.getParameter("action");
		String boardId = request.getParameter("boardId");

		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

		try {
			if ("write".equals(action)) {
				String comment = request.getParameter("content");
				String member_id = loginMember.getId();

				CommentVO cvo = new CommentVO();
				cvo.setBoard_id(boardId);
				cvo.setContent(comment);
				cvo.setMember_id(member_id);

				boolean result = cmtService.addParentComment(cvo);
				if (!result) {
					request.setAttribute("msg", "댓글 작성에 실패했습니다.");
				}
			} else if ("reply".equals(action)) {
				String content = request.getParameter("content");
				String member_id = loginMember.getId();
				int parent = Integer.parseInt(request.getParameter("parent"));

				CommentVO cvo = new CommentVO();
				cvo.setBoard_id(boardId);
				cvo.setContent(content);
				cvo.setMember_id(member_id);
				cvo.setParent(parent);

				boolean result = cmtService.addChildComment(cvo);
				if (!result) {
					request.setAttribute("msg", "답글 작성에 실패했습니다.");
				}
			} else if ("update".equals(action)) {
				String content = request.getParameter("content");
				int cmtNo = Integer.parseInt(request.getParameter("cmtNo"));

				CommentVO cvo = new CommentVO();
				cvo.setCmtNo(cmtNo);
				cvo.setContent(content);
				cvo.setMember_id(loginMember.getId());

				boolean result = cmtService.updateComment(cvo);
				if (!result) {
					request.setAttribute("msg", "댓글 수정에 실패했습니다.");
				}
			} else if ("delete".equals(action)) {
				int cmtNo = Integer.parseInt(request.getParameter("cmtNo"));
				boolean result = cmtService.deleteComment(cmtNo);
				if (!result) {
					request.setAttribute("msg", "댓글 삭제에 실패했습니다.");
				}
			}

			// 댓글 목록 새로 조회
			List<CommentVO> updatedComments = cmtService.getBoardComments(boardId);
			request.setAttribute("cmtList", updatedComments);

			return "/board/board.do?action=view&boardId=" + boardId;

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "댓글 처리 중 오류가 발생했습니다.");
			return "/board/board.do?action=view&boardId=" + boardId;
		}
	}
}
package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.MemberService;
import vo.MemberVO;

public class LoginController implements Controller {

	private MemberService service;

	public LoginController() {
		service = new MemberService();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();

		if ("GET".equals(method)) {
			return "/login/loginForm.jsp";
		}

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		MemberVO vo = new MemberVO();

		vo.setId(id);
		vo.setPassword(password);
 
		MemberVO loginMember = service.loginMember(vo);

		if (loginMember != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginMember", loginMember);
			return "/index.jsp"; // 로그인 성공 시 리다이렉트할 경로
		} else {
			// 로그인 실패
			request.setAttribute("msg", "아이디 또는 비밀번호가 잘못되었습니다");
			return "/login/loginForm.jsp";
		}
	}

}

package controller;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MemberService;
import vo.MemberVO;

public class SignUpController implements Controller {

	private MemberService service;

	public SignUpController() {
		service = new MemberService();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		
		if ("GET".equalsIgnoreCase(method)) {
            return "/signUp/signUpForm.jsp";
        }

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		MemberVO vo = new MemberVO(id, password, name, email);

		if (service.MemberSignUp(vo) != 0) {
			return "/index.jsp";
		} else {
			request.setAttribute("msg", "이미 회원가입된 정보입니다");
			return "/login/loginForm.jsp";
		}
	}

}

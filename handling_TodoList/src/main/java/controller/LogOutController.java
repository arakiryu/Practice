package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOutController implements Controller {
	
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		
		return "/index.jsp";
	}

}

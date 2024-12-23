package framework;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import controller.Controller;

public class DispatcherServlet extends HttpServlet {
    
    private HandlerMapping mappings;

    @Override
    public void init(ServletConfig config) throws ServletException {
        String propName = config.getInitParameter("propName");
        mappings = new HandlerMapping(propName);
    }
    
    @Override
    public void service(HttpServletRequest request, 
            HttpServletResponse response)
                    throws ServletException, IOException {
        
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        uri = uri.substring(contextPath.length());
        System.out.println("요청 uri : " + uri);
        
        try {
            Controller control = mappings.getController(uri);
            String callPage = control.handleRequest(request, response);
            
            // JSON 응답 처리를 위한 수정
            if (callPage == null) {
                // 이미 응답이 완료된 경우 (JSON 등)
                return;
            }
            
            // 빈 문자열이 아닌 경우에만 포워딩
            if (!callPage.trim().isEmpty()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher(callPage);
                dispatcher.forward(request, response);
            }
            
        } catch(Exception e) {
            e.printStackTrace();
            
            // 에러 응답 처리
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/plain");
            response.getWriter().write("Internal Server Error: " + e.getMessage());
        }
    }
}
package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.TodoService;
import vo.MemberVO;
import vo.TodoCompletedVO;
import vo.TodoMissedVO;
import vo.TodoVO;

public class TodoController implements Controller {

	private TodoService service;

	public TodoController() {
		service = new TodoService();
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String method = request.getMethod();
		String action = request.getParameter("action");
		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		
		

		HttpSession session = request.getSession(); // 세션객체에 로그인 되어있는 정보가 있는지 확인하는 로직이지 뭐
		MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

		if (loginMember == null) {
			request.setAttribute("msg", "로그인이 필요한서비스 입니다");
			return "/member/loginForm.jsp";
		}

		if ("todoList".equals(action)) {
			List<TodoVO> todoList = service.getSelectAll(loginMember.getId());
			request.setAttribute("todoList", todoList);
			return "/todo/todoList.jsp";
		}

		if ("todoListMain".equals(action)) {
			List<TodoVO> todoList = service.getSelectAll(loginMember.getId());
			List<TodoVO> todayList = service.getSelectTodayAll(loginMember.getId());
			request.setAttribute("todoList", todoList);
			request.setAttribute("todayList", todayList);
			return "/todo/todoListMain.jsp";
		}

		if ("todoEditForm".equals(action)) {
			int no = Integer.parseInt(request.getParameter("todoNo"));
			request.setAttribute("no", no);
			return "todo/todoForm.jsp";
		}

		if ("todoInsertForm".equals(action)) {
		    String memberId = loginMember.getId();
		    String title = request.getParameter("title");
		    String detail = request.getParameter("detail");
		    String startDate = request.getParameter("startDate");
		    String endDate = request.getParameter("endDate");
		    String isRecurring = request.getParameter("isRecurring");
		    
		    // repeatInterval 파라미터 처리 개선
		    int repeatInterval = 1; // 기본값 설정
		    String repeatIntervalStr = request.getParameter("repeatInterval");
		    if (repeatIntervalStr != null && !repeatIntervalStr.trim().isEmpty()) {
		        try {
		            repeatInterval = Integer.parseInt(repeatIntervalStr);
		        } catch (NumberFormatException e) {
		            System.out.println("repeatInterval 파싱 실패: " + repeatIntervalStr);
		        }
		    }
		    
		    // 디버깅용 로그
		    System.out.println("받은 파라미터:");
		    System.out.println("title: " + title);
		    System.out.println("detail: " + detail);
		    System.out.println("startDate: " + startDate);
		    System.out.println("endDate: " + endDate);
		    System.out.println("isRecurring: " + isRecurring);
		    System.out.println("repeatInterval: " + repeatInterval);
		    
		    TodoVO vo = new TodoVO(memberId, title, detail, startDate, endDate, isRecurring, repeatInterval);
		    service.setInsertTodo(vo);
		    
		    return "/todo/todoListMain.jsp";
		}
		//현재 todo 수정
		if ("todoEdit".equals(action)) {
			int todoId = Integer.parseInt(request.getParameter("todoId"));
			String title = request.getParameter("title");
			String detail = request.getParameter("detail");
			String startDate = request.getParameter("startDate");
			String end_Day = request.getParameter("endDate");
			String isRecurring = request.getParameter("isRecurring");
			String memberId = loginMember.getId();
			TodoVO vo = new TodoVO(todoId,title,detail,startDate,end_Day,isRecurring ,memberId);
			try {
				System.out.println(todoId);
				System.out.println(title);
				service.setUpdate(vo);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("오류발생");
			}
			
			return "/todo/todoListMain.jsp";
		}
		
		//삭제
		if ("todoDelete".equals(action)) {
		    int todoId = Integer.parseInt(request.getParameter("todoId"));
		    boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted"));
		    String memberId = loginMember.getId();
		    
		    System.out.println(todoId);
		    System.out.println(isCompleted);
		    if (isCompleted) {
		        service.deleteCompleted(todoId, memberId);
		    } else {
		        service.delete(todoId, memberId);
		    }
		    
		    if (isAjax) {
		    	request.setAttribute("todoList", service.getSelectAll(memberId));
		        return "/todo/todoItems.jsp"; 
		    } else {
		        return "todo/todoListMain.jsp";
		    }
		}
		
		if ("todayList".equals(action)) {
			
			String memberId = loginMember.getId();
		    // 자정이 지난 미완료 일정 체크
		    service.checkMissedTodos();
			
			
			List<TodoVO> todayList = service.getSelectTodayAll(memberId);
			
			if(isAjax) {
				request.setAttribute("todayList", todayList);
				return "/todo/todoItems.jsp";
			}else {
				request.setAttribute("todayList", todayList);
				return "/todo/todoListMain.jsp";
			}
		}
		
		 // 1. 계획된 일정 조회
	    if ("plannedList".equals(action)) {
	        String memberId = loginMember.getId();
	        List<TodoVO> plannedList = service.getSelectAll(memberId);
	        request.setAttribute("plannedList", plannedList);
	        
	        if (isAjax) {
	            return "/todo/plannedItems.jsp";
	        }
	        return "/todo/todoListMain.jsp";
	    }

	    // 2. 완료된 일정 조회
	    if ("completedList".equals(action)) {
	        String memberId = loginMember.getId();
	        List<TodoCompletedVO> completedList = service.getSelectCompleteAll(memberId);
	        request.setAttribute("completedList", completedList);
	        
	        if (isAjax) {
	            return "/todo/completedItems.jsp";
	        }
	        return "/todo/todoListMain.jsp";
	    }

	    // 3. 할일 완료 처리
	   
	    if ("completeTodo".equals(action)) {
	        int todoId = Integer.parseInt(request.getParameter("todoId"));
	        String memberId = loginMember.getId();
	        
	        // 완료 처리 실행
	        int result = service.setUpdateTodoComplete(todoId, memberId);
	        
	        if (isAjax) {
	            // 완료된 후 새로운 목록을 가져와서 반환
	            List<TodoVO> todoList = service.getSelectAll(memberId);
	            request.setAttribute("todoList", todoList);
	            return "/todo/todoItems.jsp";
	        }
	        return "redirect:/todo/todoListMain.jsp";
	    }
	    
	    // 4. 완료된 할일 되돌리기
	    if ("revertTodo".equals(action)) {
	    	
	        int todoId = Integer.parseInt(request.getParameter("todoId"));
	        String memberId = loginMember.getId();
	        int result = service.revertTodoCompleted(todoId, memberId);
	        
	        System.out.println("Action: " + action);
	    	System.out.println("TodoId: " + todoId);

	    	if (isAjax) {
	        	request.setAttribute("todoList", service.getSelectAll(memberId));

	        	
	        	return "/todo/todoItems.jsp";
	        }
	        return "/todo/todoListMain.jsp";
	    }
	    
	    
	    if ("selectDate".equals(action)) {
	        String memberId = loginMember.getId();
	        String startDate = request.getParameter("startDate");
	        String endDate = request.getParameter("endDate");
	        
	        System.out.println("startDate: " + startDate);  // 디버깅용
	        System.out.println("endDate: " + endDate);      // 디버깅용
	        
	        List<TodoVO> plannedList = service.getSelectChoiceDate(memberId, startDate, endDate);
	        request.setAttribute("plannedList", plannedList);
	        
	        if (isAjax) {
	            return "/todo/selectPlanedDate.jsp";
	        }
	        return "/todo/todoListMain.jsp";
	    }
	    
	    if ("calendarMonth".equals(action)) {
	        try {
	            String memberId = loginMember.getId();
	            String startDate = request.getParameter("startDate");
	            String endDate = request.getParameter("endDate");

	            System.out.println("[DEBUG] Calendar Month Request");
	            System.out.println("MemberId: " + memberId);
	            System.out.println("StartDate: " + startDate);
	            System.out.println("EndDate: " + endDate);

	            // 각 타입의 일정 조회
	            List<TodoVO> regularTodos = service.getSelectChoiceDate(memberId, startDate, endDate);
	            List<TodoCompletedVO> completedTodos = service.getSelectCompleteAll(memberId);
	            List<TodoMissedVO> missedTodos = service.getMissedTodos(memberId);

	            System.out.println("Regular Todos Count: " + regularTodos.size());
	            System.out.println("Completed Todos Count: " + completedTodos.size());
	            System.out.println("Missed Todos Count: " + missedTodos.size());

	            // FullCalendar가 이해할 수 있는 이벤트 형식으로 변환
	            List<Map<String, Object>> events = new ArrayList<>();

	            // 1. 일반 할일 목록 처리 (선택된 날짜 범위 내)
	            for (TodoVO todo : regularTodos) {
	                Map<String, Object> event = new HashMap<>();
	                event.put("title", todo.getTitle());
	                event.put("start", todo.getStartDate().split(" ")[0]);
	                event.put("allDay", true);
	                event.put("className", "uncompleted" + ("Y".equals(todo.getIsRecurring()) ? " recurring" : ""));
	                events.add(event);

	                System.out.println("[Regular Todo] " +
	                    "Title: " + todo.getTitle() +
	                    ", Start: " + todo.getStartDate() +
	                    ", Recurring: " + todo.getIsRecurring());
	            }

	            // 2. 완료된 할일 목록 처리 (전체)
	            for (TodoCompletedVO todo : completedTodos) {
	                Map<String, Object> event = new HashMap<>();
	                event.put("title", todo.getTitle());
	                event.put("start", todo.getStartDate().split(" ")[0]);
	                event.put("allDay", true);
	                event.put("className", "completed");
	                events.add(event);

	                System.out.println("[Completed Todo] " +
	                    "Title: " + todo.getTitle() +
	                    ", Start: " + todo.getStartDate() +
	                    ", Completed At: " + todo.getCompletedAt());
	            }

	            // 3. 미완료 할일 목록 처리 (전체)
	            for (TodoMissedVO todo : missedTodos) {
	                Map<String, Object> event = new HashMap<>();
	                event.put("title", "미완료: " + todo.getTodoId());
	                event.put("start", todo.getOriginalDate().toString().split(" ")[0]);
	                event.put("allDay", true);
	                event.put("className", "missed");
	                events.add(event);

	                System.out.println("[Missed Todo] " +
	                    "TodoId: " + todo.getTodoId() +
	                    ", Original Date: " + todo.getOriginalDate() +
	                    ", Missed Date: " + todo.getMissedDate());
	            }

	            System.out.println("Total Events Count: " + events.size());

	            // JSON 응답 설정
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().write(new Gson().toJson(events));
	            
	            return null;  // JSON 응답 후 null 반환
	        } catch (Exception e) {
	            // 예외 처리 추가
	            System.err.println("[ERROR] Calendar Month Processing Error:");
	            e.printStackTrace();
	            
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            
	            Map<String, String> errorResponse = new HashMap<>();
	            errorResponse.put("error", "Internal Server Error");
	            errorResponse.put("message", e.getMessage());
	            
	            response.getWriter().write(new Gson().toJson(errorResponse));
	            
	            return null;
	        }
	    }
	    return null;
	}
	
}

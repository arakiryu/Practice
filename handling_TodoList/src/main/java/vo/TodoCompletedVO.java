package vo;

import java.util.Date;

public class TodoCompletedVO {
    private int todoId;
    private String memberId;
    private String title;
    private String detail;
    private String startDate;
    private String endDate;
    private String completedAt;
    private int repeatInterval;
	
    public TodoCompletedVO(int todoId, String memberId, String title, String detail, String startDate, String endDate,
			String completedAt, int repeatInterval) {
		super();
		this.todoId = todoId;
		this.memberId = memberId;
		this.title = title;
		this.detail = detail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.completedAt = completedAt;
		this.repeatInterval = repeatInterval;
	}

	public TodoCompletedVO() {
		super();
	}

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(String completedAt) {
		this.completedAt = completedAt;
	}

	public int getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

    
    
}

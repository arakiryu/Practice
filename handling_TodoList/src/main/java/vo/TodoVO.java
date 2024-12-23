package vo;

public class TodoVO {
	private int todoId;
	private String memberId;
	private String title;
	private String detail;
	private String startDate;
	private String endDate;
	private String isRecurring;
	private int repeatInterval;
	private String createdAt;
	private String completeAt;
	private String updatedAt;

	public TodoVO(String memberId, String title, String detail, String startDate, String endDate, String isRecurring) {

		this.memberId = memberId;
		this.title = title;
		this.detail = detail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isRecurring = isRecurring;
	}

	public TodoVO() {
		super();
	}

	public TodoVO(String memberId, String title, String detail, String startDate, String endDate, String isRecurring,
			int repeatInterval) {
		super();
		this.memberId = memberId;
		this.title = title;
		this.detail = detail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isRecurring = isRecurring;
		this.repeatInterval = repeatInterval;
	}

	public TodoVO(int todoId, String memberId, String title, String detail, String startDate, String endDate,
			String isRecurring) {
		super();
		this.todoId = todoId;
		this.memberId = memberId;
		this.title = title;
		this.detail = detail;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isRecurring = isRecurring;
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

	public String getIsRecurring() {
		return isRecurring;
	}

	public void setIsRecurring(String isRecurring) {
		this.isRecurring = isRecurring;
	}

	public int getRepeatInterval() {
		return repeatInterval;
	}

	public void setRepeatInterval(int repeatInterval) {
		this.repeatInterval = repeatInterval;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCompleteAt() {
		return completeAt;
	}

	public void setCompleteAt(String completeAt) {
		this.completeAt = completeAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

}
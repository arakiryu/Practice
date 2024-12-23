package vo;

public class TodoMissedVO {
    private int missedId;
    private int todoId;
    private String memberId;
    private String title;        // 추가: 일정 제목
    private String detail;       // 추가: 일정 상세
    private String originalDate; // 변경: Date -> String
    private String missedDate;   // 변경: Date -> String
    private String reason;       // 추가: 미완료 사유
    private String createdAt;    // 변경: Date -> String

    // 기본 생성자
    public TodoMissedVO() {
        super();
    }

    // 전체 필드 생성자
    public TodoMissedVO(int missedId, int todoId, String memberId, String title, String detail,
                       String originalDate, String missedDate, String reason, String createdAt) {
        this.missedId = missedId;
        this.todoId = todoId;
        this.memberId = memberId;
        this.title = title;
        this.detail = detail;
        this.originalDate = originalDate;
        this.missedDate = missedDate;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getMissedId() { return missedId; }
    public void setMissedId(int missedId) { this.missedId = missedId; }
    
    public int getTodoId() { return todoId; }
    public void setTodoId(int todoId) { this.todoId = todoId; }
    
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    
    public String getOriginalDate() { return originalDate; }
    public void setOriginalDate(String originalDate) { this.originalDate = originalDate; }
    
    public String getMissedDate() { return missedDate; }
    public void setMissedDate(String missedDate) { this.missedDate = missedDate; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
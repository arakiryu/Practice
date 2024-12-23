package vo;

public class TodoRecurringVO {
    private int recurringId;
    private int todoId;
    private String memberId;     // 추가: 회원 ID
    private String repeatType;
    private int repeatInterval;
    private String repeatStartDate;  // 변경: Date -> String
    private String repeatEndDate;    // 변경: Date -> String
    private String createdAt;    // 변경: Date -> String

    // 기본 생성자
    public TodoRecurringVO() {
        super();
    }

    // 전체 필드 생성자
    public TodoRecurringVO(int recurringId, int todoId, String memberId, String repeatType,
                          int repeatInterval, String repeatStart, String repeatEnd, String createdAt) {
        this.recurringId = recurringId;
        this.todoId = todoId;
        this.memberId = memberId;
        this.repeatType = repeatType;
        this.repeatInterval = repeatInterval;
        this.repeatStartDate = repeatStart;
        this.repeatEndDate = repeatEnd;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getRecurringId() { return recurringId; }
    public void setRecurringId(int recurringId) { this.recurringId = recurringId; }
    
    public int getTodoId() { return todoId; }
    public void setTodoId(int todoId) { this.todoId = todoId; }
    
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    
    public String getRepeatType() { return repeatType; }
    public void setRepeatType(String repeatType) { this.repeatType = repeatType; }
    
    public int getRepeatInterval() { return repeatInterval; }
    public void setRepeatInterval(int repeatInterval) { this.repeatInterval = repeatInterval; }
    
    public String getRepeatStart() { return repeatStartDate; }
    public void setRepeatStart(String repeatStart) { this.repeatStartDate = repeatStart; }
    
    public String getRepeatEnd() { return repeatEndDate; }
    public void setRepeatEnd(String repeatEnd) { this.repeatEndDate = repeatEnd; }
    
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
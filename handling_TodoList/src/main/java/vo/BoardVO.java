package vo;



public class BoardVO {
    private int boardId;
    private String title;
    private String content;
    private String memberId;
    private String date;
    private int views;

    public BoardVO() {
		super();
	}
    
    
    

	public BoardVO(String title, String content) {
		
		this.title = title;
		this.content = content;
	}




	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
    
    
    
   
    
    
}
package vo;

public class CommentVO {
	
	private int cmtNo;
	private String board_id;
	private String member_id;
	private int parent;
	private int depth;
	private String content;
	private String created_At;
	
	public CommentVO() {
		super();
	}
	
	
	

	public CommentVO(int cmtNo, String board_id, String member_id) {
		super();
		this.cmtNo = cmtNo;
		this.board_id = board_id;
		this.member_id = member_id;
	}




	public int getCmtNo() {
		return cmtNo;
	}

	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated_At() {
		return created_At;
	}

	public void setCreated_At(String created_At) {
		this.created_At = created_At;
	}
	
	
	

}

package vo;

public class MemberVO {

	private String name;
	private String id;
	private String password;
	private String email;
	private String regDate;

	public MemberVO() {

	}
	
	
	



	public MemberVO (String id, String password, String name, String email) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.email = email;
	}






	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}

package dao;

import vo.MemberVO;

public interface MemberDAO {
	
	public MemberVO memberLogin(MemberVO member);
	
	public int memberSignUp(MemberVO member);
}

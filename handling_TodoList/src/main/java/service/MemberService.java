package service;

import dao.MemberDAO;
import dao.MemberDAOImp;
import dao.TodoDAO;
import vo.MemberVO;

public class MemberService {
	private MemberDAO member;

	public MemberService() {
		member = new MemberDAOImp();
	}

	public MemberVO loginMember(MemberVO memberVO) {
		MemberVO vo = member.memberLogin(memberVO);
		return vo;
	}
	
	public int MemberSignUp(MemberVO memberVO) {
		return member.memberSignUp(memberVO);
	}
	

}

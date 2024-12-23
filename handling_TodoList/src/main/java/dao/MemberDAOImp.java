package dao;

import org.apache.ibatis.session.SqlSession;

import mybatis.MyConfig;
import vo.MemberVO;

public class MemberDAOImp implements MemberDAO{
	
	private SqlSession sqlSession;
	
	
	public MemberDAOImp() {
		sqlSession = new MyConfig().getInstance();
	}

	
	@Override
	public MemberVO memberLogin(MemberVO member) {
		return sqlSession.selectOne("dao.MemberDAO.currentMember", member);
	}

	@Override
	public int memberSignUp(MemberVO member) {
		
		
		return sqlSession.insert("dao.MemberDAO.InsertMember", member);
		
	}
	
	
	
	
	
	

}

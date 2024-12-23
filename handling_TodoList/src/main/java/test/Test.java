package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.TodoDAOImp;
import mybatis.MyConfig;
import vo.TodoVO;


class Test {

	@org.junit.jupiter.api.Disabled
	void test() throws Exception {
		SqlSession session = new MyConfig().getInstance();
		
		List<TodoVO> list = new TodoDAOImp().selectAll();
		assertNotEquals(list.size(), 0);
		for(TodoVO vo : list) {
			System.out.println(vo.getNo() + vo.getTitle());
		}
	}

}

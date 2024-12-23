package mybatis;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyConfig {
    private static SqlSessionFactory sqlSessionFactory;
    
    static {
        String resource = "mybatis/mybatis-config.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static SqlSession getInstance() {
        return sqlSessionFactory.openSession(true); // true: auto-commit
    }
}
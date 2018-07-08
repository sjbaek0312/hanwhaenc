package examples.mybatis.dao;

import examples.mybatis.model.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
    @Autowired
    SqlSession sqlSession;

    public Member getMember(long id){
        return sqlSession.selectOne("findById", id);
    }

    public void insert(Member member){
        sqlSession.insert("insert", member);
    }
}

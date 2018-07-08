package examples.mybatis;

import examples.mybatis.config.MyBatisExamConfig;
import examples.mybatis.dao.MemberDao;
import examples.mybatis.mapper.MemberMapper;
import examples.mybatis.model.Member;
import examples.mybatis.util.Mapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= MyBatisExamConfig.class)
@Transactional
public class MybatisTest {
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private MemberDao memberDao;

    @Test
    public void test() {
        Assert.assertNotNull(memberMapper);
    }

    @Test
    public void testCRUD() {
        Member member = new Member();
        member.setEmail("arawn.kr@gmail.com");
        member.setName("Arawn Park");

        memberMapper.insert(member);
        System.out.println(member);
        Member findOne = memberMapper.findById(member.getId());
        System.out.println(findOne);
    }

    @Test
    public void testCRUD2() {
        Member member = new Member();
        member.setEmail("arawn.kr@gmail.com");
        member.setName("Arawn Park");

        memberDao.insert(member);
        System.out.println(member);
        Member findOne = memberDao.getMember(member.getId());
        System.out.println(findOne);
    }
}

package examples.jpashop.repository;

import examples.jpashop.config.ApplicationConfig;
import examples.jpashop.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= ApplicationConfig.class)
@Transactional
public class OrderRepositoryTest {


    @Autowired
    MemberRepository memberRepository;

    @Test
    public void configTest(){

    }

    @Test
    public void 회원가입() throws Exception {

        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        memberRepository.save(member);

        //Then
        assertEquals(member, memberRepository.findOne(member.getId()));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {

        //Given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //When
        memberRepository.save(member1);
        memberRepository.save(member2); //예외가 발생해야 한다.

        //Then
        fail("예외가 발생해야 한다.");
    }


}
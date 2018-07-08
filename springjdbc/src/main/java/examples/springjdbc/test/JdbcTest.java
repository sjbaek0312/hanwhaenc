package examples.springjdbc.test;

import java.util.List;

import examples.springjdbc.config.ApplicationConfig;
import examples.springjdbc.dao.RoleDao;
import examples.springjdbc.dto.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class JdbcTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao = ac.getBean(RoleDao.class);

        Role role = new Role();
        role.setRoleId(500);
        role.setDescription("CEO");

        int count = roleDao.update(role);

        System.out.println(count + "건 수정하였습니다..");

        Role role2 = roleDao.selectById(500);

        System.out.println(role2);

        int count2 = roleDao.deleteById(500);

        System.out.println(count2 + "건 삭제하였습니다.");

        Role role3 = roleDao.selectById(500);

        System.out.println(role3);
    }

}

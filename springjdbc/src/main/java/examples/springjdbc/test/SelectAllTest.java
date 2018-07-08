package examples.springjdbc.test;

import java.util.List;

import examples.springjdbc.config.ApplicationConfig;
import examples.springjdbc.dao.RoleDao;
import examples.springjdbc.dto.Role;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SelectAllTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        RoleDao roleDao =ac.getBean(RoleDao.class);

        List<Role> list = roleDao.selectAll();

        for(Role role: list) {
            System.out.println(role);
        }
    }

}

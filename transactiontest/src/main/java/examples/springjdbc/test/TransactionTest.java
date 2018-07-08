package examples.springjdbc.test;

import java.util.List;

import examples.springjdbc.config.ApplicationConfig;
import examples.springjdbc.dao.InfoDao;
import examples.springjdbc.dto.Info;
import examples.springjdbc.service.InfoService;
import examples.springjdbc.service.LogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionTest {

    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        InfoService infoService = ac.getBean(InfoService.class);

        Info info = new Info();
        info.setData("info data");
        infoService.addInfo(info);
    }

}

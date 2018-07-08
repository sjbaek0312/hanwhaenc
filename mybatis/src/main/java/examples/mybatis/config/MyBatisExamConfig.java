package examples.mybatis.config;

import examples.mybatis.util.Mapper;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.server.web.WebServlet;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan("examples.mybatis")
@MapperScan(basePackages = "examples.mybatis", annotationClass = Mapper.class)
@PropertySource(value={"classpath:database.properties"})
public class MyBatisExamConfig{
  private String driverClassName;
  @Value("${database.url}")
  private String dbUrl;
  @Value("${database.id}")
  private String dbUserId;
  @Value("${database.passwd}")
  private String dbUserPasswd;
  @Value("${database.mapperLocations}")
  private String mapperLocations;

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

//  @Bean
//  public DataSource dataSource(){
//    BasicDataSource dataSource = new BasicDataSource();
//    dataSource.setDriverClassName(driverClassName);
//    dataSource.setUrl(dbUrl);
//    dataSource.setUsername(dbUserId);
//    dataSource.setPassword(dbUserPasswd);
//
//    return dataSource;
//  }

  @Bean
  public DataSource dataSource() {

    // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db = builder
            .setType(EmbeddedDatabaseType.H2) //.H2 or .DERBY or .HSQL
            .addScript("db/sql/create-db.sql")
//            .addScript("db/sql/insert-data.sql")
            .build();
    return db;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws IOException {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);

    // 마이바티스 설정파일 위치 설정
    sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/database/configuration.xml"));

    // spring.examples.model 패키지 이하의 model 클래스 이름을 짧은 별칭으로 등록
    sqlSessionFactoryBean.setTypeAliasesPackage("examples.mybatis.model");

    // META-INF/mybatis/mappers 패키지 이하의 모든 XML을 매퍼로 등록
    sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/database/mappers/**/*.xml"));

    return sqlSessionFactoryBean;
  }

  /**
   * 마이바티스 {@link org.apache.ibatis.session.SqlSession} 빈을 등록한다.
   *
   * SqlSessionTemplate은 SqlSession을 구현하고 코드에서 SqlSession를 대체하는 역할을 한다.
   * 쓰레드에 안전하게 작성되었기 때문에 여러 DAO나 매퍼에서 공유 할 수 있다.
   */
  @Bean
  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }
}


/*
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.first"/>
    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="com.mysql.jdbc.Driver" />
        <property name="url" value="jdbc:mysql://127.0.0.1:3306/db_tmonplus" />
        <property name="username" value="root" />
        <property name="password" value="" />
    </bean>
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="dataSource" />
        <property name="mapperLocations" value="classpath*:database/**" />
    </bean>
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg ref="sqlSessionFactory" />
    </bean>
</beans>
 */
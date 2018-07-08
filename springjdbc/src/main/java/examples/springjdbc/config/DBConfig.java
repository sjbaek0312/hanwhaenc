package examples.springjdbc.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@EnableTransactionManagement
@PropertySource(value={"classpath:database.properties"})
public class DBConfig {
    private String driverClassName;
    @Value("${database.url}")
    private String dbUrl;
    @Value("${database.id}")
    private String dbUserId;
    @Value("${database.passwd}")
    private String dbUserPasswd;
    @Value("${database.mapperLocations}")
    private String mapperLocations;


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
    public PlatformTransactionManager transactionManger(DataSource ds) {
        return new DataSourceTransactionManager(ds);
    }
}

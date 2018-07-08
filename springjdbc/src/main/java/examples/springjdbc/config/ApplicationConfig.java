package examples.springjdbc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "examples.springjdbc" })
@Import({ DBConfig.class })
public class ApplicationConfig {
}

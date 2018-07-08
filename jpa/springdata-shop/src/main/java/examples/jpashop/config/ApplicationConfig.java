package examples.jpashop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "examples.jpashop" })
@Import({ DBConfig.class })
public class ApplicationConfig {
}

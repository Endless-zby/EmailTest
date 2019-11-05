package club.zby.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class RunApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(RunApplication.class, args);
    }


}




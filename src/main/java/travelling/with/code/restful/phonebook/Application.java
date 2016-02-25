package travelling.with.code.restful.phonebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = PhoneBookController.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket phoneBookApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("phonebook-api")
                .apiInfo(apiInfo())
                .select()
                    .paths(PathSelectors.regex("/phonebook/contacts.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RESTful Phone Book")
                .description("This is a demo phone book project, which includes names and phones of characters on the Simpsons TV show. Phones may not be valid, so don't rush to give your favourite characters a call!")
                .contact("alex (travelling.with.code@gmail.com)")
                .version("1.0")
                .build();
    }

}

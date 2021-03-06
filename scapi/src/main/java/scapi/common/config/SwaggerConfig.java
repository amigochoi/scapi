package scapi.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import scapi.model.dto.UserDTO;
import scapi.model.request.ListParam;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = "scapi.controller.api")
/*
 * @ComponentScan(basePackageClasses = { MemberController.class })
 */
public class SwaggerConfig {

	@Bean
	public Docket customDocket() {

		return new Docket(DocumentationType.SWAGGER_2)
				.forCodeGeneration(true)
				.useDefaultResponseMessages(false)
				.ignoredParameterTypes(ListParam.class).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				.build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API SPEC")
				.description("A SAMPLE WITH RESTFUL CRUD API").contact("Eddie LI").version("1.0")
				.build();
	}

}

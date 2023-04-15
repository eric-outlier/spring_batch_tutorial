package cmm.config.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@Profile({"!prod"})
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket allUserApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("AllUser")
        .consumes(getConsumeContentTypes())
        .produces(getProduceContentTypes())
        .apiInfo(apiInfo())
        .securityContexts(Arrays.asList(securityContext()))    // 전역 auth 설정을 위한 추가
        .securitySchemes(Arrays.asList(apiKey()))              // 전역 auth 설정을 위한 추가
        .useDefaultResponseMessages(false)
        .select()
        .apis(RequestHandlerSelectors.basePackage("gopos"))
        .paths(regex("/(test)/.*"))
        .build();
  }

  private Set<String> getConsumeContentTypes() {
    Set<String> consumes = new HashSet<>();
    consumes.add("application/json;charset=UTF-8");
    consumes.add("application/x-www-form-urlencoded");
    return consumes;
  }

  private Set<String> getProduceContentTypes() {
    Set<String> produces = new HashSet<>();
    produces.add("application/json;charset=UTF-8");
    return produces;
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("GoPos Backend API")
        .description("GoPos Backend API Document")
        .version("1.0")
        .build();
  }

  // Swagger 전역에 JWT token값 세팅을 위해 추가 ->
  // "X-AUTH-TOKEN" 명으로 받아서 세팅하기때문에 "X-AUTH-TOKEN" 명 주의
  private ApiKey apiKey() {
    return new ApiKey("JWT", "X-AUTH-TOKEN", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext
        .builder()
        .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
  }

}
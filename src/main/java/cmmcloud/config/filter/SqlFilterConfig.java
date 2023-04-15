package cmmcloud.config.filter;

import cmmcloud.filter.SqlInjectionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqlFilterConfig {

    @Bean
    public FilterRegistrationBean filterRegister() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new SqlInjectionFilter());
        return registrationBean;
    }
}

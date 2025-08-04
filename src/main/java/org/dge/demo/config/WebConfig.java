package org.dge.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "DGE Demo Chat Microservice API",
                version = "1.0",
                description = "API documentation for DGE Demo Chat Microservice API"
        )
)
public class WebConfig {
    @Bean
    public FilterRegistrationBean<ApiKeyFilter> filter(ApiKeyFilter f) {
        FilterRegistrationBean<ApiKeyFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(f);
        bean.addUrlPatterns("/*");
        return bean;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

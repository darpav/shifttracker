package com.hita.shifttracker.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins() // No origins allowed, effectively blocking CORS
                .allowedMethods("*")
                .allowedHeaders("*");
    }

//    @Bean
//    public Filter cspFilter() {
//        return new OncePerRequestFilter() {
//
//            @Override
//            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//                // Add the Content Security Policy header
//                //response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self'; style-src 'self';");
//                //response.setHeader("Content-Security-Policy", "default-src 'self'; frame-src 'self' https://apache-superset-railway-19vo-production.up.railway.app;");
//                response.setHeader("Content-Security-Policy", "default-src 'self'; frame-src 'self' https://apache-superset-railway-19vo-production.up.railway.app; frame-ancestors 'self' http://localhost:8080;");
//
//
//                filterChain.doFilter(request, response);
//            }
//        };
//    }

    @Bean
    public FilterRegistrationBean<CSPFilter> cspFilter() {
        FilterRegistrationBean<CSPFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CSPFilter());
        registrationBean.addUrlPatterns("/*");  // Apply the filter to all URLs
        return registrationBean;
    }
}

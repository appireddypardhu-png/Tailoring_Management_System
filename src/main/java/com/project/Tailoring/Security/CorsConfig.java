package com.project.Tailoring.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.filter.CorsFilter;

@Configuration(proxyBeanMethods = false)
public class CorsConfig {

    @Bean
    @org.springframework.context.annotation.Lazy
    public CorsFilter corsFilter() {

        CorsConfiguration config =
                new CorsConfiguration();

        config.addAllowedOrigin("*");

        config.addAllowedHeader("*");

        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                config
        );

        return new CorsFilter(source);
    }
}
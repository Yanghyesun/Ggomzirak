package com.ggomzirak;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ggomzirak.service.JWTInterceptor;

@EnableScheduling
@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer implements WebMvcConfigurer{
	@Autowired
	private JWTInterceptor jwtInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor)
		.addPathPatterns(Arrays.asList("/api/v1/tips/register","/api/v1/tips/comments/register","/api/v1/tips/likes/**","/api/v1/shorts/register","/api/v1/shorts/comments/register","/api/v1/shorts/likes/**","/api/v1/qna/register","/api/v1/qna/comments/register","/api/v1/user/**","/openvidu/**"))
		.excludePathPatterns(Arrays.asList("/api/v1/user/login","/api/v1/user/reg","/api/v1/user/check/**","/openvidu/api/sessions/*/connection/**"));
	}
	

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.exposedHeaders("jwt-auth-access-token")
		.exposedHeaders("jwt-auth-refresh-token");
	}
}

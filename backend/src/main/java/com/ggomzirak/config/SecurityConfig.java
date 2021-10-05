package com.ggomzirak.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ggomzirak.db.entity.Role;
//import com.ggomzirak.oauth.service.CustomOAuth2UserService;

/**
 * 인증(authentication) 와 인가(authorization) 처리를 위한 스프링 시큐리티 설정 정의.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private SsafyUserDetailService ssafyUserDetailService;
//    
//    @Autowired
//    private UserService userService;
    
// Password 인코딩 방식에 BCrypt 암호화 방식 사용
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**","/font/**","/html/**");
//        web.ignoring().mvcMatchers("/members/**","image/**");
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    // DAO 기반으로 Authentication Provider를 생성
    // BCrypt Password Encoder와 UserDetailService 구현체를 설정
//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(this.ssafyUserDetailService);
//        return daoAuthenticationProvider;
//    }

    // DAO 기반의 Authentication Provider가 적용되도록 설정
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider());
//    }
    
//    @Autowired
//    private CustomOAuth2UserService customOAuth2UserService;
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 사용 하지않음
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), userService)) //HTTP 요청에 JWT 토큰 인증 필터를 거치도록 필터를 추가
//                .authorizeRequests()
//                .antMatchers("/api/v1/users/me").authenticated()       //인증이 필요한 URL과 필요하지 않은 URL에 대하여 설정
//    	        	    .anyRequest().permitAll()
//                .and().cors();

    	http
    		.cors()
    		.and()
    		.csrf().disable();//403에러나서
    	
//    	  http
    	  //.authorizeRequests()
//          .antMatchers("/", "/oauth2/**", "/login/**", "/css/**",
//                  "/images/**", "/js/**", "/console/**", "/favicon.ico/**")
//          .permitAll()
//          .anyRequest().authenticated()
//          .and()
//          .oauth2Login()
//          .and()
//          .exceptionHandling()
//          .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
    	  //.anyRequest()
    	  //.permitAll()
//          .csrf().disable()
//          .headers().frameOptions().disable()
//          .and()
//          .authorizeRequests()
//          .antMatchers("/", "/css/**", "/images/**",
//                  "/js/**", "/h2-console/**").permitAll()
//          .antMatchers("/api/v1/**").hasRole(Role.
//          GUEST.name())
//          .anyRequest().authenticated()
//    	  .and()
//    	  	.logout()
//    	  	.clearAuthentication(true)
//            .invalidateHttpSession(true)
//            .deleteCookies("JSESSIONID")
//            .logoutSuccessUrl("/lives") // 로그아웃 성공시
//            .permitAll()
//    	  .and()
//    	  	.oauth2Login()
//
//    	  	.defaultSuccessUrl("/tips")
//    	  	.userInfoEndpoint();
//    	  	.userService(customOAuth2UserService);

    }
    
    
}
package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	OAuth2UserService<OAuth2UserRequest, OAuth2User> myOAuth2UserService() {
		return new MyOAuth2UserService();
	}
	
	//DB의 인증정보를 이용해서 인증처리하는 service
	@Bean
	MyUserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
    PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
    }
	
	@Bean
    public HiddenHttpMethodFilter httpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return hiddenHttpMethodFilter;
    }
	
	
	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http
 			.authorizeRequests(authorize -> authorize
 						.antMatchers("/css/**","/images/**","/js/**","/webjars/**","/files/**").permitAll()
 						.antMatchers("/","/members/**","/comm/**","/check/**","/my-websocket/**","/common/**").permitAll()
 						.antMatchers("/admin/**").hasRole("ADMIN")
 						.antMatchers(HttpMethod.PATCH,"/admin/**").hasRole("ADMIN")
 						.anyRequest().authenticated()
 					)
 					.formLogin(formLogin->formLogin
 							.loginPage("/members/signin")//로그인 페이지
 							.loginProcessingUrl("/members/signin")//form안의 action 경로
 							.usernameParameter("email")
 							.passwordParameter("pass")
 							.defaultSuccessUrl("/")//성공시 이동 url
 							.failureUrl("/members/signin")//실패시 이동 url
 							.permitAll()
 							)
 					.logout(logout->logout
 							.logoutSuccessUrl("/members/signin"))
 					.csrf(csrf->csrf.disable()
 							)
 					.oauth2Login(oauth2->oauth2
 							.loginPage("/members/signin")
 							.userInfoEndpoint().userService(myOAuth2UserService())
 							)
 					
 					;
 		return http.build();
 	}
	
	@Bean
	public HttpFirewall defaultHttpFirewall() {
		return new DefaultHttpFirewall();
	}
	
	public void configure(WebSecurity web) throws Exception {
		web.httpFirewall(defaultHttpFirewall());
	}
	

}
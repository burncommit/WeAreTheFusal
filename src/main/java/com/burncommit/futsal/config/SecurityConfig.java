package com.burncommit.futsal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        // 정적 리소스들이 보안필터를 거치지 않게끔
	        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/font/**");
	    }
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.cors().disable()                               // cors 방지
	                .csrf().disable()                           // csrf 방지
	                .headers().frameOptions().disable();        // x frame 방어 해제

	        http.authorizeRequests()
	                // 페이지 권한 설정 일단 로그인 페이지만 열어두고 여기는 퍼밋all 이니깐 그냥 권한 없이도 접근 가능한 곳 
//	        		.antMatchers("/login","/","/members/new").permitAll() //get요청 
	        		.antMatchers("/adminPage").hasRole("USER") // 권한에 따라서 접근할수 있는 페이지
	        		//위 쪽에 권한 주고 접근 페이지나 API 걸어주면대 컨트롤러 에서 데이터 받을때도 권한 없으면 못받지
//	        		.anyRequest().authenticated() //이부분에서 어쏘시케이티드로 적용해주고
	        		.anyRequest().permitAll()
                	.and()
	        		.formLogin()
	                    .loginPage("/login") // 로그인하는 페이지 설정해주는 부분 여기 없으면 시큐리티 기본 로그인 페이지
	                    .loginProcessingUrl("/loginSuccess") //로그인 이메일과 패스워드 받아주는 url login폼에 action과 일치시켜준다
	                    .defaultSuccessUrl("/") // 이제 로그인 성공하면 가는 url
	                    .failureUrl("/login?error=true") //실패
	                    .usernameParameter("email") // 이메일 login폼에 name속성과 일치시켜준다
	                    .passwordParameter("password") //비밀번호 login폼에 name속성과 일치시켜준다
	                .and()
	                    .logout() //로그아웃
	                    .logoutSuccessUrl("/?logout=true") //로그아웃을 성공했을 때 돌아가는 주소 이것 또한 param을 같이 넘길 수 있다.
	                    .invalidateHttpSession(true) //로그아웃 시 session을 전부 날린다.
	                    .deleteCookies("JSESSIONID"); //인자로 적은 값의 cookies를 죽인다.

	        // status code 핸들링
	       http.exceptionHandling().accessDeniedPage("/denied");

	        return http.build();
	    }
	
}

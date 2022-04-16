package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/*
	 * 「/customer」這個 API 及其底下的所有 GET 請求，需通過身份驗證才可存取。 其餘 API 的所有GET請求，允許所有呼叫方存取。
	 * 「/customer」這個 API 的 POST 請求，允許所有呼叫方存取。 其餘的所有 API，需通過身份驗證才可存取。
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/auth").permitAll()
	        .antMatchers(HttpMethod.POST, "/auth/parse").permitAll()
			.antMatchers(HttpMethod.GET, "/customer/**").authenticated()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.POST, "/customer/insertCustomer").permitAll()
			.antMatchers(HttpMethod.DELETE, "/customer/deleteCustomer").hasAuthority("admin")
			.anyRequest().authenticated()
//			.anyRequest().permitAll()
			.and().csrf().disable().formLogin();
	}

	/*
	 * 使用 UserDetailsService 這個介面來注入，
	 * Spring 會自動找到有實作這個介面的類別，也就是 SpringUserService。 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

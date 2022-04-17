package com.example.security;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;
    
    /*
     * 從請求標頭取出 Token 進行解析，藉此查詢使用者詳情。隨後提供資料給 Security 機制，向伺服器表明該請求方是通過驗證的使用者。
     */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
	    if (authHeader != null) {
	        String accessToken = authHeader.replace("Bearer ", "");

	        Map<String, Object> claims = jwtService.parseToken(accessToken);
	        String username = (String) claims.get("username");
	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	        Authentication authentication =
	                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    }

	    filterChain.doFilter(request, response);
		
	}

}

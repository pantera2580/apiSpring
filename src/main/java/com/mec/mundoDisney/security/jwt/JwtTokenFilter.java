package com.mec.mundoDisney.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.filter.OncePerRequestFilter;

import com.mec.mundoDisney.security.service.UserDetailsServiceImplem;

public class JwtTokenFilter extends OncePerRequestFilter {
	
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailsServiceImplem userDetailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain){
		String token = getToken(request);
		if(token!=null && jwtProvider.validateToken(token)) {
			String userName = jwtProvider.getUserNameFromToken(token);
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			
		}
		try {
			filterChain.doFilter(request, response);
		} catch (IOException e) {
			logger.error("fail en el metodo filter");
			e.printStackTrace();
		} catch (ServletException e) {
			logger.error("fail en el metodo filter");
			e.printStackTrace();
		}
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		if(header!=null && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		}
		return null;
	}

}

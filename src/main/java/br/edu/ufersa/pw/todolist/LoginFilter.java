package br.edu.ufersa.pw.todolist;

import java.io.IOException; 
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.ufersa.pw.todolist.dto.CreateUserDto;
import br.edu.ufersa.pw.todolist.services.AuthenticationService;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	public LoginFilter(String url, AuthenticationManager authManager){
			super(new AntPathRequestMatcher(url));
			setAuthenticationManager(authManager);
	}
@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
	throws AuthenticationException, IOException, ServletException {
		
		CreateUserDto dto = new ObjectMapper()
				.readValue(req.getInputStream(), CreateUserDto.class);
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
				dto.getEmail(), dto.getSenha(), Collections.emptyList()));
	}
@Override
	protected void successfulAuthentication(HttpServletRequest req,HttpServletResponse res, FilterChain chain, Authentication auth)
	throws IOException, ServletException {
		AuthenticationService.addToken(res, auth.getName());
	}
}


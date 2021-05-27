package br.com.zup.mercado.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zup.mercado.entity.User;
import br.com.zup.mercado.repository.UserRepository;

public class AuthenticationByTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private UserRepository repository;
	
	public AuthenticationByTokenFilter(TokenService tokenService, UserRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recoverToken(request);
		boolean valid = tokenService.isTokenValid(token);
		System.out.println("gustavo-> "+valid+" = "+token);
		if(valid) {
			authUser(token);
		}
		filterChain.doFilter(request, response);
	}
	
	private void authUser(String token) {
		
		Long idUser = tokenService.getUserId(token);
		User user = repository.findById(idUser).get();
		System.out.println(user);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
		System.out.println("Gustavo olha-> "+authentication.isAuthenticated()); 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println("Gustavo olha2-> "+authentication.isAuthenticated());
	}

	private String recoverToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}

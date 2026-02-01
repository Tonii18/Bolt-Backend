package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String jwt = getJwtFromRequest(request);

		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {

//			String username = tokenProvider.getEmailFromJWT(jwt);
//			String role = tokenProvider.getRoleFromJWT(jwt); // ROLE_ADMIN
//
//			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
//					List.of(new SimpleGrantedAuthority(role)));
//
//			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			String username = tokenProvider.getEmailFromJWT(jwt);

			var userDetails = customUserDetailsService.loadUserByUsername(username);

			UsernamePasswordAuthenticationToken authentication =
			    new UsernamePasswordAuthenticationToken(
			        userDetails,
			        null,
			        userDetails.getAuthorities()
			    );


			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}

		return null;
	}
}

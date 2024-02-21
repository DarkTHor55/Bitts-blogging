//package com.Bitts.security;
//
//import java.io.IOException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import com.Bitts.security.JWTTokenHelper;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//
//@Component
//public class JWTAuthenticationFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private JWTTokenHelper helper;
//	@Autowired
//	private UserDetailsService detailsService;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//
////get token
//		String requestToken = request.getHeader("Authorization");
//		// Bearer 2352523sdgsg
//		System.out.println(requestToken);
//		String username = null;
//		String token = null;
//		if (requestToken != null && requestToken.startsWith("Bearer")) {
//			token = requestToken.substring(7);
//			try {
//				username = this.helper.getUsernameFromToken(token);
//
//			} catch (IllegalArgumentException e) {
//				System.out.println("unable to get token");
//			} catch (ExpiredJwtException e) {
//				System.out.println("jwt token has expire");
//			} catch (MalformedJwtException e) {
//				System.out.println();
//			}
//		} else {
//			System.out.println("Jwt token does nnot begain with bearer");
//		}
//		// once we get the token now validate
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userDetails = this.detailsService.loadUserByUsername(username);
//			//sai clra h 
////			Authentication krna h 
//			if (this.helper.validationToken(token, userDetails)) {
//				UsernamePasswordAuthenticationToken authenticationPasswordToken = new UsernamePasswordAuthenticationToken(
//						userDetails, null, userDetails.getAuthorities());
//				authenticationPasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(null);
//			}
//
//			else {
//				System.out.println("invalid jwrt token");
//			}
//		} else {
//			System.out.println("username is null or context is not  nulls");
//		}
//		filterChain.doFilter(request, response);
//	}
//
//}

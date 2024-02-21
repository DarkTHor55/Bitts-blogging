//package com.Bitts.security;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//@Component
//public class JWTTokenHelper {
//	public static final long JWT_TOKEN_VALIDATY= 5 * 60 * 60 ;
//	private String secret ="jwtTokenKey";
//	
//	//retrive username from jwt token
//	public String getUsernameFromToken(String token) {
//		return getClaimFromToken(token,Claims::getSubject);
//	}
//	// retrive expiration data from jwt token 
//	public Date getExpirationDateFromToken(String token) {
//		return getClaimFromToken(token,Claims::getExpiration);
//	}
//
//	public <T> T getClaimFromToken(String token,Function<Claims,T>claimsResolver) {
//		final Claims claims=getAllClaimsFromToken(token);
//		return claimsResolver.apply(claims);
//		
//	}
//	//for retrivinf any information from token we will need the srcret key
//	   private Claims getAllClaimsFromToken(String token) {
//	        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
//	    }
////	check if the token has expired
//	private Boolean isTokenExpires(String token) {
//		final Date expiration=getExpirationDateFromToken(token);
//		return expiration.before(new Date());
//	}
//	//genrate token for user
//	public String generateToken(UserDetails details) {
//		Map<String ,Object>claims=new HashMap<>();
//		return doGenerateToken(claims,details.getUsername());
//	}
//	private String doGenerateToken(Map<String, Object>claims,String subject) {
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDATY*1000)).signWith(SignatureAlgorithm.HS512,secret).compact();
//	}
//	 //validation token
//	public Boolean validationToken(String token,UserDetails details) {
//		final String username=getUsernameFromToken(token);
//		return (username.equals(details.getUsername())&&!isTokenExpires(token));
//	}
////	private String gCreateJWTTokenHelperetClaimFromToken(String token, Object object) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	
//
//}

//package com.Bitts.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.Bitts.excepction.ResourceNotFound;
//import com.Bitts.model.User;
//import com.Bitts.repository.UserRepos;
//
//@Service
//public class CustomUserDetailService implements UserDetailsService {
//	@Autowired
//	private UserRepos userrepos;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// loading user from database by username
//		User user = this.userrepos.findByEmail(username)
//				.orElseThrow(() -> new ResourceNotFound("user", "email " + username, (long) 0));
//
//		return user;
//	}
//
//}

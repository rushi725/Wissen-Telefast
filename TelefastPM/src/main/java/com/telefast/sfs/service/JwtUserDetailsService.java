package com.telefast.sfs.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.telefast.sfs.model.Employee;
import com.telefast.sfs.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.telefast.sfs.model.User  user=userRepository.findByUserName(username); 
		if (user.getEmail().equals(username)) {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
			Employee emp = user.getEmployee();
			System.out.println(emp.getEmpRole().toString());
			grantedAuthorities.add(new SimpleGrantedAuthority(emp.getEmpRole().toString()));
			return new User(username,user.getPassword(),
					grantedAuthorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
}
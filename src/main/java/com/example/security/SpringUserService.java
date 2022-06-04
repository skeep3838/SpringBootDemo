package com.example.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.Customer;
import com.example.entity.Function;
import com.example.entity.Role;
import com.example.service.CustomerService;

@Service
public class SpringUserService implements UserDetailsService {

	@Autowired
	private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer user = customerService.findByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException("Username is wrong.");
		
		return new User(user.getUserName(), user.getPassword(), getAuthorities(user.getRole()));
	}

	/*
	 * 取得授權的functions
	 * 取得授權的角色
	 */
	private List<GrantedAuthority> getAuthorities(Collection<Role> roles) {
		List<GrantedAuthority> roleSet = new ArrayList<>();
		for(Role role:roles) {
			roleSet.add(new SimpleGrantedAuthority(role.getName()));
		}	
		return roleSet;
//		return getGrantedAuthorities(getFunctions(roles));
	}

	/*
	 * 取得所有角色授權的function
	 */
	private List<String> getFunctions(Collection<Role> roles) {

		List<String> functions = new ArrayList<>();
		List<Function> collection = new ArrayList<>();
		for (Role role : roles) {
			functions.add(role.getName());
			collection.addAll(role.getFunctions());
		}
		for (Function item : collection) {
			functions.add(item.getName());
		}
		return functions;
	}

	/*
	 * 將授權的functions放進authorities
	 */
	private List<GrantedAuthority> getGrantedAuthorities(List<String> functions) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String function : functions) {
			authorities.add(new SimpleGrantedAuthority(function));
		}
		return authorities;
	}

}

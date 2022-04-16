package com.example.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		return new User(user.getUserName(), user.getPassword(), getAuthorities(user.getRoles()));
	}

	/*
	 * 取得授權的functions
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getFunctions(roles));
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

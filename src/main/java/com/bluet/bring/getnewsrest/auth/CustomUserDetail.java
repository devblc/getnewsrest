package com.bluet.bring.getnewsrest.auth;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bluet.bring.getnewsrest.auth.models.User;


public class CustomUserDetail implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	private boolean active;
	private List<GrantedAuthority> autorities;
	
	public CustomUserDetail() {
		super();	
	}

	public CustomUserDetail(User user) {
		super();
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.autorities = Arrays.stream(user.getRoles().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
				
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}




}

package com.devoir.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails extends User implements UserDetails{
	
	private static final long serialVersionUID = 7375505939470153350L;
	@JsonIgnore
	private String username;
	@JsonIgnore
	private String password;

	@JsonIgnore
	private List<GrantedAuthority>authorities;
	
	public MyUserDetails(User user) {
	
		super(user.getId(),
				user.getName(),
				user.getEmail(),
				user.getPassword(),
				user.getPhone(),
				user.getCreatedAt(),
				user.getUpdatedAt(),
				user.isAccountNonLocked(),
				user.isEnabled(),
				user.isCredentialsNonExpired());
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
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

	@Override
	public String getUsername() {
		
		return username;
	}
}

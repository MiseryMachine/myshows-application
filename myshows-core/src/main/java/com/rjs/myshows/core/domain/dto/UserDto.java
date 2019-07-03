package com.rjs.myshows.core.domain.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.rjs.myshows.core.domain.BaseElement;
import com.rjs.myshows.core.domain.User;
import com.rjs.myshows.core.domain.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends BaseElement<Long> implements User<Long> {
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private LocalDate dateOfBirth;
	private Set<Role> roles = new HashSet<>();

	@Override
	public void grantRole(Role role) {
		roles.add(role);
	}

	@Override
	public void revokeRole(Role role) {
		roles.remove(role);
	}

	@Override
	public boolean hasRole(Role role) {
		return roles.contains(role);
	}
}

package com.rjs.myshows.core.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.rjs.myshows.core.domain.security.Role;


public interface User<ID extends Serializable> {
	ID getElementId();
	void setElementId(ID elementId);

	String getUsername();
	void setUsername(String username);

	String getEmail();
	void setEmail(String email);

	String getFirstName();
	void setFirstName(String firstName);

	String getLastName();
	void setLastName(String lastName);

	boolean isEnabled();
	void setEnabled(boolean enabled);

	LocalDate getDateOfBirth();
	void setDateOfBirth(LocalDate dateOfBirth);

	Set<Role> getRoles();
	void setRoles(Set<Role> roles);
	void grantRole(Role role);
	void revokeRole(Role role);
	boolean hasRole(Role role);
}

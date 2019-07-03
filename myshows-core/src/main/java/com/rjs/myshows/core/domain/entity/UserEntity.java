package com.rjs.myshows.core.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.rjs.myshows.core.domain.User;
import com.rjs.myshows.core.domain.security.Role;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@Document(collection = "user")
@Entity
@Table(name = "user")
public class UserEntity extends JpaBaseEntity implements User<Long> {
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private boolean enabled = false;
	private LocalDate dateOfBirth;
	private Set<Role> roles = new HashSet<>();

	@Column(name = "username", length = 40, unique = true, nullable = false)
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "email", length = 80)
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "first_name", length = 40)
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", length = 40)
	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "enabled", nullable = false)
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Column(name = "dob")
	@Override
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(value = EnumType.STRING)
	@Column(name = "role")
	@Override
	public Set<Role> getRoles() {
		return roles;
	}

	@Override
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	@Override
	public void grantRole(Role role) {
		if (role != null) {
			roles.add(role);
		}
	}

	@Transient
	@Override
	public void revokeRole(Role role) {
		if (role != null) {
			roles.remove(role);
		}
	}

	@Transient
	@Override
	public boolean hasRole(Role role) {
		return role != null && roles.contains(role);
	}
}

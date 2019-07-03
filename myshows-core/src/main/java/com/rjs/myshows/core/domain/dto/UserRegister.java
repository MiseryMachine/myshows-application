package com.rjs.myshows.core.domain.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRegister {
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String password;
	private String confirmPassword;
}

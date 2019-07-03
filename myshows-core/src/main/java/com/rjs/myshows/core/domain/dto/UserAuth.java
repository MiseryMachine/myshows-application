package com.rjs.myshows.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAuth {
	private String username;
	private String password;
	private String confirmPassword;
}

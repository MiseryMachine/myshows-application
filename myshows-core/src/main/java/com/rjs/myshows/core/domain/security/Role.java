package com.rjs.myshows.core.domain.security;

import java.util.Arrays;

public enum Role {
	ROLE_USER("User"),
	ROLE_REST_USER("REST User"),
	ROLE_ADMIN("Admin");

	public final String text;

	Role(String text) {
		this.text = text;
	}

	public static Role findByText(String txt) {
		return Arrays.stream(values())
			.filter(r -> r.text.equals(txt))
			.findFirst()
			.orElse(null);
	}

	public String toString() {
		return this.text;
	}
}

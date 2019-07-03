package com.rjs.myshows.core.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShowFilter {
	private String title;
	private String showTypeName;
	@JsonDeserialize(as = HashSet.class)
	private Set<String> genres;
	@JsonDeserialize(as = HashSet.class)
	private Set<String> showRatings;
	@JsonDeserialize(as = HashSet.class)
	private Set<String> mediaFormats;
}

package com.rjs.myshows.core.domain.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rjs.myshows.core.domain.BaseElement;
import com.rjs.myshows.core.domain.Show;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowDto extends BaseElement<Long> implements Show<Long> {
	private String mdbId;
	private String imdbId;
	private String title;
	private String showRating;
	@JsonDeserialize(as = HashSet.class)
	private Set<String> contents = new HashSet<>();
	private String tagLine;
	private String description;
	private LocalDate releaseDate;
	private String releaseDateText;
	private Integer runtime;
	private String showType;
	@JsonDeserialize(as = HashSet.class)
	private Set<String> genres = new HashSet<>();;
	private String mediaFormat;
	private String personalNotes;
	private int starRating = 0;
}

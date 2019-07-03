package com.rjs.myshows.core.domain.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rjs.myshows.core.domain.BaseElement;
import com.rjs.myshows.core.domain.ShowType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShowTypeDto extends BaseElement<Long> implements ShowType<Long> {
	private String name;
	@JsonDeserialize(as = HashSet.class)
	private Set<String> genres = new HashSet<>();
	@JsonDeserialize(as = HashSet.class)
	private Set<String> ratings = new HashSet<>();

	@Override
	public void addGenre(String genre) {
		genres.add(genre);
	}

	@Override
	public void removeGenre(String genre) {
		genres.remove(genre);
	}

	@Override
	public boolean hasGenre(String genre) {
		return genres.contains(genre);
	}

	@Override
	public void addRating(String rating) {
		ratings.add(rating);
	}

	@Override
	public void removeRating(String rating) {
		ratings.remove(rating);
	}

	@Override
	public boolean hasRating(String rating) {
		return ratings.contains(rating);
	}
}

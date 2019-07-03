package com.rjs.myshows.core.domain;

import java.io.Serializable;
import java.util.Set;

public interface ShowType<ID extends Serializable> {
	ID getElementId();
	void setElementId(ID elementId);

	String getName();
	void setName(String name);

	Set<String> getGenres();
	void setGenres(Set<String> genres);
	void addGenre(String genre);
	void removeGenre(String genre);
	boolean hasGenre(String genre);

	Set<String> getRatings();
	void setRatings(Set<String> ratings);
	void addRating(String rating);
	void removeRating(String rating);
	boolean hasRating(String rating);
}

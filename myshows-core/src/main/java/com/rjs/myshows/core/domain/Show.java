package com.rjs.myshows.core.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public interface Show<ID extends Serializable> {
	ID getElementId();
	void setElementId(ID elementId);

	String getMdbId();
	void setMdbId(String mdbId);

	String getImdbId();
	void setImdbId(String imdbId);

	String getTitle();
	void setTitle(String title);

	String getShowRating();
	void setShowRating(String showRating);

	Set<String> getContents();
	void setContents(Set<String> contents);

	String getTagLine();
	void setTagLine(String tagLine);

	String getDescription();
	void setDescription(String description);

	LocalDate getReleaseDate();
	void setReleaseDate(LocalDate releaseDate);

	String getReleaseDateText();
	void setReleaseDateText(String releaseDateText);

	Integer getRuntime();
	void setRuntime(Integer runtime);

	String getShowType();
	void setShowType(String showType);

	Set<String> getGenres();
	void setGenres(Set<String> genres);

	String getMediaFormat();
	void setMediaFormat(String mediaFormat);

	String getPersonalNotes();
	void setPersonalNotes(String personalNotes);

	int getStarRating();
	void setStarRating(int starRating);
}

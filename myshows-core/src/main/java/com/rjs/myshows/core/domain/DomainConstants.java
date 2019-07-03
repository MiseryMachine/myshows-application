package com.rjs.myshows.core.domain;

public class DomainConstants {
	public static final String VALUE_DELIMITER = ", ";
	public static final String MOVIE_TYPE = "Movie";
	public static final String TV_TYPE = "TV";
	public static final String[] SHOW_TYPES = {MOVIE_TYPE, TV_TYPE};
	public static final String[] MOVIE_RATINGS = {"G", "PG", "PG-13", "R", "NC-17", "N/R"};
	public static final String[] MOVIE_CONTENTS = {"Language", "Violence", "Substance Abuse", "Nudity", "Sexual Content"};
	public static final String[] TV_RATINGS = {"TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA", "N/R"};
	public static final String[] TV_CONTENTS = {"D", "L", "S", "V", "FV", "E/I", "N/R"};
	public static final String[] MEDIA_FORMATS = {"Any", "DVD", "BLU-RAY", "Amazon Prime", "Netflix", "Funimation"};
	public static final int[] STAR_RATINGS = {0, 1, 2, 3, 4, 5};
}

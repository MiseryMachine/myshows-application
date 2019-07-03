package com.rjs.myshows.core.domain.dto;

import org.springframework.hateoas.ResourceSupport;

public class ShowSummary extends ResourceSupport {
	public long id = -1;
	public String title = "";
	public String rating = "";
	public String overview = "";
	public String posterPath = "";
	public String releaseDate = "";
}

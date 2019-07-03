package com.rjs.myshows.core.service.mdb;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.rjs.myshows.core.config.CoreConfig;
import com.rjs.myshows.core.domain.dto.ShowDto;
import com.rjs.myshows.core.service.ImageService;

public abstract class MdbService {
	protected CoreConfig coreConfig;
	protected ImageService imageService;

	protected MdbService(CoreConfig coreConfig, ImageService imageService) {
		this.coreConfig = coreConfig;
		this.imageService = imageService;
	}

	public abstract List<ShowSummary> searchShows(String showTypeName, String title);
	public abstract Optional<ShowDto> addShow(String showTypeName, String mdbId);
	public abstract Set<String> getGenres(String showTypeName);
}

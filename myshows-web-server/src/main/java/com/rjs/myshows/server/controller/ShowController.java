package com.rjs.myshows.server.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.rjs.myshows.core.domain.ShowFilter;
import com.rjs.myshows.core.domain.dto.ShowDto;
import com.rjs.myshows.core.domain.dto.ShowSummary;
import com.rjs.myshows.core.domain.entity.ShowEntity;
import com.rjs.myshows.core.service.ShowService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/shows")
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class ShowController {
	private final Pageable defaultPageable = PageRequest.of(0, 25, Sort.by("title"));
	private final ShowService showService;

	public ShowController(ShowService showService) {
		this.showService = showService;
	}

	@GetMapping("/{id}")
	public ShowDto getShow(@PathVariable("id") Long id) {
		Optional<ShowEntity> showEntity = showService.findById(id);

		if (showEntity.isPresent()) {
			return showService.convertToDto(showEntity.get());
		}

		return new ShowDto();
	}

	@PostMapping("/search")
	public Resources<ShowSummary> searchShows(@RequestBody ShowFilter showFilter, Pageable pageable) {
		if (pageable == null) {
			pageable = defaultPageable;
		}

		Page<ShowEntity> shows = showService.filterShows(showFilter, pageable);
		List<ShowSummary> summaryList = new ArrayList<>();

		for (ShowEntity show : shows) {
			Link selfLink = linkTo(methodOn(ShowController.class).getShow(show.getId())).withSelfRel();
			ShowSummary showSummary = new ShowSummary();
			showSummary.id = show.getElementId();
			showSummary.rating = show.getShowRating();
			showSummary.title = show.getTitle();
			showSummary.overview = show.getTagLine();
			showSummary.releaseDate = show.getReleaseDateText();
//			showSummary.posterPath = String.format("/shows/poster-thumb/%d", show.getElementId());
			showSummary.posterPath = linkTo(methodOn(ShowController.class).getPosterThumbnail(show.getId())).toString();
			showSummary.add(selfLink);

			summaryList.add(showSummary);
		}

		Link link = linkTo(methodOn(ShowController.class).searchShows(showFilter, pageable)).withSelfRel();
		Resources<ShowSummary> result = new Resources<>(summaryList, link);

		if (shows.hasPrevious()) {
			result.add(linkTo(methodOn(ShowController.class).searchShows(showFilter, pageable.previousOrFirst())).withRel("previous"));
		}

		if (shows.hasNext()) {
			result.add(linkTo(methodOn(ShowController.class).searchShows(showFilter, pageable.next())).withRel("previous"));
		}

		return result;
	}

	@GetMapping(value = "/poster-thumb/{showId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public byte[] getPosterThumbnail(@PathVariable("showId") Long showId) {
		return showService.getPosterData(showId, true);
	}

	@GetMapping(value = "/poster/{showId}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public byte[] getPoster(@PathVariable("showId") Long showId) {
		return showService.getPosterData(showId, false);
	}
}

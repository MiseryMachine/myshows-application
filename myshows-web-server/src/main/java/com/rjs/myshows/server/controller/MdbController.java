package com.rjs.myshows.server.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rjs.myshows.core.domain.dto.ShowDto;
import com.rjs.myshows.core.service.mdb.MdbService;
import com.rjs.myshows.core.service.mdb.ShowSummary;

@RestController
@RequestMapping("/admin/mdb")
public class MdbController {
	private MdbService mdbService;

	public MdbController(MdbService mdbService) {
		this.mdbService = mdbService;
	}

	@PostMapping("/search/{showTypeName}")
	public List<ShowSummary> search(@PathVariable("showTypeName") String showTypeName, String title) {
		return mdbService.searchShows(showTypeName, title);
	}

	@PostMapping("/add/{showTypeName}/{mdbId}")
	public ShowDto addShow(@PathVariable("showTypeName") String showTypeName, @PathVariable("mdbId") String mdbId) {
		Optional<ShowDto> show = mdbService.addShow(showTypeName, mdbId);

		return show.orElse(null);
	}
}

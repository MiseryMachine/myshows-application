package com.rjs.myshows.core.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rjs.myshows.core.config.CoreConfig;
import com.rjs.myshows.core.domain.ShowFilter;
import com.rjs.myshows.core.domain.dto.ShowDto;
import com.rjs.myshows.core.domain.dto.ShowSummary;
import com.rjs.myshows.core.domain.entity.ShowEntity;
import com.rjs.myshows.core.repository.ShowRepository;
import com.rjs.myshows.core.repository.specification.IsMemberSpecification;
import com.rjs.myshows.core.repository.specification.TextLikeSpecification;
import com.rjs.myshows.core.repository.specification.ValueEqualsSpecification;
import com.rjs.myshows.core.repository.specification.ValueInSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("showService")
public class ShowService extends AbstractService<ShowEntity, ShowDto> {
	private final Logger logger = LoggerFactory.getLogger(ShowService.class);
	private final int pngImageType = 0;
	private final int jpgImageType = 1;
	private final int thumbnailWidth = 92;
	private final String[] imageExts = {".png", ".jpg", ".jpeg"};
	private final CoreConfig coreConfig;
	private final ShowRepository showRepository;
	private final File defaultPoster;
	private final File defaultPosterThumb;

	public ShowService(ModelMapper modelMapper, CoreConfig coreConfig, ShowRepository showRepository,
					   File defaultPoster, File defaultPosterThumb) {
		super(modelMapper);

		this.coreConfig = coreConfig;
		this.showRepository = showRepository;
		this.defaultPoster = defaultPoster;
		this.defaultPosterThumb = defaultPosterThumb;
	}

	public Optional<ShowEntity> findById(Long id) {
		return showRepository.findById(id);
	}

	public Optional<ShowEntity> findByMdbId(String mdbId) {
		return showRepository.findByMdbId(mdbId);
	}

	public Optional<ShowEntity> findByImdbId(String imdbId) {
		return showRepository.findByImdbId(imdbId);
	}

	public List<ShowEntity> findByTitleExact(String title) {
		return showRepository.findByTitle(title);
	}

	public List<ShowEntity> findByTitleLike(String title) {
		return showRepository.findByTitleLike("%" + title + "%", Sort.by("title").ascending());
	}

	public List<ShowEntity> findByStarRating(int starRating) {
		return showRepository.findByStarRating(starRating, Sort.by("title").ascending());
	}

	public Page<ShowEntity> filterShows(ShowFilter showFilter, Pageable pageable) {
		Specification<ShowEntity> showSpecification = buildSpecification(showFilter);

		return showSpecification != null ? showRepository.findAll(showSpecification,  pageable) :
			new PageImpl<>(new ArrayList<>());
	}

	public ShowEntity save(ShowEntity show) {
		return showRepository.save(show);
	}

	public void delete(Long showId) {
		showRepository.deleteById(showId);
	}

	public ShowSummary convertToSummary(ShowEntity showEntity) {
		ShowSummary showSummary = new ShowSummary();
		showSummary.id = showEntity.getElementId();
		showSummary.rating = showEntity.getShowRating();
		showSummary.title = showEntity.getTitle();
		showSummary.overview = showEntity.getTagLine();
		showSummary.releaseDate = showEntity.getReleaseDateText();
//		showSummary.posterPath = "";

		return showSummary;
	}

	public byte[] getPosterData(Long showId, boolean thumbnail) {
		if (showId == null) {
			return new byte[0];
		}

		String filename = thumbnail ? "poster_thumb" : "poster";
		String localPath = coreConfig.getLocalImagePath(showId);
		File imageFile = null;

		for (String ext : imageExts) {
			imageFile = new File(localPath, filename + ext);

			if (imageFile.exists()) {
				break;
			}
		}

		if (!imageFile.exists()) {
			imageFile = thumbnail ? defaultPosterThumb : defaultPoster;
		}

		try (FileInputStream fis = new FileInputStream(imageFile)) {
			return IOUtils.toByteArray(fis);
		}
		catch (IOException e) {
			logger.error(String.format("Error reading poster file: %s", imageFile.getAbsolutePath()), e);
		}

		return new byte[0];
	}

	@Override
	public ShowDto convertToDto(ShowEntity showEntity) {
		return showEntity != null ? modelMapper.map(showEntity, ShowDto.class) : null;
	}

	@Override
	public List<ShowDto> convertToDto(List<ShowEntity> showEntities) {
		if (showEntities == null || showEntities.isEmpty()) {
			return new ArrayList<>();
		}

		Type dtoListType = new TypeToken<List<ShowDto>>() {}.getType();

		return modelMapper.map(showEntities, dtoListType);
	}

	@Override
	public ShowEntity convertToEntity(ShowDto showDto) {
		if (showDto == null) {
			return null;
		}

		ShowEntity showEntity = new ShowEntity();

		if (showDto.getId() != null) {
			Optional<ShowEntity> opt = findById(showDto.getId());

			if (opt.isPresent()) {
				showEntity = opt.get();
			}
			else {
				logger.warn(String.format("Show entity with id %d does not exist in datasource.", showDto.getId()));

				return null;
			}
		}

		modelMapper.map(showDto, showEntity);

		return showEntity;
	}

	@Override
	public List<ShowEntity> convertToEntity(List<ShowDto> showDtos) {
		if (showDtos == null || showDtos.isEmpty()) {
			return new ArrayList<>();
		}


		final List<ShowEntity> showEntities = new ArrayList<>();

		showDtos.forEach(dto -> {
			ShowEntity showEntity = convertToEntity(dto);

			if (showEntity != null) {
				showEntities.add(showEntity);
			}
		});

		return showEntities;
	}

	private Specification<ShowEntity> buildSpecification(ShowFilter showFilter) {
		if (showFilter == null) {
			return null;
		}

		Specification<ShowEntity> showSpecification = null;

		if (StringUtils.isNotBlank(showFilter.getTitle())) {
			// Add the title filter
			showSpecification = new TextLikeSpecification<>("title", showFilter.getTitle());
		}

		if (StringUtils.isNotBlank(showFilter.getShowTypeName())) {
			// Add the show type filter
			ValueEqualsSpecification<ShowEntity> showTypeSpec = new ValueEqualsSpecification<>("showType",
				showFilter.getShowTypeName());

			if (showSpecification != null) {
				showSpecification = showSpecification.and(showTypeSpec);
			}
			else {
				showSpecification = showTypeSpec;
			}
		}

		if (showFilter.getGenres() != null && !showFilter.getGenres().isEmpty()) {
			// Add the genre filter
			IsMemberSpecification<ShowEntity> genreSpec = new IsMemberSpecification<>("genres",
				showFilter.getGenres());

			if (showSpecification != null) {
				showSpecification = showSpecification.and(genreSpec);
			}
			else {
				showSpecification = genreSpec;
			}
		}

		if (showFilter.getShowRatings() != null && !showFilter.getShowRatings().isEmpty()) {
			// Add the show rating filter
			ValueInSpecification<ShowEntity, String> showRatingSpec = new ValueInSpecification<>("showRating",
				showFilter.getShowRatings());

			if (showSpecification != null) {
				showSpecification = showSpecification.and(showRatingSpec);
			}
			else {
				showSpecification = showRatingSpec;
			}
		}

		if (showFilter.getMediaFormats() != null && !showFilter.getMediaFormats().isEmpty()) {
			// Add the media format filter
			ValueInSpecification<ShowEntity, String> mediaFormatSpec = new ValueInSpecification<>("mediaFormat",
				showFilter.getMediaFormats());

			if (showSpecification != null) {
				showSpecification = showSpecification.and(mediaFormatSpec);
			}
			else {
				showSpecification = mediaFormatSpec;
			}
		}

		return showSpecification;
	}
}

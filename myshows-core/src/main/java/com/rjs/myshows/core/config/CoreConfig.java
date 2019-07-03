package com.rjs.myshows.core.config;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
@NoArgsConstructor
@Configuration
public class CoreConfig {
	private final Logger logger = LoggerFactory.getLogger(CoreConfig.class);

	@Value("${datePattern:yyyy-MM-dd}")
	private String datePattern;

	@Value("${localFilePath}")
	private String localFilePath;


	@Bean
	public DateTimeFormatter dateFormat() {
		return DateTimeFormatter.ofPattern(datePattern);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public File defaultPoster() {
		try {
			ClassPathResource resource = new ClassPathResource("/static/img/default-movie-box.png");

			return resource.getFile();
		}
		catch (IOException e) {
			logger.error("Cannot locate default box art image.", e);
		}

		return null;
	}

	@Bean
	public File defaultPosterThumb() {
		try {
			ClassPathResource resource = new ClassPathResource("/static/img/default-movie-box-thumb.png");

			return resource.getFile();
		}
		catch (IOException e) {
			logger.error("Cannot locate default box art thumb image.", e);
		}

		return null;
	}

	public String getLocalImagePath(Long showId) {
		return String.format("%s/shows/%d/images", localFilePath, showId);
	}
}

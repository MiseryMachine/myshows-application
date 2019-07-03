package com.rjs.myshows.core.domain.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.rjs.myshows.core.domain.Show;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@Document(collection = "show")
@Entity
@Table(name = "show")
public class ShowEntity extends JpaBaseEntity implements Show<Long> {
	private String mdbId;
	private String imdbId;
	private String title;
	private String showRating;
	private Set<String> contents = new HashSet<>();
	private String tagLine;
	private String description;
	private LocalDate releaseDate;
	private String releaseDateText;
	private Integer runtime;
	private String showType;
	private Set<String> genres = new HashSet<>();
	private String mediaFormat;
	private String personalNotes;
	private int starRating = 0;

	@Column(name = "mdb_id", unique = true, nullable = false)
	@Override
	public String getMdbId() {
		return mdbId;
	}

	@Column(name = "imdb_id")
	@Override
	public void setMdbId(String mdbId) {
		this.mdbId = mdbId;
	}

	@Override
	public String getImdbId() {
		return imdbId;
	}

	@Override
	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	@Column(name = "title", nullable = false, length = 80)
	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "show_rating", nullable = false, length = 10)
	@Override
	public String getShowRating() {
		return showRating;
	}

	@Override
	public void setShowRating(String showRating) {
		this.showRating = showRating;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "show_content", joinColumns = @JoinColumn(name = "show_id"))
	@Column(name = "content")
	@Override
	public Set<String> getContents() {
		return contents;
	}

	@Override
	public void setContents(Set<String> contents) {
		this.contents = contents;
	}

	@Column(name = "tag_line")
	@Override
	public String getTagLine() {
		return tagLine;
	}

	@Override
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	@Column(name = "description", length = 2000)
	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "release_date")
	@Override
	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	@Override
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	@Column(name = "release_date_text", length = 40)
	@Override
	public String getReleaseDateText() {
		return releaseDateText;
	}

	@Override
	public void setReleaseDateText(String releaseDateText) {
		this.releaseDateText = releaseDateText;
	}

	@Column(name = "runtime")
	@Override
	public Integer getRuntime() {
		return runtime;
	}

	@Override
	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	@Column(name = "show_type", nullable = false)
	@Override
	public String getShowType() {
		return showType;
	}

	@Override
	public void setShowType(String showType) {
		this.showType = showType;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "show_genre", joinColumns = @JoinColumn(name = "show_id"))
	@Column(name = "genre")
	@Override
	public Set<String> getGenres() {
		return genres;
	}

	@Override
	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	@Column(name = "media_format", nullable = false)
	@Override
	public String getMediaFormat() {
		return mediaFormat;
	}

	@Override
	public void setMediaFormat(String mediaFormat) {
		this.mediaFormat = mediaFormat;
	}

	@Column(name = "personal_notes", length = 2000)
	@Override
	public String getPersonalNotes() {
		return personalNotes;
	}

	@Override
	public void setPersonalNotes(String personalNotes) {
		this.personalNotes = personalNotes;
	}

	@Column(name = "star_rating")
	@Override
	public int getStarRating() {
		return starRating;
	}

	@Override
	public void setStarRating(int starRating) {
		this.starRating = starRating;
	}
}

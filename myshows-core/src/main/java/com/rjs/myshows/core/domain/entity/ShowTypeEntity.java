package com.rjs.myshows.core.domain.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import com.rjs.myshows.core.domain.ShowType;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@Document(collection = "show-type")
@Entity
@Table(name = "show_type")
public class ShowTypeEntity extends JpaBaseEntity implements ShowType<Long> {
	private String name;
	private Set<String> genres = new HashSet<>();
	private Set<String> ratings = new HashSet<>();

	@Column(unique = true, nullable = false)
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "show_type_genre", joinColumns = @JoinColumn(name = "show_type_id"))
	@Column(name = "genre")
	@Override
	public Set<String> getGenres() {
		return genres;
	}

	@Override
	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	@Transient
	@Override
	public void addGenre(String genre) {
		genres.add(genre);
	}

	@Transient
	@Override
	public void removeGenre(String genre) {
		genres.remove(genre);
	}

	@Transient
	@Override
	public boolean hasGenre(String genre) {
		return genres.contains(genre);
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "show_type_rating", joinColumns = @JoinColumn(name = "show_type_id"))
	@Column(name = "rating")
	@Override
	public Set<String> getRatings() {
		return ratings;
	}

	@Override
	public void setRatings(Set<String> ratings) {
		this.ratings = ratings;
	}

	@Transient
	@Override
	public void addRating(String rating) {
		ratings.add(rating);
	}

	@Transient
	@Override
	public void removeRating(String rating) {
		ratings.remove(rating);
	}

	@Transient
	@Override
	public boolean hasRating(String rating) {
		return ratings.contains(rating);
	}
}

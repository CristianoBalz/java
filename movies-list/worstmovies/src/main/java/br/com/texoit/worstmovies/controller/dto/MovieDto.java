package br.com.texoit.worstmovies.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.texoit.worstmovies.model.Movie;

public class MovieDto {
	private int year;
	private String title;
	private String studios;
	private String producers;
	
	public MovieDto(Movie movie) {
		if(movie == null) {
			return;
		}
		this.year = movie.getYear();
		this.title = movie.getTitle();
		this.studios = movie.getStudios();
		this.producers = movie.getProducers();
	}
	
	public final int getYear() {
		return year;
	}	
	
	public final String getTitle() {
		return title;
	}
	
	public final String getStudios() {
		return studios;
	}
	
	public final String getProducers() {
		return producers;
	}
	
	public static List<MovieDto> converter(List<Movie> movies) {
		return movies.stream().map(MovieDto::new).collect(Collectors.toList());
	}
	
	public static Page<MovieDto> converter(Page<Movie> movies) {
		return movies.map(MovieDto::new);
	}

	@Override
	public String toString() {
		return "MovieDto [year=" + year + ", title=" + title + ", studios=" + studios + ", producers=" + producers
				+ "]";
	}	
	
}

package br.com.texoit.worstmovies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.texoit.worstmovies.model.Movie;
import br.com.texoit.worstmovies.model.MovieId;

public interface MovieRepository extends JpaRepository<Movie, MovieId>{
	
	@Query("SELECT m FROM Movie m WHERE m.winner = 'yes'")
	List<Movie> listMoviesWinner();
	
}

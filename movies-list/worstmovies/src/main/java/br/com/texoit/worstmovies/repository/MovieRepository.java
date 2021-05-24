package br.com.texoit.worstmovies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.texoit.worstmovies.model.Movie;
import br.com.texoit.worstmovies.model.MovieId;

public interface MovieRepository extends JpaRepository<Movie, MovieId>{

}

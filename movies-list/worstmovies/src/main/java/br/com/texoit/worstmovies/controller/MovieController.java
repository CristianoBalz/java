package br.com.texoit.worstmovies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.texoit.worstmovies.business.MovieBusiness;
import br.com.texoit.worstmovies.controller.dto.MovieDto;
import br.com.texoit.worstmovies.controller.dto.ProducerWinnerDto;
import br.com.texoit.worstmovies.model.Movie;
import br.com.texoit.worstmovies.repository.MovieRepository;

@RestController
public class MovieController {
	
	@Autowired
	private MovieRepository repository;
	
	@GetMapping("/movies/list")
	public Page<MovieDto> listAll(@PageableDefault(sort = "year", direction = Direction.DESC, page = 0, size = 50) Pageable paginacao) {
		Page<Movie> movies = repository.findAll(paginacao);
		return MovieDto.converter(movies);
	}	
	
	@GetMapping("/producers/prize-range")
	public ResponseEntity<ProducerWinnerDto> listProducers() {		
		return ResponseEntity.of(new MovieBusiness(repository).listProducersPrizeRange());
	}	
	
}

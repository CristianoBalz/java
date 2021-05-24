package br.com.texoit.worstmovies.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.texoit.worstmovies.business.MovieBusiness;
import br.com.texoit.worstmovies.controller.dto.MovieDto;
import br.com.texoit.worstmovies.controller.dto.ProducerWinnerDto;
import br.com.texoit.worstmovies.repository.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	private MovieRepository repository;
	
	@RequestMapping("/list")
	public List<MovieDto> listAll() {
		return new MovieBusiness(repository).listAll();
	}
	
	@RequestMapping("/producers-winner")
	public ProducerWinnerDto listProduces() {
		return new MovieBusiness(repository).listProducersWinner();
	}
}

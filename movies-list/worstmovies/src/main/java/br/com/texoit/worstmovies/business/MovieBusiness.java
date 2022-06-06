package br.com.texoit.worstmovies.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.texoit.worstmovies.controller.dto.MovieDto;
import br.com.texoit.worstmovies.controller.dto.ProducerWinnerDto;
import br.com.texoit.worstmovies.controller.dto.ProducerWinnerIntervalDto;
import br.com.texoit.worstmovies.model.Movie;
import br.com.texoit.worstmovies.repository.MovieRepository;


public class MovieBusiness {	
	
	private MovieRepository repository;
	
	public MovieBusiness(MovieRepository repository) {
		this.repository = repository;
	}
	
	public List<MovieDto> listAll() {
		return MovieDto.converter(repository.findAll());
	}
	
	public Optional<ProducerWinnerDto> listProducersPrizeRange() {
		
		List<Movie> listMoviesWinner = repository.listMoviesWinner();
		
		listMoviesWinner = extractProducersWinner(listMoviesWinner);		
		
		Map<String, List<Movie>> moviesByProducer = extractMoviesByProducer(listMoviesWinner);		
		
		List<ProducerWinnerIntervalDto> listProducersWinnerInterval = 
				calculateProducerWinnerInterval(moviesByProducer);
		
		Map<Integer, List<ProducerWinnerIntervalDto>> producersWinnerByInterval = 
				extractProducersWinnerByInterval(listProducersWinnerInterval);
			
		Optional<Entry<Integer, List<ProducerWinnerIntervalDto>>> minInterval = 
				producersWinnerByInterval.entrySet().stream().min(Comparator.comparingInt(Map.Entry::getKey));
		
		Optional<Entry<Integer, List<ProducerWinnerIntervalDto>>> maxInterval	= 
				producersWinnerByInterval.size() > 0 ? 
						producersWinnerByInterval.entrySet().stream()
							.max(Comparator.comparingInt(Map.Entry::getKey)) 
				: Optional.empty();
				
		ProducerWinnerDto producerWinner = new ProducerWinnerDto();
		producerWinner.setMin(minInterval.isPresent() ? minInterval.get().getValue() : new ArrayList<ProducerWinnerIntervalDto>());
		producerWinner.setMax(maxInterval.isPresent() ? maxInterval.get().getValue() : new ArrayList<ProducerWinnerIntervalDto>());
		return Optional.of(producerWinner);
		
	}

	private Map<Integer, List<ProducerWinnerIntervalDto>> extractProducersWinnerByInterval(
			List<ProducerWinnerIntervalDto> listProducerWinnerWithInterval) {
		
		return listProducerWinnerWithInterval.stream()
				.collect(Collectors.groupingBy((m) -> m.getInterval()));
		
	}

	private Map<String, List<Movie>> extractMoviesByProducer(List<Movie> listProducersWinner) {
		
		return listProducersWinner.stream()
				.collect(Collectors.groupingBy(m -> m.getProducers()));
		
	}

	private List<ProducerWinnerIntervalDto> calculateProducerWinnerInterval(Map<String, List<Movie>> collect) {
		List<ProducerWinnerIntervalDto> listProducerWinnerWithInterval = new ArrayList<>();		
		for (Map.Entry<String, List<Movie>> entry : collect.entrySet()) {	        
			
			List<Movie> listAllMoviesByProducer = entry.getValue();			
			Collections.sort(listAllMoviesByProducer);
			
			Movie firstMovieProducer = null;	 
	        for (Movie currentMovie : listAllMoviesByProducer) {	        	
	        	if(firstMovieProducer == null) {
					firstMovieProducer = currentMovie;					
					continue;
				}				
				ProducerWinnerIntervalDto producerWinnerIntervalDto = new ProducerWinnerIntervalDto(currentMovie.getProducers(), firstMovieProducer.getYear(), currentMovie.getYear());
				listProducerWinnerWithInterval.add(producerWinnerIntervalDto);
				firstMovieProducer = currentMovie;
			}
	    }
		return listProducerWinnerWithInterval;
	}

	private List<Movie> extractProducersWinner(List<Movie> listProducersWinner) {		
		List<Movie> listExtractProducers = new ArrayList<Movie>();		
		for(Movie movie : listProducersWinner) {			
			String[] splitProducers = movie.getProducers().split(",| and ");
			if(splitProducers.length > 0) {
				for(String producer : splitProducers) {
					listExtractProducers.add(new Movie(movie.getYear(), movie.getTitle(), movie.getStudios(), producer.trim(), movie.getWinner()));
				}
			} else {
				listExtractProducers.add(new Movie(movie.getYear(), movie.getTitle(),movie.getStudios(), movie.getProducers(), movie.getWinner()));
			}						
		}
		return listExtractProducers;
	}	
}

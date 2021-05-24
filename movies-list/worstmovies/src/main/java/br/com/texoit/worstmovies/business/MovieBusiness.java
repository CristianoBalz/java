package br.com.texoit.worstmovies.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.texoit.worstmovies.controller.dto.MovieDto;
import br.com.texoit.worstmovies.controller.dto.ProducerWinnerDto;
import br.com.texoit.worstmovies.controller.dto.ProducerWinnerIntervalDto;
import br.com.texoit.worstmovies.model.Movie;
import br.com.texoit.worstmovies.model.WinnerEnum;
import br.com.texoit.worstmovies.repository.MovieRepository;


public class MovieBusiness {	
	
	private MovieRepository repository;
	
	public MovieBusiness(MovieRepository repository) {
		this.repository = repository;
	}
	
	public List<MovieDto> listAll() {
		return MovieDto.converter(repository.findAll());
	}
	
	public ProducerWinnerDto listProducersWinner() {
		
		List<Movie> listProducersWinner = getAllProducersWinner();
		
		listProducersWinner = extractProducersWinner(listProducersWinner);		
		
		Map<String, List<Movie>> moviesByProducer = extractMoviesByProducer(listProducersWinner);		
		
		List<ProducerWinnerIntervalDto> listProducersWinnerInterval = calculateProducerWinnerInterval(moviesByProducer);
		
		Map<Integer, List<ProducerWinnerIntervalDto>> producersWinnerByInterval = extractProducersWinnerByInterval(listProducersWinnerInterval);
			
		Optional<Entry<Integer, List<ProducerWinnerIntervalDto>>> minInterval = producersWinnerByInterval.entrySet().stream().findFirst();
		
		Optional<Entry<Integer, List<ProducerWinnerIntervalDto>>> maxInterval;
		if(producersWinnerByInterval.size() > 0) {
			maxInterval = producersWinnerByInterval.entrySet().stream().skip(producersWinnerByInterval.size() - 1).findFirst();
		} else {
			maxInterval = Optional.empty();
		}
		
		ProducerWinnerDto producerWinner = new ProducerWinnerDto();
		producerWinner.setMin(minInterval.isPresent() ? minInterval.get().getValue() : new ArrayList<ProducerWinnerIntervalDto>());
		producerWinner.setMax(maxInterval.isPresent() ? maxInterval.get().getValue() : new ArrayList<ProducerWinnerIntervalDto>());
		return producerWinner;
		
	}

	private Map<Integer, List<ProducerWinnerIntervalDto>> extractProducersWinnerByInterval(
			List<ProducerWinnerIntervalDto> listProducerWinnerWithInterval) {
		return listProducerWinnerWithInterval.stream().collect(Collectors.groupingBy((m) -> m.getInterval()));
	}

	private Map<String, List<Movie>> extractMoviesByProducer(List<Movie> listProducersWinner) {
		return listProducersWinner.stream()
				.collect(Collectors.groupingBy(m -> m.getProducers()));
	}

	private List<ProducerWinnerIntervalDto> calculateProducerWinnerInterval(Map<String, List<Movie>> collect) {
		List<ProducerWinnerIntervalDto> listProducerWinnerWithInterval = new ArrayList<>();		
		for (Map.Entry<String, List<Movie>> entry : collect.entrySet()) {
	        Movie movie = null;	 
	        for (Movie m : entry.getValue()) {	        	
	        	if(movie == null) {
					movie = m;					
					continue;
				}				
				ProducerWinnerIntervalDto p = new ProducerWinnerIntervalDto(m.getProducers(), movie.getYear(), m.getYear());
				listProducerWinnerWithInterval.add(p);
				movie = m;
			}
	    }
		return listProducerWinnerWithInterval;
	}

	private List<Movie> extractProducersWinner(List<Movie> listProducersWinner) {
		String[] splitProducers;
		List<Movie> listExtractProducers = new ArrayList<Movie>();
		for(Movie movie : listProducersWinner) {
			if(movie.getProducers().indexOf(",") != -1) {
				splitProducers = movie.getProducers().split(",");
				for(String producer : splitProducers) {
					if(producer.toLowerCase().trim().contains(" and ")) {
						splitProducers = producer.trim().split(" and ");
						for(String p : splitProducers) {
							listExtractProducers.add(new Movie(movie.getYear(), movie.getTitle(),movie.getStudios(), p.trim(), movie.getWinner()));
						}						
					} else {
						listExtractProducers.add(new Movie(movie.getYear(), movie.getTitle(), movie.getStudios(), producer.trim(), movie.getWinner()));
					}
				}
			} else if (movie.getProducers().toLowerCase().trim().contains(" and ")) {
				splitProducers = movie.getProducers().trim().split(" and ");
				listExtractProducers.add(new Movie(movie.getYear(), movie.getTitle(),movie.getStudios(), splitProducers[1].trim(), movie.getWinner()));
			} else {
				listExtractProducers.add(new Movie(movie.getYear(), movie.getTitle(),movie.getStudios(), movie.getProducers(), movie.getWinner()));
			}			
		}
		return listExtractProducers;
	}

	private List<Movie> getAllProducersWinner() {
		List<Movie> listProducersWinner = repository.findAll().stream()
				.filter(m -> m.getWinner() == WinnerEnum.YES)
				.collect(Collectors.toList());
		return listProducersWinner;
	}
}

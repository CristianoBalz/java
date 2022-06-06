package br.com.texoit.worstmovies.controller.dto;

import java.util.ArrayList;
import java.util.List;

public class ProducerWinnerDto {
	private List<ProducerWinnerIntervalDto> min = new ArrayList<ProducerWinnerIntervalDto>();
	private List<ProducerWinnerIntervalDto> max = new ArrayList<ProducerWinnerIntervalDto>();
	
	public final List<ProducerWinnerIntervalDto> getMin() {
		return min;
	}
	
	public final void setMin(List<ProducerWinnerIntervalDto> min) {
		this.min = min;
	}
	
	public final List<ProducerWinnerIntervalDto> getMax() {
		return max;
	}
	
	public final void setMax(List<ProducerWinnerIntervalDto> max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "ProducerWinnerDto [min=" + min + ", max=" + max + "]";
	}	
	
}

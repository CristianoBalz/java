package br.com.texoit.worstmovies.controller.dto;

public class ProducerWinnerIntervalDto implements Comparable<ProducerWinnerIntervalDto> {
	private String producer;
	private Integer interval;
	private Integer previousWin;
	private Integer followingWin;
	
	public ProducerWinnerIntervalDto(String producer, Integer previousWin, Integer followingWin) {
		this.producer = producer;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
		if(followingWin != null) {
			this.interval = followingWin - previousWin;
		}
	}	
	
	public final String getProducer() {
		return producer;
	}
	
	public final Integer getInterval() {
		return interval;
	}
	
	public final Integer getPreviousWin() {
		return previousWin;
	}
	
	public final Integer getFollowingWin() {
		return followingWin;
	}
	

	@Override
	public String toString() {
		return "ProducerIntervalDto [producer=" + producer + ", interval=" + interval + ", previousWin=" + previousWin
				+ ", followingWin=" + followingWin + "]";
	}

	@Override
	public int compareTo(ProducerWinnerIntervalDto o) {	
		return o.getInterval() <= getInterval() ? -1 : 1;
	}	
	
}

package br.com.texoit.worstmovies.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Entity @IdClass(MovieId.class)
public class Movie implements Comparable<Movie> {	
	@Id private int year;
	@Id private String title;
	@Column(name="studios") private String studio;
	@Column(name="producers") private String producer;
	@Basic private String winner;	
    @Transient private WinnerEnum winnerEnum;
    
    public Movie() {}
    
    public Movie(int year, String title, String studio, String producer, WinnerEnum winner) {
    	this.year = year;
    	this.title = title;
    	this.studio = studio;
    	this.producer = producer;
    	this.winnerEnum = winner;
    }

    @PostLoad
    void fillTransient() {
         this.winnerEnum = WinnerEnum.of(winner != null ? winner : "");
    }

    @PrePersist
    void fillPersistent() {
        if (winnerEnum != null) {
            this.winner = winnerEnum.getValue();
        }
    }		
	
	public int getYear() {
		return year;
	}	

	public String getTitle() {
		return title;
	}
	
	public String getStudios() {
		return studio;
	}

	public String getProducers() {
		return producer;
	}
		
	public WinnerEnum getWinner() {
		return winnerEnum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Movie [year=" + year + ", title=" + title + ", studio=" + studio + ", producer=" + producer + ", winner=" + winner + "]";
	}

	@Override
	public int compareTo(Movie o) {
		return Integer.compare(this.year, o.getYear());
	}		
	
}

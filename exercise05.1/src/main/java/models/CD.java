package models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CD")
public class CD extends Product{
	
	private String artist;
	
	

	public CD() {
	}



	public String getArtist() {
		return artist;
	}



	public void setArtist(String artist) {
		this.artist = artist;
	}



	public CD(String artist) {
		this.artist = artist;
	}
	
	

}

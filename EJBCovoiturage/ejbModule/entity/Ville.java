package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ville {
	
	@Id	@GeneratedValue
	private int id;
	
	private String ville;

	public Ville() {	
	}
	
	public Ville(String maVille) {
		this.ville=maVille;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}
	
	
	

}

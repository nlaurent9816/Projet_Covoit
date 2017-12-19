package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity @SequenceGenerator(name = "port_gen", sequenceName = "port_gen",  initialValue = 1000)
public class Ville {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "port_gen")
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

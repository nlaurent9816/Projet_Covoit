package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Etape {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	private Ville ville;
	

	private Float tarif;
	
	public Etape() {
	}
	
	public Etape(Ville v, Float t) {
		this.ville=v;
		this.tarif=t;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}

	public Float getTarif() {
		return tarif;
	}

	public void setTarif(Float tarif) {
		this.tarif = tarif;
	}
	
	
	
	
}

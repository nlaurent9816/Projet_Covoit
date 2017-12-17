package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Etape {

	@Id @GeneratedValue
	private int id;
	
	@ManyToOne
	private Ville ville;
	
	@OneToOne
	private Tarif tarif;
	
	public Etape() {
	}
	
	public Etape(Ville v, Tarif t) {
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

	public Tarif getTarif() {
		return tarif;
	}

	public void setTarif(Tarif tarif) {
		this.tarif = tarif;
	}
	
	
	
	
}

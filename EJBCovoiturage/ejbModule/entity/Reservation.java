package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reservation {
	
	@Id @GeneratedValue
	private int idReservation;
	
	//private InfoUtilisateur passager;
	
	//private Trajet leTrajet;
	
	private int nombrePlace;
	
	//private Ville arrivee;
	
	private String statut; //approuvé, non approuvé, en attente

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	/*public InfoUtilisateur getPassager() {
		return passager;
	}

	public void setPassager(InfoUtilisateur passager) {
		this.passager = passager;
	}

	public Trajet getLeTrajet() {
		return leTrajet;
	}

	public void setLeTrajet(Trajet leTrajet) {
		this.leTrajet = leTrajet;
	}*/

	public int getNombrePlace() {
		return nombrePlace;
	}

	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}

	/*public Ville getArrivee() {
		return arrivee;
	}

	public void setArrivee(Ville arrivee) {
		this.arrivee = arrivee;
	}*/

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	


}

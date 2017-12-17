package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {
	
	@Id @GeneratedValue
	private int idReservation;
	
	@ManyToOne
	private InfoUtilisateur passager;//Un utilisateur peut avoir plusieurs réservations BD
	
	@ManyToOne
	private Trajet leTrajet; //Il peut y avoir plusieurs réservations pour le même trajet (BD)
	
	private int nombrePlace;
	
	@ManyToOne 
	private Ville arrivee; //une ville d'arrivée peut être dans plusieurs réservations (MD)
	
	private String statut; //approuvé, non approuvé, en attente

	public Reservation() {
		
	}
	
	public Reservation(Ville arrivee, Trajet trajet, int nbPlaces, InfoUtilisateur passager, String statut) {
		this.arrivee=arrivee;
		this.leTrajet=trajet;
		this.nombrePlace=nbPlaces;
		this.passager=passager;
		this.statut=statut;
	}
	
	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public InfoUtilisateur getPassager() {
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
	}

	public int getNombrePlace() {
		return nombrePlace;
	}

	public void setNombrePlace(int nombrePlace) {
		this.nombrePlace = nombrePlace;
	}

	public Ville getArrivee() {
		return arrivee;
	}

	public void setArrivee(Ville arrivee) {
		this.arrivee = arrivee;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	
	


}

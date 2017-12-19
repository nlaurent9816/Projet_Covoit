package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity 
public class Reservation {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idReservation;
	
	@ManyToOne
	private InfoUtilisateur passager;//Un utilisateur peut avoir plusieurs réservations BD
	
	@ManyToOne
	private Trajet leTrajet; //Il peut y avoir plusieurs réservations pour le même trajet (BD)
	
	private int nombrePlace;
	
	@ManyToOne
	private Etape etapeArrivee;//une même étape peut être réservé par plusieurs personnes

	private String statut; //approuvé, non approuvé, en attente

	public Reservation() {
		
	}
	
	public Reservation(Trajet trajet, int nbPlaces, InfoUtilisateur passager, String statut, Etape etapeArrivee) {
		this.leTrajet=trajet;
		this.nombrePlace=nbPlaces;
		this.passager=passager;
		this.statut=statut;
		this.etapeArrivee=etapeArrivee;
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public Etape getEtapeArrivee() {
		return etapeArrivee;
	}

	public void setEtapeArrivee(Etape etapeArrivee) {
		this.etapeArrivee = etapeArrivee;
	}
	
	


}

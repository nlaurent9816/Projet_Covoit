package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Trajet {
	
	@Id @GeneratedValue
	private int idTrajet;
	
	@ManyToOne
	private InfoUtilisateur conducteur;//Un utilisateur peut avoir plusieurs trajets prpoposés
	
	@OneToMany(mappedBy="leTrajet")//Il peut y avoir plusieurs réservations pour le même trajet (BD) 
	private List<Reservation> passagers;
	
	@ManyToOne
	private Vehicule typeVehicule;
	
	private String monVehicule;
	
	private String dateDepart; //garder ce type et faire une méthode pour parser chaine ou autre type plus convenable ?
	
	private String heureDepart;//idem que date

	@ManyToOne
	private Ville villeDepart;
	
	@ManyToOne
	private Ville villeArrivee;
	
	@ManyToMany
	@JoinTable(
			name="EtapesTrajet", 
			joinColumns=
				@JoinColumn(name="id_trajet"),
			inverseJoinColumns=
				@JoinColumn(name="id_etape"))
	private List<Ville> lesEtapes;  // n étapes 
	
	//private List<Float> lesTarifs; // n+1 tarifs le premier étant le tarif depuis la ville de départ
	
	private int nombrePlaces;

	public int getIdTrajet() {
		return idTrajet;
	}

	public void setIdTrajet(int id) {
		this.idTrajet = id;
	}

	public InfoUtilisateur getConducteur() {
		return conducteur;
	}

	public void setConducteur(InfoUtilisateur conducteur) {
		this.conducteur = conducteur;
	}

	public Vehicule getTypeVehicule() {
		return typeVehicule;
	}

	public void setTypeVehicule(Vehicule typeVehicule) {
		this.typeVehicule = typeVehicule;
	}

	public String getMonVehicule() {
		return monVehicule;
	}

	public void setMonVehicule(String monVehicule) {
		this.monVehicule = monVehicule;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}

	public String getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
	}

	public Ville getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(Ville villeDepart) {
		this.villeDepart = villeDepart;
	}

	public Ville getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(Ville villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public List<Ville> getLesEtapes() {
		return lesEtapes;
	}

	public void setLesEtapes(List<Ville> lesEtapes) {
		this.lesEtapes = lesEtapes;
	}

	/*public List<Float> getLesTarifs() {
		return lesTarifs;
	}

	public void setLesTarifs(List<Float> lesTarifs) {
		this.lesTarifs = lesTarifs;
	}*/

	public int getNombrePlaces() {
		return nombrePlaces;
	}

	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}


}

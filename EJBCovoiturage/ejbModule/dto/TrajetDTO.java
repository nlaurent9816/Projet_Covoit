package dto;

import java.util.List;

public class TrajetDTO {
	private String conducteurNom;
	private String conducteurPrenom;
	private String dateDepart;
	private String heureDepart;
	private String gabaritVehicule;
	private int nombrePlaces; //plutôt places restantes 
	private String descriptionVehicule;
	private String villeDepart;
	private List<String> etapes;
	private List<Float> tarifs;
	private int idTrajet;
	
	public TrajetDTO(String conducteurNom, String conducteurPrenom, String dateDepart, String heureDepart, String gabaritVehicule, int nombrePlaces, String descriptionVehicule, String villeDepart, List<String> etapes, List<Float> tarifs, int idTrajet) {
		this.conducteurNom=conducteurNom;
		this.conducteurPrenom=conducteurPrenom;
		this.dateDepart=dateDepart;
		this.heureDepart=heureDepart;
		this.gabaritVehicule=gabaritVehicule;
		this.nombrePlaces=nombrePlaces;
		this.descriptionVehicule=descriptionVehicule;
		this.villeDepart=villeDepart;
		this.etapes=etapes;
		this.tarifs=tarifs;
		this.setIdTrajet(idTrajet);
	}

	
	public String getConducteurNom() {
		return conducteurNom;
	}
	public void setConducteurNom(String conducteurNom) {
		this.conducteurNom = conducteurNom;
	}
	public String getConducteurPrenom() {
		return conducteurPrenom;
	}
	public void setConducteurPrenom(String conducteurPrenom) {
		this.conducteurPrenom = conducteurPrenom;
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
	public String getGabaritVehicule() {
		return gabaritVehicule;
	}
	public void setGabaritVehicule(String gabaritVehicule) {
		this.gabaritVehicule = gabaritVehicule;
	}
	public int getNombrePlaces() {
		return nombrePlaces;
	}
	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}
	public String getDescriptionVehicule() {
		return descriptionVehicule;
	}
	public void setDescriptionVehicule(String descriptionVehicule) {
		this.descriptionVehicule = descriptionVehicule;
	}
	public String getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}
	public List<String> getEtapes() {
		return etapes;
	}
	public void setEtapes(List<String> etapes) {
		this.etapes = etapes;
	}
	public List<Float> getTarifs() {
		return tarifs;
	}
	public void setTarifs(List<Float> tarifs) {
		this.tarifs = tarifs;
	}


	public int getIdTrajet() {
		return idTrajet;
	}


	public void setIdTrajet(int idTrajet) {
		this.idTrajet = idTrajet;
	}
	
	
}

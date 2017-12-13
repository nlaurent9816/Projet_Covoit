package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class InfoUtilisateur {
	
	@Id @GeneratedValue
	private int idInfo;
	

	
	private String nom;
	
	private String prenom;
	
	private String sexe;//ou int et si 0 alors femme et 1 homme
	
	private String tel;
	
	private String mail;
	
	//plus d'autres infos si besoin
	
	@OneToMany(mappedBy="conducteur")
	private List<Trajet> mesTrajetsProposés;//Un utilisateur peut avoir plusieurs trajets proposés (BD)
	
	@OneToMany(mappedBy="passager")
	private List<Reservation> mesTrajetsRéservés;//un utilisateur peut avoir plusieurs réservations (BD)
	
	public InfoUtilisateur(String newNom, String newPrenom, String newSexe, String newTel, String newMail){
		this.nom=newNom;
		this.prenom=newPrenom;
		this.sexe=newSexe;
		this.tel=newTel;
		this.mail=newMail;
	}
	
	public InfoUtilisateur(){	
	}
	
	public int getIdInfo() {
		return idInfo;
	}

	public void setIdInfo(int id) {
		this.idInfo = id;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	

	public List<Trajet> getMesTrajetsProposés() {
		return mesTrajetsProposés;
	}

	public void setMesTrajetsProposés(List<Trajet> mesTrajetsProposés) {
		this.mesTrajetsProposés = mesTrajetsProposés;
	}

	public List<Reservation> getMesTrajetsRéservés() {
		return mesTrajetsRéservés;
	}

	public void setMesTrajetsRéservés(List<Reservation> mesTrajetsRéservés) {
		this.mesTrajetsRéservés = mesTrajetsRéservés;
	}

	

		 
		 
}

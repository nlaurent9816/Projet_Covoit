package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Utilisateur {
	
	@Id @GeneratedValue
	private int id;
	
	private String login;
	
	private String password;// à hasher
	
	private String nom;
	
	private String prenom;
	
	private String sexe;//ou int et si 0 alors femme et 1 homme
	
	private String tel;
	
	private String mail;
	
	//plus d'autres infos si besoin
	
	
	//private List<Trajet> mesTrajetsProposés;
	
	//private List<Trajet> mesTrajetsRéservés;
	
	

		 
		 
}

package ejbs;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.InfoUtilisateur;
import entity.Login;
import entity.Vehicule;
import entity.Ville;

/**
 * Session Bean implementation class Facade
 */
@Stateless
@LocalBean
public class Facade {
	
	@PersistenceContext(unitName="monUnite")
	EntityManager em;
	
	public void enregistrerUtilisateur(String nom, String prenom, String sexe, String tel, String email, String login, String password) throws NoSuchAlgorithmException {
		//on enregistre des infos de l'utilisateur
		InfoUtilisateur iu = new InfoUtilisateur(nom, prenom, sexe, tel, email);
		em.persist(iu);
		//on hash le mdp
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
		StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<encodedhash.length;i++) {
    	  hexString.append(Integer.toHexString(0xFF & encodedhash[i]));
    	}
		String passwordHash = hexString.toString();
		System.out.println("passwordHash : "+ passwordHash);
		//on rajoute le login, mdp et les info-utilisateurs dans les données persistantes
			Login l = new Login(login, passwordHash, iu);
			em.persist(l);
	}
	
	public boolean checkUtilisateur(String login, String password) throws NoSuchAlgorithmException {
		
		//on hash le mot de passe
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
	    StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<encodedhash.length;i++) {
    	  hexString.append(Integer.toHexString(0xFF & encodedhash[i]));
    	}
		String passwordHash = hexString.toString();
		Query q = em.createQuery("From Login l where l.login=:log AND l.password=:pass");
		q.setParameter("log", login);
		q.setParameter("pass", passwordHash);
		return q.getResultList().size()==1;
		
	}
	
	public List<String> getNameVille(){
		Query q = em.createQuery("From Ville");
		List<Ville> listObject = q.getResultList();
		List<String> listVilles = new ArrayList<String>();
		for (Ville v : listObject) {
			listVilles.add(v.getVille());
		}
		return listVilles;
	}
	
	public List<String> getNameVehicule(){
		Query q = em.createQuery("From Vehicule");
		List<Vehicule> listObject = q.getResultList();
		List<String> listVehicules = new ArrayList<String>();
		for (Vehicule v : listObject) {
			listVehicules.add(v.getGabaritVehicule());
		}
		return listVehicules;
	}

}

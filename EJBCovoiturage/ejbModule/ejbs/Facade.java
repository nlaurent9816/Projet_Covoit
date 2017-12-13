package ejbs;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.InfoUtilisateur;
import entity.Login;

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
		String passwordHash = new String(encodedhash);
		System.out.println("passwordHash : "+ passwordHash);
		//on rajoute le login, mdp et les info-utilisateurs dans les données persistantes
			Login l = new Login(login, passwordHash, iu);
			em.persist(l);
	}


}

package ejbs;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dto.TrajetDTO;
import entity.Etape;
import entity.InfoUtilisateur;
import entity.Login;
import entity.Tarif;
import entity.Trajet;
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
	
	public List<TrajetDTO> getTrajets(String ville_depart, String ville_arrivee){
		//get Ville départ
		Query q = em.createQuery("FROM Ville v WHERE v.ville=:maVille");
		q.setParameter("maVille", ville_depart);
		Ville villeDepart = (Ville) q.getSingleResult();
				
		//get Ville Arrivée
		q = em.createQuery("FROM Ville v WHERE v.ville=:maVille");
		q.setParameter("maVille", ville_arrivee);
		Ville villeArrivee = (Ville) q.getSingleResult();
		
		Date d=new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormat.format(d);
		System.out.println(date);
		
		q = em.createQuery("From Trajet t WHERE t.villeDepart= :ville_depart" ); //AND t.dateDepart >= :date_depart
		q.setParameter("ville_depart", villeDepart);
		//q.setParameter("date_depart", date);
		List<Trajet> trajetsPotentiels = (List<Trajet>) q.getResultList();
		System.out.println("Nombre de trajets donnés par requête :" + trajetsPotentiels.size());
		List<Trajet> trajetsRecherchesTemp = new ArrayList<Trajet>();
		List<TrajetDTO> trajetsRecherches = new ArrayList<TrajetDTO>();
		for (Trajet t : trajetsPotentiels) {
			List<Etape> etapes = t.getLesEtapes();
			for (Etape e : etapes) {
				if(e.getVille().equals(villeArrivee)) {
					//on vient de trouver un trajet qui nous intéresse
					trajetsRecherchesTemp.add(t);
				}
			}
		}
		
		//on remplit notre liste de trajet DTO que l'on va renvoyer
		for (Trajet t : trajetsRecherchesTemp) {
			List<Float> tarifs =new ArrayList<Float>();
			List<String> etapeString = new ArrayList<String>();
			List<Etape> etapes = t.getLesEtapes();
			for (Etape e : etapes) {
				etapeString.add(e.getVille().getVille());
				tarifs.add(e.getTarif().getValeur());
				
			}
			TrajetDTO t_dto = new TrajetDTO(t.getConducteur().getNom(), t.getConducteur().getPrenom(), t.getDateDepart(), t.getHeureDepart(), t.getTypeVehicule().getGabaritVehicule(), t.getNombrePlaces(), t.getMonVehicule(), t.getVilleDepart().getVille(), etapeString, tarifs);
			trajetsRecherches.add(t_dto);
		}
		
		return trajetsRecherches;
	}
	
	public void enregistrerTrajet(String conducteur, String vehicule_desc, String vehicule_gabarit, String date_trajet, String heure_trajet, String ville_depart, String ville_arrivee, String tarif_trajet, String[] etapes_trajet, String[] tarifs_etapes, String place_trajet) {
		
		//construire la liste des étapes
		
		List<Etape> etapes = new ArrayList<Etape>();
		Query q;
		if(tarifs_etapes.length>0) {
			for(int i =0;i<tarifs_etapes.length;i++) {
				Tarif t = new Tarif(Float.parseFloat(tarifs_etapes[i]));
				em.persist(t);
				q = em.createQuery("FROM Ville v WHERE v.ville=:maVille");
				q.setParameter("maVille", etapes_trajet[i]);
				Ville v = (Ville) q.getSingleResult();
				Etape e = new Etape(v, t);
				em.persist(e);
				etapes.add(e);
			}
		}
		
		Tarif tarifTrajet = new Tarif(Float.parseFloat(tarif_trajet));
		em.persist(tarifTrajet);
		
		//get Ville Arrivée
		q = em.createQuery("FROM Ville v WHERE v.ville=:maVille");
		q.setParameter("maVille", ville_arrivee);
		Ville villeArrivee = (Ville) q.getSingleResult();
		
		Etape arrivee = new Etape(villeArrivee, tarifTrajet);
		em.persist(arrivee);
		etapes.add(arrivee);
		//get infoUtilisateur conducteur
		q = em.createQuery("FROM Login login WHERE login.login= :monLogin");
		q.setParameter("monLogin", conducteur);
		Login log_conducteur = (Login) q.getSingleResult();
		
		//get vehicule
		q=em.createQuery("FROM Vehicule v WHERE v.gabaritVehicule= :gabarit");
		q.setParameter("gabarit", vehicule_gabarit);
		Vehicule gabarit_vehicule = (Vehicule) q.getSingleResult();
		
		//get Ville départ
		q = em.createQuery("FROM Ville v WHERE v.ville=:maVille");
		q.setParameter("maVille", ville_depart);
		Ville villeDepart = (Ville) q.getSingleResult();
		
		
		Trajet monTrajet = new Trajet(log_conducteur.getInfos(), gabarit_vehicule, vehicule_desc, date_trajet, heure_trajet, villeDepart, etapes, Integer.parseInt(place_trajet));
		em.persist(monTrajet);
	}
	
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

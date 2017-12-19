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

import entity.Etape;
import entity.InfoUtilisateur;
import entity.Login;
import entity.Reservation;
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
	
	public List<Trajet> getTrajetConducteur(String loginConducteur){
		Query q = em.createQuery("SELECT t FROM Trajet t, InfoUtilisateur info, Login l WHERE l.login=:loginConducteur AND  l.infos=info AND t.conducteur=info");
		q.setParameter("loginConducteur", loginConducteur);
		List<Trajet> mesTrajets = (List<Trajet>) q.getResultList();
		return mesTrajets;
	}
	
	public List<Reservation> getResAValide(int idTrajet){
		Query q = em.createQuery("SELECT t.passagers FROM Trajet t WHERE t.idTrajet= :idTrajet");
		q.setParameter("idTrajet", idTrajet);
		List<Reservation> resAValideTemp = (List<Reservation>) q.getResultList();
		List<Reservation> resAValide = new ArrayList<Reservation>();
		for (Reservation r : resAValideTemp) {
			if(r.getStatut().equals("A confirmer")) {
				resAValide.add(r);
			}
		}
		return resAValide;	
	}
	
	public List<Reservation> getResValide(int idTrajet){
		Query q = em.createQuery("SELECT t.passagers FROM Trajet t WHERE t.idTrajet= :idTrajet");
		q.setParameter("idTrajet", idTrajet);
		List<Reservation> resValideTemp = (List<Reservation>) q.getResultList();
		List<Reservation> resValide = new ArrayList<Reservation>();
		for (Reservation r : resValideTemp) {
			if(r.getStatut().equals("Valide")) {
				resValide.add(r);
			}
		}
		return resValide;	
	}
	
	public void annulerReservation(int idRes) {
		//on modifie le nombres de places restantes pour le trajet correspondant
		Reservation maRes = em.find(Reservation.class, idRes);
		Query q = em.createQuery("UPDATE Trajet t SET t.nombrePlacesRestantes = t.nombrePlacesRestantes + :nbReserves WHERE t.idTrajet = :idTrajet");
		q.setParameter("nbReserves", maRes.getNombrePlace());
		q.setParameter("idTrajet", maRes.getLeTrajet().getIdTrajet());
		q.executeUpdate();
		em.remove(maRes);
		
	}
	
	public void supprimerTrajet(int idTrajet) {
		
		Trajet leTrajet=em.find(Trajet.class, idTrajet);
		//supprimer les réservations correspondantes
		Query q = em.createQuery("FROM Reservation r WHERE r.leTrajet= :trajet");
		q.setParameter("trajet", leTrajet);
		List<Reservation> lesRes = q.getResultList();
		for(Reservation r : lesRes) {
			em.remove(r);
		}
		
		//Supprimer les étapes correspondantes
		//on récupère les étapes à supprimer
		q=em.createQuery("SELECT t.lesEtapes FROM Trajet t WHERE t= :trajet");
		q.setParameter("trajet", leTrajet);
		List<Etape> lesEtapes = (List<Etape>) q.getResultList();
		for (Etape e : lesEtapes) {
			em.remove(e);
		}
		//Supprimer le trajet en lui-même
		em.remove(leTrajet);
	}
	
	public List<Reservation> getReservations(String currentLogin){
		Query q = em.createQuery("SELECT r FROM Reservation r, InfoUtilisateur info, Login l WHERE l.login=:loginConducteur AND  l.infos=info AND info=r.passager");
		q.setParameter("loginConducteur", currentLogin);
		List<Reservation> mesReservations = (List<Reservation>) q.getResultList();
		System.out.println("Nombre de reserv retournées : " + mesReservations.size());
		return mesReservations;
	}
	public List<Trajet> getTrajets(String ville_depart, String ville_arrivee){
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
		List<Trajet> trajetsRecherches = new ArrayList<Trajet>();

		for (Trajet t : trajetsPotentiels) {
			List<Etape> etapes = t.getLesEtapes();
			for (Etape e : etapes) {
				if(e.getVille().equals(villeArrivee)) {
					//on vient de trouver un trajet qui nous intéresse
					trajetsRecherches.add(t);
				}
			}
		}
		
		return trajetsRecherches;
	}
	
	
	public void enregistrerTrajet(String conducteur, String vehicule_desc, String vehicule_gabarit, String date_trajet, String heure_trajet, String ville_depart, String ville_arrivee, String tarif_trajet, String[] etapes_trajet, String[] tarifs_etapes, String place_trajet) {
		
		//construire la liste des étapes
		
		List<Etape> etapes = new ArrayList<Etape>();
		Query q;
		if(tarifs_etapes!=null) {
			if(tarifs_etapes.length>0) {
				for(int i =0;i<tarifs_etapes.length;i++) {
					q = em.createQuery("FROM Ville v WHERE v.ville=:maVille");
					q.setParameter("maVille", etapes_trajet[i]);
					Ville v = (Ville) q.getSingleResult();
					Etape e = new Etape(v, Float.parseFloat(tarifs_etapes[i]));
					em.persist(e);
					etapes.add(e);
				}
			}
		}
		//get Ville Arrivée
		q = em.createQuery("FROM Ville v WHERE v.ville=:maVille");
		q.setParameter("maVille", ville_arrivee);
		Ville villeArrivee = (Ville) q.getSingleResult();
		
		Etape arrivee = new Etape(villeArrivee, Float.parseFloat(tarif_trajet));
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
		Query q = em.createQuery("From Ville v ORDER BY v.ville");
		List<Ville> listObject = q.getResultList();
		List<String> listVilles = new ArrayList<String>();
		for (Ville v : listObject) {
			listVilles.add(v.getVille());
		}
		return listVilles;
	}
	
	public List<String> getNameVehicule(){
		Query q = em.createQuery("From Vehicule v ORDER BY v.gabaritVehicule");
		List<Vehicule> listObject = (List<Vehicule>) q.getResultList();
		List<String> listVehicules = new ArrayList<String>();
		for (Vehicule v : listObject) {
			listVehicules.add(v.getGabaritVehicule());
		}
		return listVehicules;
	}
	
	public void reserverTrajet(int idTrajet, int nbPlaces, String loginPassager, int idEtapeArrivee) {
		//on récupère l'objet Trajet
		System.out.println(idEtapeArrivee);
		Trajet trajet = em.find(Trajet.class, idTrajet);
		//on récupère les infos du passagers 
		Login passagerLogin = em.find(Login.class, loginPassager);
		Etape etapeArrivee = em.find(Etape.class, idEtapeArrivee);
		if(etapeArrivee==null)
			System.out.println("probleme niveau arrivee");
		if(passagerLogin==null)
			System.out.println("probleme niveau login");
		if(passagerLogin.getInfos()==null)
			System.out.println("probleme niveau infos passager");
		if(trajet==null)
			System.out.println("probleme niveau trajet");
		Reservation maReservation = new Reservation(trajet, nbPlaces, passagerLogin.getInfos(), "A confirmer", etapeArrivee);
		em.persist(maReservation);
		
		//modifier aussi le nombre de places restantes dans le trajet
		Query q = em.createQuery("UPDATE Trajet t SET t.nombrePlacesRestantes = t.nombrePlacesRestantes - :nbReserves WHERE t.idTrajet = :idTrajet");
		q.setParameter("nbReserves", nbPlaces);
		q.setParameter("idTrajet", idTrajet);
		q.executeUpdate();
	}
	
	public void confirmerReservation(int idReservation){
		Query q = em.createQuery("UPDATE Reservation r SET r.statut = 'Valide' WHERE r.idReservation = :idRes");
		q.setParameter("idRes", idReservation);
		q.executeUpdate();
		
	}
	
	public boolean isAdmin(String currentLogin) {
		Login user = em.find(Login.class, currentLogin);
		return (user.getRole()==0);
	}
	
	public void ajoutVille(String ville){
		Ville maVille = new Ville(ville);
		em.persist(maVille);
	}
	
	public void ajoutVehicule(String vehicule){
		Vehicule monVehicule = new Vehicule(vehicule);
		em.persist(monVehicule);
	}
}

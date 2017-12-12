<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enregistrement</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>

<nav class="menu">
<form method="post">
   <ul>
  	<li><button type = "submit" name ="Nav" value="accueil">Accueil</button></li><!--visible pour tout le monde, ramène sur la page d'accueil  -->
  	<!--<li><a href="#Apropos">Se connecter</a></li>visible que si non connecté, mène vers page de connexion  -->
    <li><button type = "submit" name ="Nav" value="register">S'enregistrer</button></li><!--visible que si non connecté, mène vers page d'enregistrement  -->
    <li><button type = "submit" name ="Nav" value="compte">Votre Compte</button></li><!--visible que si connecté, contient liste des réservations, des trajets proposés  -->
    <li><button type = "submit" name ="Nav" value="recherche">Trouver un trajet</button></li><!--toujours visible, mène vers page de recherche avancée  -->
    <li><button type = "submit" name ="Nav" value="ajout">Ajouter une ville/voiture</button></li><!--Visible pour l'administrateur seulement  -->
  </ul>
</form>
</nav>

<form id="register" method="post">
		<label>Login : </label><input type ="text" name ="login"/>
		<br/>
		<label>Mot de Passe : </label><input type ="password" name ="mdp"/>
		<br/>
		<label>Entrez de nouveau votre mot de passe : </label><input type ="password" name ="mdpbis"/>
		<br/>
		<label>Nom : </label><input type ="text" name ="nom"/>
		<br/>
		<label>Prénom : </label><input type ="text" name ="prenom"/>
		<br/>
		<label>Sexe : </label>
			<input type ="radio" name ="sexe" value="homme"/><label>Homme</label>
			<input type ="radio" name ="sexe" value="femme"/><label>Femme</label>
		<br/>
		<label>Numéro de téléphone : </label><input type ="text" name ="tel"/>
		<br/>
		<label>Email : </label><input type ="text" name ="email"/>
		<br/>
		
		<button type = "submit" name ="todo" value="register">S'enregistrer</button>
	</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Covoiturage</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<!-- En tête -->
<form method="post" class="menu">
  <ul>
  	<li><button type = "submit" name ="Nav" value="accueil">Accueil</button></li><!--visible pour tout le monde, ramène sur la page d'accueil  -->
  	<!--<li><a href="#Apropos">Se connecter</a></li>visible que si non connecté, mène vers page de connexion  -->
    <li><button type = "submit" name ="Nav" value="register">S'enregistrer</button></li><!--visible que si non connecté, mène vers page d'enregistrement  -->
    <li><button type = "submit" name ="Nav" value="compte">Votre Compte</button></li><!--visible que si connecté, contient liste des réservations, des trajets proposés  -->
    <li><button type = "submit" name ="Nav" value="recherche">Trouver un trajet</button></li><!--toujours visible, mène vers page de recherche avancée  -->
    <li><button type = "submit" name ="Nav" value="ajout">Ajouter ville/voiture</button></li><!--Visible pour l'administrateur seulement  -->
  </ul>
</form>

<!-- Champ de recherche -->
<div style="height:500px; width:100%; background-color: #b3e6ff; display: table;">

<div  align="center" style="display: table-cell; vertical-align: middle;">

<!--div id=connexion>
	Connectez-vous :
	<form method="post">
		Login :<input type ="text" name ="login"/>
		<br/>
		MDP : <input type ="password" name ="mdp"/>
		<button type = "submit" name ="todo" value="connect">Connexion</button>
	</form>
</div-->
<h1 style="font-size: 60px; margin-bottom: 20px;">Covoiturage !</h1>
<div  style="font-size: 30px; display: table-cell; text-align: center;">
De
<input type="text" placeholder="Départ" style=" padding:5px; font-size: inherit;" >
Vers
<input type="text" placeholder="Arrivée" style=" padding:5px; font-size: inherit;" >
</div>
<button style="font-size: 20px; display: table-cell; text-align: center; margin: 10px; padding: 5px;">Rechercher !</button>
</div>
</div>

<!-- Pied de page -->

<div align="right" class="bottom">
	Site réalisé par Manuel Ory et Nicolas Laurent - 2017
</div>
</body>
</html>
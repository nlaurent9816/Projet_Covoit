<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Covoiturage</title>
</head>
<body>
<nav class="menu">
  <ul>
  	<li><a href="#Apropos">Accueil</a></li><!--visible pour tout le monde, ramène sur la page d'accueil  -->
  	<li><a href="#Apropos">Se connecter</a></li><!--visible que si non connecté, mène vers page de connexion  -->
    <li><a href="#Contact">S'enregistrer</a></li><!--visible que si non connecté, mène vers page d'enregistrement  -->
    <li><a href="#Accueil">Votre compte</a></li><!--visible que si connecté, contient liste des réservations, des trajets proposés  -->
    <li><a href="#Apropos">Trouver un trajet</a></li><!--toujours visible, mène vers page de recherche avancée  -->
    <li><a href="#Contact">Ajouter ville/voiture</a></li><!--Visible pour l'administrateur seulement  -->
  </ul>
</nav>
<div style="height:500px; width:100%; background-color: #b3e6ff; display: table; border-top-left-radius: 12px; border-top-right-radius: 12px;">
<div align="center" style="display: table-cell; vertical-align: middle;">
<div id=connexion>
	Connectez-vous :
	<form method="post">
		Login :<input type ="text" name ="login"/>
		<br/>
		MDP : <input type ="password" name ="mdp"/>
		<button type = "submit" name ="todo" value="connect">Connexion</button>
	</form>
</div>
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
<div align="right" style="padding: 10px; background-color: #f2f2f2;color:gray; border-bottom-left-radius: 12px; border-bottom-right-radius: 12px;">
	Site réalisé par Manuel Ory et Nicolas Laurent - 2017
</div>
</body>
</html>
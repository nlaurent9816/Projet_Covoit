<!-- En tête -->
<form method="post" class="menu">
  <ul>
  	<li><button type = "submit" name ="Nav" value="accueil">Accueil</button></li><!--visible pour tout le monde, ramène sur la page d'accueil  -->
  	<!--<li><a href="#Apropos">Se connecter</a></li>visible que si non connecté, mène vers page de connexion  -->
    <c:if test = "${connecte ne 'true'}"><li><button type = "submit" name ="Nav" value="register">Se connecter</button></li></c:if><!--visible que si non connecté, mène vers page d'enregistrement  -->
    <c:if test = "${connecte eq 'true'}"><li><button type = "submit" name ="Nav" value="compte">Votre Compte</button></li></c:if><!--visible que si connecté, contient liste des réservations, des trajets proposés  -->
    <li><button type = "submit" name ="Nav" value="recherche">Trouver un trajet</button></li><!--toujours visible, mène vers page de recherche avancée  -->
    <c:if test = "${connecte eq 'true'}"><li><button type = "submit" name ="Nav" value="ajoutTrajet">Proposer un trajet</button></li></c:if>
    <c:if test = "${connecte eq 'true'}"><li><button type = "submit" name ="Nav" value="deconnexion">Se déconnecter</button></li></c:if>
   	<c:if test = "${role eq 'admin' }"> <li><button type = "submit" name ="Nav" value="ajout">Ajouter ville/voiture</button></li></c:if><!--Visible pour l'administrateur seulement  -->
  </ul>
</form>
<!-- En t�te -->
<form method="post" class="menu">
  <ul>
  	<li><button type = "submit" name ="Nav" value="accueil">Accueil</button></li><!--visible pour tout le monde, ram�ne sur la page d'accueil  -->
  	<!--<li><a href="#Apropos">Se connecter</a></li>visible que si non connect�, m�ne vers page de connexion  -->
    <c:if test = "${connecte ne 'true'}"><li><button type = "submit" name ="Nav" value="register">Se connecter</button></li></c:if><!--visible que si non connect�, m�ne vers page d'enregistrement  -->
    <c:if test = "${connecte eq 'true'}"><li><button type = "submit" name ="Nav" value="compte">Votre Compte</button></li></c:if><!--visible que si connect�, contient liste des r�servations, des trajets propos�s  -->
    <li><button type = "submit" name ="Nav" value="recherche">Trouver un trajet</button></li><!--toujours visible, m�ne vers page de recherche avanc�e  -->
    <c:if test = "${connecte eq 'true'}"><li><button type = "submit" name ="Nav" value="ajoutTrajet">Proposer un trajet</button></li></c:if>
    <c:if test = "${connecte eq 'true'}"><li><button type = "submit" name ="Nav" value="deconnexion">Se d�connecter</button></li></c:if>
   	<c:if test = "${role eq 'admin' }"> <li><button type = "submit" name ="Nav" value="ajout">Ajouter ville/voiture</button></li></c:if><!--Visible pour l'administrateur seulement  -->
  </ul>
</form>
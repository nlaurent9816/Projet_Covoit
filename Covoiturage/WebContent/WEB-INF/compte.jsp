<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon Compte</title>
<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>

<%@include file="header.jsp" %>
<div style="background-color: #b3e6ff;padding: 1px;">
<div id="MesReservations" class="simpleForm">
	<h1>Mes Réservations</h1>
	<c:forEach items="${listeReservations}" var="r">
	<div>
	 De ${r.leTrajet.villeDepart.ville} à ${r.etapeArrivee.ville.ville}
	 Conducteur : ${r.leTrajet.conducteur.nom } ${r.leTrajet.conducteur.prenom } 
	 Coût : ${r.etapeArrivee.tarif} €
	 Statut : ${r.statut}
	<form id="annulerReservation" method="post">
		<button type="submit" name="todo" value="AnnulerReservation">Annuler la réservation</button>
		<input type="hidden" name="idReservation" value="${ r.idReservation}"/>
	</form>
	</div>
	</c:forEach>
</div>


<div id="MesTrajets" class="simpleForm">
<!-- Afficher les trajets en tant que conducteur ici, faire en sorte de pouvoir accepter 
les réservations (indiquer le nombre de réservations en attente + bouton permettat d'aller voir les réservations en attente pour ce trajet en particulier -->
	<h1>Mes Trajets</h1>
	<c:forEach items="${listeTrajetConducteur}" var="t">
	<div>
	<span>Trajet du ${t.dateDepart } à ${t.heureDepart }.
	Départ à ${t.villeDepart.ville}. Il reste ${t.nombrePlacesRestantes} places.</span>
		<form id="verificationReservation" method="post">
			<button type="submit" name="todo" value="VerifReservation">Voir les réservations</button>
			<button type="submit" name="todo" value="SupprimerTrajet">Supprimer le trajet</button>
			<input type="hidden" name="idTrajet" value="${t.idTrajet }"/>
		</form>
	</div>
	</c:forEach>
</div>
</div>


<%@include file="footer.jsp" %>
</body>
</html>
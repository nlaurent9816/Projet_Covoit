<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rechercher un trajet</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<%@include file="header.jsp" %>

<form id="recherche" method="post" class="searchForm">
	<h2>Trouver un trajet</h2>
	<span>De</span>
	<select name="villeDepart" size="1" style=" padding:5px; font-size: inherit;">
		<c:forEach items="${listeVilles}" var="v">
			<c:if test="${villeDepartSelect eq v}">
			<option selected>${v}</option></c:if>
			<c:if test="${villeDepartSelect ne v}">
			<option>${v}</option></c:if>
		</c:forEach>
	</select>
	<span>Vers</span>
	<select name="villeArrivee" size="1" style=" padding:5px; font-size: inherit;">
		<c:forEach items="${listeVilles}" var="v">
			<c:if test="${villeArriveeSelect eq v}">
			<option selected>${v}</option></c:if>
			<c:if test="${villeArriveeSelect ne v}">
			<option>${v}</option></c:if>
		</c:forEach>
	</select>
	
	<button name="todo" value="recherche" style="font-size: 20px; display: table-cell; text-align: center; margin: 10px; padding: 5px;">Rechercher !</button>
</form>




<div class="results">

<c:if test="${failReservation}"><p style="font-size: larger;color: red;">${reason}</p></c:if>
<c:if test="${SuccessReservation}"><p style="font-size: larger;color: red;">Réservation enregistrée</p></c:if>

<c:forEach items="${listeTrajets}" var="t">
		<div class="trajet">
			<span class="title">Départ le&nbsp;
			<b> ${t.dateDepart} </b>&nbsp;à&nbsp;
			<b> ${t.heureDepart} </b>&nbsp;de&nbsp;
			<b> ${t.villeDepart.ville} </b>&nbsp;vers :</span>
			
			<form id="Reservation" method="post">
	     		<ul>
					<c:forEach items="${t.lesEtapes}" var="etape" varStatus="loop">
							<li> ${etape.ville.ville} pour ${etape.tarif} €
							<c:if test = "${(connecte == 'true') and (t.nombrePlacesRestantes ne 0) }"><input type="radio" name="arrivee" value="${etape.id}"/></c:if>
							</li>
					</c:forEach>
				</ul>
				<span class="info">Conducteur : ${t.conducteur.nom} ${t.conducteur.prenom}<br/>
				Voiture : ${t.gabaritVehicule.gabaritVehicule} ${t.monVehicule}<br/>
				Il reste ${t.nombrePlacesRestantes } places</span><br>
				
				
				<c:if test = "${(connecte == 'true') and (t.nombrePlacesRestantes ne 0) }">
					<label>Nombre de places à réserver : </label> 
					
					<select name="nbPlaces" size="1" style=" padding:5px; font-size: inherit;">
						<c:forEach var="nb" begin="1" end="${ t.nombrePlacesRestantes}">
							<option>${nb}</option>
						</c:forEach>
					</select>
					<button type ="submit" name ="todo" value="reservation">Réserver</button>
					<input name="idTrajet" type="hidden" value="${t.idTrajet}">
					<input name="villeDepart" type="hidden" value="${villeDepartSelect}">
					<input name="villeArrivee" type="hidden" value="${villeArriveeSelect}">
				</c:if>
				</form>
			
		</div>
</c:forEach>
</div>
<%@include file="footer.jsp" %>

</body>
</html>
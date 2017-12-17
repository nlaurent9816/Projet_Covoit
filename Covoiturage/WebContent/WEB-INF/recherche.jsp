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
	<span>De</span>
	<select name="villeDepart" size="1" style=" padding:5px; font-size: inherit;">
		<c:forEach items="${listeVilles}" var="v">
			<option>${v}</option>
		</c:forEach>
	</select>
	<span>Vers</span>
	<select name="villeArrivee" size="1" style=" padding:5px; font-size: inherit;">
		<c:forEach items="${listeVilles}" var="v">
			<option>${v}</option>
		</c:forEach>
	</select>
	
	<button name="todo" value="recherche" style="font-size: 20px; display: table-cell; text-align: center; margin: 10px; padding: 5px;">Rechercher !</button>
</form>




<div class="results">

<c:forEach items="${listeTrajets}" var="t">
		<div class="trajet">
			<span>Départ le ${t.dateDepart} à ${t.heureDepart}</span><br/> <span> de ${t.villeDepart.ville} vers</span>
			<div>
			
				<form id="Reservation" method="post">
		     		<ul>
						<c:forEach items="${t.lesEtapes}" var="etape" varStatus="loop">
								<li> ${etape.ville.ville} pour ${etape.tarif} €</li>
								<c:if test = "${connecte}"><input type="radio" name="arrivee" value="${etape.id}"/></c:if>
						</c:forEach>
					</ul>
					<span>Conducteur : ${t.conducteur.nom} ${t.conducteur.prenom}</span><br/>
					<span>Voiture : ${t.gabaritVehicule.gabaritVehicule} ${t.monVehicule}</span><br/>
					<span>Il reste ${t.nombrePlacesRestantes } places</span>
					
					
					<c:if test = "${connecte}">
						
						<label>Nombre de places à réserver : </label> 
						<select name="nbPlaces" size="1" style=" padding:5px; font-size: inherit;">
							<c:forEach var="nb" begin="1" end="${ t.nombrePlacesRestantes}">
								<option>${nb}</option>
							</c:forEach>
						</select>
						<button type ="submit" name ="todo" value="reservation">Réserver</button>
						<input name="idTrajet" type="hidden" value="${t.idTrajet}">
					</c:if>
				</form>
			</div>
			
		</div>
</c:forEach>
</div>
<%@include file="footer.jsp" %>

</body>
</html>
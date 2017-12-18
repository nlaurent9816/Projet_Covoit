<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Votre trajet</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<%@include file="header.jsp" %>
<div style="background-color: #b3e6ff;padding: 1px;">
<form id="newTrajet" method="post" class="simpleForm">
	<h1>Proposer un trajet</h1>
	<c:if test="${failTrajet}"><p style="font-size: larger;color: red;">${reason}</p></c:if>
	<label>Votre véhicule : </label><input type ="text" name ="vehiculeDesc"/>
	<label>Gabarit :</label><select name="vehiculeGabarit" size="1">
	<c:forEach items="${listeVehicules}" var="v">
		<option>${v}</option>
	</c:forEach>
	</select>
	<br/>
	<label>Date : </label><input type ="date" name ="dateTrajet" placeholder="YYYY-MM-JJ"/>
	<label>Horaire : </label><input type ="text" name ="heureTrajet"/><span> h </span><input type ="text" name ="minuteTrajet"/>
	<br/>
	<label>Ville de départ :</label><select name="villeDepart" size="1">
	<c:forEach items="${listeVilles}" var="v">
		<option>${v}</option>
	</c:forEach>
	</select>
	<label>Ville d'arrivée :</label><select name="villeArrivee" size="1">
	<c:forEach items="${listeVilles}" var="v">
		<option>${v}</option>
	</c:forEach>
	</select>
	<label>Tarif :</label><input type="text" name="tarifTrajet"/><span> €</span>
	<br/>
	
	<ul id="lesEtapes">
	</ul>
	<button type="button" onclick="ajout_etape()">Ajouter une étape</button>
	<br/>
	
	<!-- Ajouter les étapes ici -->
	<label>Nombre de places :</label><input type="text" name="placeTrajet"/>
	<br/>
	
	<button type = "submit" name ="todo" value="newTrajet">Valider</button>
</form>
</div>
<%@include file="footer.jsp" %>

<script type="text/javascript">
function ajout_etape(){
	var ma_liste = document.getElementById("lesEtapes");
	var tab_etapes = ma_liste.getElementsByClassName("etape");
	var nb_etapes = tab_etapes.length;
	var num_new_etape = nb_etapes+1;
	var contenu_ma_liste = ma_liste.innerHTML;
	var li_a_ajouter= ' <li class="etape"><label>Ville étape d\'arrivée :</label><select name="etapeTrajet" size="1"><c:forEach items="${listeVilles}" var="v"><option>${v}</option></c:forEach></select> <label>Tarif :</label><input type="text" name="tarifEtape"/><span> €</span><button type="button" onclick="supprime_etape(this)">X</button></li>';
	contenu_ma_liste = contenu_ma_liste + li_a_ajouter;
	ma_liste.innerHTML = contenu_ma_liste;
}
function supprime_etape(a){
	a.parentNode.parentNode.removeChild(a.parentNode);
}
</script>
</body>
</html>
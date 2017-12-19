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
	<c:if test="${successTrajet}"><p style="font-size: larger;color: red;">Votre trajet a été enregistré !</p></c:if>
	<table class="trajetTable">
	<tr>
		<th>Votre véhicule : </th>
		<td><input type ="text" name ="vehiculeDesc"/></td>
	</tr>
	<tr>
		<th>Gabarit :</th>
		<td><select name="vehiculeGabarit" size="1">
		<c:forEach items="${listeVehicules}" var="v">
			<option>${v}</option>
		</c:forEach>
		</select></td>
	</tr>
	<tr>
		<th>Date : </th>
		<td><input type ="date" name ="dateTrajet" placeholder="YYYY-MM-JJ"/></td>
	</tr>
	<tr>
		<th>Horaire : </th>
		<td><input type ="number" name ="heureTrajet" min="0" max="23" maxlength="2" style="width: 40px;"/>
			<span> h </span>
			<input type ="number" name ="minuteTrajet" min="0" max="59" maxlength="2" style="width: 40px;"/>
		</td>
	</tr>
	<tr>
		<th>Ville de départ :</th>
		<td><select name="villeDepart" size="1">
		<c:forEach items="${listeVilles}" var="v">
			<option>${v}</option>
		</c:forEach>
		</select></td>
	</tr>
	<tr>
		<th>Ville d'arrivée :</th>
		<td><select name="villeArrivee" size="1">
		<c:forEach items="${listeVilles}" var="v">
			<option>${v}</option>
		</c:forEach>
		</select></td>
	</tr>
	<tr>
		<th>Tarif :</th>
		<td><input type="number" min=0 style="width: 60px" name="tarifTrajet"/><span> €</span></td>
	</tr>
	<tr>
		<th>
			Étapes : <br>
			<button type="button" onclick="ajout_etape()">Ajouter une étape</button>
		</th>
		<td>
			<ul id="lesEtapes">
				<!-- Ajouter les étapes ici -->
			</ul>
		</td>
	<tr>
		<th>Nombre de places :</th>
		<td><input type="number" min="0" maxlength="1"  style="width: 60px" name="placeTrajet"/></td>
	</tr>
	<tr>
		<th></th>
		<td><button type = "submit" name ="todo" value="newTrajet" style="width: 100%">Valider !</button></td>
	</tr>
	</table>
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
	var li_a_ajouter= ' <li class="etape"><label>Ville étape d\'arrivée :</label><select name="etapeTrajet" size="1"><c:forEach items="${listeVilles}" var="v"><option>${v}</option></c:forEach></select> <label>Tarif :</label><input type="number" min=0 style="width: 60px" name="tarifEtape"/><span> €</span><button type="button" onclick="supprime_etape(this)">X</button></li>';
	contenu_ma_liste = contenu_ma_liste + li_a_ajouter;
	ma_liste.innerHTML = contenu_ma_liste;
}
function supprime_etape(a){
	a.parentNode.parentNode.removeChild(a.parentNode);
}
</script>
</body>
</html>
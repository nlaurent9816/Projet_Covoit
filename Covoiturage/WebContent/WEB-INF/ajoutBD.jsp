<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ajouter des villes ou des véhicules</title>
<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>
<%@include file="header.jsp" %>
<form id="ajoutVille" method="post">
	<label>Ville déjà présente</label>
	<select name="villeDepart" size="5" style=" padding:5px; font-size: inherit;">
		<c:forEach items="${listeVilles}" var="v">		
			<option>${v}</option>
		</c:forEach>
	</select>
	<label>Ajouter une ville :</label>
	<input type="text" name="ajoutVille"/>
	<button type="submit" name="todo" value="ajouterVille">Ajouter une ville</button>
</form>

<form id="ajoutVehicule" method="post">
	<label>Véhicule déjà présent</label>
	<select name="vehicule" size="5" style=" padding:5px; font-size: inherit;">
		<c:forEach items="${listeVehicules}" var="v">		
			<option>${v}</option>
		</c:forEach>
	</select>
	<label>Ajouter un véhicule :</label>
	<input type="text" name="ajoutVehicule"/>
	<button type="submit" name="todo" value="ajouterVehicule">Ajouter un véhicule</button>
</form>

<%@include file="footer.jsp" %>

</body>
</html>
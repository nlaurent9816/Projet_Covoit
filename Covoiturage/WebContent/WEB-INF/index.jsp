<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Covoiturage</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>

<%@include file="header.jsp" %>


<!-- Champ de recherche -->
<div style="height:500px; width:100%; background-color: #b3e6ff; display: table;">

<div  align="center" style="display: table-cell; vertical-align: middle;">
<c:if test = "${not connecte}">
<div id=connexion>
	Connectez-vous :
	<form method="post">
		Login :<input type ="text" name ="login"/>
		<br/>
		MDP : <input type ="password" name ="mdp"/>
		<br/>
		<button type = "submit" name ="todo" value="connect">Connexion</button>
	</form>
</div>
</c:if>

<c:if test = "${connecte}">
Vous êtes connecté.
</c:if>

<h1 style="font-size: 60px; margin-bottom: 20px;">Covoiturage !</h1>
<form id="recherche" method="post" class="simpleForm">
	<div  style="font-size: 30px; display: table-cell; text-align: center;">

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

	</div>
	<button type="submit" name="todo" value="recherche" style="font-size: 20px; display: table-cell; text-align: center; margin: 10px; padding: 5px;">Rechercher !</button>
</form>
</div>
</div>

<%@include file="footer.jsp" %>

</body>
</html>
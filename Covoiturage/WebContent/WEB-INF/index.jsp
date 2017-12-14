<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
Bienvenue ${login}
</c:if>

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

<%@include file="footer.jsp" %>

</body>
</html>
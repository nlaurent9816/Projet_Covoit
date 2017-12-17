<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enregistrement</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>

<%@include file="header.jsp" %>
<div class="loginRegister">
<form id="register" method="post" class="loginForm">
	<p style="text-align: center;font-size: larger;" > <b>Créer un nouveau compte </b></p>
	<c:if test="${failRegister}"><p style="font-size: larger;color: red;">${reason}</p></c:if>
	<label>Login : </label><input type ="text" name ="login"/>
	<br/>
	<label>Mot de Passe : </label><input type ="password" name ="mdp"/>
	<br/>
	<label>Réentrez votre mot de passe : </label><input type ="password" name ="mdpbis"/>
	<br/>
	<label>Nom : </label><input type ="text" name ="nom"/>
	<br/>
	<label>Prénom : </label><input type ="text" name ="prenom"/>
	<br/>
	<label>Sexe : </label>
		<input type ="radio" name ="sexe" value="homme"/><label>Homme</label>
		<input type ="radio" name ="sexe" value="femme"/><label>Femme</label>
	<br/>
	<label>Numéro de téléphone : </label><input type ="text" name ="tel"/>
	<br/>
	<label>Email : </label><input type ="text" name ="email"/>
	<br/>
	
	<button type = "submit" name ="todo" value="register">S'enregistrer</button>
</form>

<form method="post" class="loginForm">
	<p style="text-align: center;font-size: larger;"> <b>Se connecter </b></p>
	<c:if test="${failConnect}"><p style="font-size: larger;color: red;">Echec de connexion</p></c:if>
	Login :<input type ="text" name ="login"/>
	<br/>
	Mot de passe : <input type ="password" name ="mdp"/>
	<br/>
	<button type = "submit" name ="todo" value="connect">Connexion</button>
</form>
</div>

<%@include file="footer.jsp" %>


</body>
</html>

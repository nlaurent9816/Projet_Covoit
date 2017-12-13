<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enregistrement</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>

<%@include file="header.jsp" %>

<form id="register" method="post">
		<label>Login : </label><input type ="text" name ="login"/>
		<br/>
		<label>Mot de Passe : </label><input type ="password" name ="mdp"/>
		<br/>
		<label>Entrez de nouveau votre mot de passe : </label><input type ="password" name ="mdpbis"/>
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
	
<%@include file="footer.jsp" %>


</body>
</html>
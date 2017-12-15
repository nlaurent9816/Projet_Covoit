<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Votre trajet</title>
<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>
<%@include file="header.jsp" %>

<form id="newTrajet" method="post" class="simpleForm">
	<label>Votre véhicule: </label><input type ="text" name ="vehiculeDesc"/>
	<label>Gabarit </label><select name="vehiculegabarit" size="1">
	<c:forEach items="${listeVehicules}" var="v">
	<option>${v}</option>
</c:forEach>
	
</select>
	<br/>
	
	<button type = "submit" name ="todo" value="newTrajet">S'enregistrer</button>
</form>
	
<%@include file="footer.jsp" %>

</body>
</html>
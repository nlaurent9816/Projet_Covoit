<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<body>
<%@include file="header.jsp" %>

<div id="LesReservationsAValider">
	<h1>Les Réservations à valider</h1>
	<c:forEach items="${lesResAValide}" var="r">
	 Passager : ${r.passager.nom } ${r.passager.prenom } 
	 De ${r.leTrajet.villeDepart.ville} à ${r.etapeArrivee.ville.ville}
	 Coût : ${r.etapeArrivee.tarif} €
	 Mail : ${r.passager.mail }  Tel : ${r.passager.tel }
	 Statut : ${r.statut}
	<form id="confirmerReservation" method="post">
		<button type="submit" name="todo" value="ConfirmerReservation">Confirmer la réservation</button>
		<input type="hidden" name="idReservation" value="${ r.idReservation}"/>
		<input type="hidden" name="idTrajet" value="${idTrajet}"/>
		
	</form>
	</c:forEach>
</div>

<div id="LesReservationsConfirmees">
	<h1>Les Réservations confirmées</h1>
	<c:forEach items="${lesResValide}" var="r">
	 Passager : ${r.passager.nom } ${r.passager.prenom } 
	 De ${r.leTrajet.villeDepart.ville} à ${r.etapeArrivee.ville.ville}
	 Coût : ${r.etapeArrivee.tarif} €
	 Mail : ${r.passager.mail }  Tel : ${r.passager.tel }
	 Statut : ${r.statut}
	</c:forEach>
</div>



<%@include file="footer.jsp" %>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>인기 인물</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/personPage.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
    <h1>인기 인물</h1>
    <div class="person-container">
        <c:forEach var="person" items="${popularPerson}">
            <div class="person">
                <a href="personDetailPage.do?id=${person.id}">
                    <img src="https://image.tmdb.org/t/p/w500${person.profile_path}" alt="${person.name}">
                </a>
                <div class="person-name">${person.name}</div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

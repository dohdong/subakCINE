<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp" />
<%
    // 세션에서 이메일을 가져옴
    String email = (String) session.getAttribute("email");
    String displayName = null;

    // 이메일이 null이 아니고 비어있지 않다면
    if (email != null && !email.isEmpty()) {
        // '@' 앞부분을 가져옴
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            displayName = email.substring(0, atIndex);
        }
    }
    // displayName을 request에 저장
    request.setAttribute("displayName", displayName);
%>
<div class="container">
    <h2>${displayName }님이 좋아요한 영화 목록</h2>
    <div class="movie-container">
        <c:forEach var="movie" items="${likedMovies}">
            <div class="movie">
                <a href="movieDetailPage.do?id=${movie.id}">
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
                </a>
                <div class="movie-title">${movie.title}</div>
            </div>
        </c:forEach>
    </div>

    <h2>${displayName }님이 좋아요한 TV 프로그램 목록</h2>
    <div class="tvshow-container">
        <c:forEach var="tvShow" items="${likedTVShows}">
            <div class="tvshow">
                <a href="tvShowDetailPage.do?id=${tvShow.id}">
                    <img src="https://image.tmdb.org/t/p/w500${tvShow.poster_path}" alt="${tvShow.name}">
                </a>
                <div class="tvshow-title">${tvShow.name}</div>
            </div>
        </c:forEach>
    </div>

    <h2>${displayName}님이 좋아요한 인물 목록</h2>
    <div class="person-container">
        <c:forEach var="person" items="${likedPersons}">
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

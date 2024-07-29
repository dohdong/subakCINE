<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Popular TV Shows</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/TVShowsPage.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
    <h1>Airing Today</h1>
    <div class="tvshow-container">
        <c:forEach var="tvShow" items="${airingTodayTVShows}">
            <div class="tvshow">
                <a href="tvShowDetailPage.do?id=${tvShow.id}">
                    <img src="https://image.tmdb.org/t/p/w500${tvShow.poster_path}" alt="${tvShow.name}">
                </a>
                <div class="tvshow-title">${tvShow.name}</div>
            </div>
        </c:forEach>
    </div>

    <h1>Top Rated TVShows</h1>
    <div class="tvshow-container">
        <c:forEach var="tvShow" items="${topRatedTVShows}">
            <div class="tvshow">
                <a href="tvShowDetailPage.do?id=${tvShow.id}">
                    <img src="https://image.tmdb.org/t/p/w500${tvShow.poster_path}" alt="${tvShow.name}">
                </a>
                <div class="tvshow-title">${tvShow.name}</div>
            </div>
        </c:forEach>
    </div>

    <h1>Popular TV Shows</h1>
    <div class="tvshow-container">
        <c:forEach var="tvShow" items="${popularTVShows}">
            <div class="tvshow">
                <a href="tvShowDetailPage.do?id=${tvShow.id}">
                    <img src="https://image.tmdb.org/t/p/w500${tvShow.poster_path}" alt="${tvShow.name}">
                </a>
                <div class="tvshow-title">${tvShow.name}</div>
            </div>
        </c:forEach>
    </div>

    <h1>On The Air TVShows</h1>
    <div class="tvshow-container">
        <c:forEach var="tvShow" items="${onTheAirTVShows}">
            <div class="tvshow">
                <a href="tvShowDetailPage.do?id=${tvShow.id}">
                    <img src="https://image.tmdb.org/t/p/w500${tvShow.poster_path}" alt="${tvShow.name}">
                </a>
                <div class="tvshow-title">${tvShow.name}</div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

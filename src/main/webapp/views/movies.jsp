<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Popular Movies</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/moviesPage.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="container">
    <h1>Now Playing Movies</h1>
    <div class="movie-container">
        <c:forEach var="movie" items="${nowPlayingMovies}">
            <div class="movie">
                <a href="movieDetailPage.do?id=${movie.id}">
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
                </a>
                <div class="movie-title">${movie.title}</div>
            </div>
        </c:forEach>
    </div>

    <h1>Top Rated Movies</h1>
    <div class="movie-container">
        <c:forEach var="movie" items="${topRatedMovies}">
            <div class="movie">
                <a href="movieDetailPage.do?id=${movie.id}">
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
                </a>
                <div class="movie-title">${movie.title}</div>
            </div>
        </c:forEach>
    </div>

    <h1>Popular Movies</h1>
    <div class="movie-container">
        <c:forEach var="movie" items="${popularMovies}">
            <div class="movie">
                <a href="movieDetailPage.do?id=${movie.id}">
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
                </a>
                <div class="movie-title">${movie.title}</div>
            </div>
        </c:forEach>
    </div>

    <h1>Upcoming Movies</h1>
    <div class="movie-container">
        <c:forEach var="movie" items="${upcomingMovies}">
            <div class="movie">
                <a href="movieDetailPage.do?id=${movie.id}">
                    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
                </a>
                <div class="movie-title">${movie.title}</div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

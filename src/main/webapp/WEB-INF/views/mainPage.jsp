<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>subakCINE</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .header {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 10px 20px;
            background-color: white;
            color:black;
        }
        .header img {
            width: 50px;
            cursor: pointer;
        }
        .nav {
            display: flex;
            gap: 20px;
        }
        .nav a {
            color: black;
            text-decoration: none;
            font-size: 18px;
        }
        .nav a:hover {
            text-decoration: underline;
        }
        .container {
            padding: 20px;
        }
        .movie-container, .tvshow-container, .person-container {
            display: flex;
            overflow-x: scroll;
        }
        .movie, .tvshow, .person {
            margin-right: 20px;
        }
        .movie img, .tvshow img, .person img {
            width: 150px;
            height: 225px;
            cursor: pointer;
        }
        .movie-title, .tvshow-title, .person-name {
            text-align: center;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div class="header">
    <img src="img/logo.png" alt="Logo" onclick="window.location.href='mainPage.do'">
    <div class="nav">
        <a href="moviesPage.do">영화</a>
        <a href="tvShowsPage.do">TV 프로그램</a>
        <a href="peoplePage.do">인물</a>
    </div>
</div>
<div class="container">
    <h1>subakCINE</h1>
    <h2>Popular Movies</h2>
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

    <h2>Popular TV Shows</h2>
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

    <h2>Popular People</h2>
    <div class="person-container">
        <c:forEach var="person" items="${popularPeople}">
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

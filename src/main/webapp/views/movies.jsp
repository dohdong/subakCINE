<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Popular Movies</title>
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
            position: fixed;
            width: 80%;
            top: 0;
            z-index: 1000;
            height: 70px;
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
            margin-top: 50px;
        }
        .search-container {
            display: flex;
            gap: 10px;
        }
        .search-container input {
            padding: 5px;
            font-size: 16px;
        }
        .search-container button {
            padding: 5px 10px;
            font-size: 16px;
        }
        .movie-container {
            display: flex;
            overflow-x: scroll;
        }
        .movie {
            margin: 10px;
            flex: 1 0 21%; /* Adjust the width of the items */
            box-sizing: border-box;
        }
        .movie img {
            width: 100%;
            height: auto;
            cursor: pointer;
        }
        .movie-title {
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
        <a href="personPage.do">인물</a>
    </div>
    <div class="search-container">
        <form action="searchPage.do" method="get">
            <input type="text" name="query" placeholder="Search...">
            <button type="submit">Search</button>
        </form>
    </div>
</div>
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

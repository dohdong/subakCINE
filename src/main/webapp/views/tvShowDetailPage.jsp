<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TV Show Details</title>
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
        .tvshow-details {
            max-width: 800px;
            margin: auto;
            padding: 20px;
            background: white;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .tvshow-details img {
            width: 300px;
            height: 450px;
        }
        .tvshow-info {
            margin-top: 20px;
        }
        .tvshow-info h2 {
            margin: 0;
            font-size: 24px;
        }
        .tvshow-info p {
            margin: 5px 0;
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
    <div class="search-container">
        <form action="searchPage.do" method="get">
            <input type="text" name="query" placeholder="Search...">
            <button type="submit">Search</button>
        </form>
    </div>
</div>
<div class="tvshow-details">
    <img src="https://image.tmdb.org/t/p/w500${tvShow.poster_path}" alt="${tvShow.name}">
    <div class="tvshow-info">
        <h2>${tvShow.name}</h2>
        <p><strong>Original Name:</strong> ${tvShow.original_name}</p>
        <p><strong>First Air Date:</strong> ${tvShow.first_air_date}</p>
        <p><strong>Genres:</strong>
            <c:forEach var="genre" items="${tvShow.genres}">
                ${genre.name}
                <c:if test="${!genre.last}">, </c:if>
            </c:forEach>
        </p>
        <p><strong>Rating:</strong> ${tvShow.vote_average}</p>
        <p><strong>Overview:</strong> ${tvShow.overview}</p>
        <p><strong>Popularity:</strong> ${tvShow.popularity}</p>
    </div>
</div>
</body>
</html>

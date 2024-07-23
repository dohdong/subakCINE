<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Results</title>
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
        .search-results {
            display: flex;
            flex-wrap: wrap;
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
        .result-item {
            margin: 10px;
            width: 150px;
            text-align: center;
        }
        .result-item img {
            width: 150px;
            height: 225px;
            cursor: pointer;
        }
        .result-title {
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
    <div class="search-container">
        <form action="searchPage.do" method="get">
            <input type="text" name="query" placeholder="Search...">
            <button type="submit">Search</button>
        </form>
    </div>
</div>
<div class="container">
    <h1>Search Results</h1>

    <h2>Movies</h2>
    <div class="search-results">
        <c:forEach var="result" items="${movies}">
            <div class="result-item">
                <a href="movieDetailPage.do?id=${result.id}">
                    <img src="https://image.tmdb.org/t/p/w500${result.poster_path}" alt="${result.title}">
                    <div class="result-title">${result.title}</div>
                </a>
            </div>
        </c:forEach>
    </div>

    <h2>TV Shows</h2>
    <div class="search-results">
        <c:forEach var="result" items="${tvShows}">
            <div class="result-item">
                <a href="tvShowDetailPage.do?id=${result.id}">
                    <img src="https://image.tmdb.org/t/p/w500${result.poster_path}" alt="${result.name}">
                    <div class="result-title">${result.name}</div>
                </a>
            </div>
        </c:forEach>
    </div>

    <h2>People</h2>
    <div class="search-results">
        <c:forEach var="result" items="${people}">
            <div class="result-item">
                <a href="personDetailPage.do?id=${result.id}">
                    <img src="https://image.tmdb.org/t/p/w500${result.profile_path}" alt="${result.name}">
                    <div class="result-title">${result.name}</div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>

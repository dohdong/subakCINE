<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Popular TV Shows</title>
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
            color: black;
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
        .container {
            padding: 20px;
        }
        .tvshow-container {
            display: flex;
            overflow-x: scroll;
        }
        .tvshow {
            margin: 10px;
            flex: 1 0 21%; /* Adjust the width of the items */
            box-sizing: border-box;
        }
        .tvshow img {
            width: 100%;
            height: auto;
            cursor: pointer;
        }
        .tvshow-title {
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
    <div class="search-container">
        <form action="searchPage.do" method="get">
            <input type="text" name="query" placeholder="Search...">
            <button type="submit">Search</button>
        </form>
    </div>
</div>
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

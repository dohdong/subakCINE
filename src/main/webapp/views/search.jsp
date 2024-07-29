<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            width: 100%;
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
            margin-top: 100px; /* 헤더 아래로 여백 추가 */
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
<jsp:include page="header.jsp" />

<div class="container">
    <h1>Search Results</h1>

    <h2>영화</h2>
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

    <h2>TV 프로그램</h2>
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

    <h2>인물</h2>
    <div class="search-results">
        <c:forEach var="result" items="${person}">
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

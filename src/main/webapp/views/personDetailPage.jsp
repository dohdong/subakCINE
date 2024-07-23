<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Person Details</title>
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
<h1>${personDetails.name}</h1>
<img src="https://image.tmdb.org/t/p/w500${personDetails.profile_path}" alt="${personDetails.name}">
<p><strong>Known For:</strong> ${personDetails.known_for_department}</p>
<p><strong>Biography:</strong> ${personDetails.biography}</p>
<p><strong>Birthday:</strong> ${personDetails.birthday}</p>
<p><strong>Place of Birth:</strong> ${personDetails.place_of_birth}</p>
<p><strong>Popularity:</strong> ${personDetails.popularity}</p>
</body>
</html>

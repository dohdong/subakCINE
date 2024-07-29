<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>인기 인물</title>
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
            margin-top: 100px; /* 헤더 아래로 여백 추가 */
        }
        .person-container {
            display: flex;
            flex-wrap: wrap;
        }
        .person {
            margin: 10px;
            flex: 1 0 21%; /* Adjust the width of the items */
            box-sizing: border-box;
        }
        .person img {
            width: 100%;
            height: auto;
            cursor: pointer;
        }
        .person-name {
            text-align: center;
            font-size: 14px;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" />

<div class="container">
    <h1>인기 인물</h1>
    <div class="person-container">
        <c:forEach var="person" items="${popularPerson}">
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

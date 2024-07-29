<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Person Details</title>--%>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        /*.header {*/
        /*    display: flex;*/
        /*    align-items: center;*/
        /*    justify-content: space-between;*/
        /*    padding: 10px 20px;*/
        /*    background-color: white;*/
        /*    color: black;*/
        /*    position: fixed;*/
        /*    width: 100%;*/
        /*    top: 0;*/
        /*    z-index: 1000;*/
        /*    height: 70px;*/
        /*}*/
        /*.header img {*/
        /*    width: 50px;*/
        /*    cursor: pointer;*/
        /*}*/
        /*.search-container {*/
        /*    display: flex;*/
        /*    gap: 10px;*/
        /*}*/
        /*.search-container input {*/
        /*    padding: 5px;*/
        /*    font-size: 16px;*/
        /*}*/
        /*.search-container button {*/
        /*    padding: 5px 10px;*/
        /*    font-size: 16px;*/
        /*}*/
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
        .buttons {
            margin-top: 20px;
        }
        .buttons form {
            display: inline-block;
        }
        .buttons button {
            padding: 10px 20px;
            font-size: 16px;
            margin-right: 10px;
            cursor: pointer;
        }
    </style>
<%--</head>--%>
<body>
<jsp:include page="header.jsp" />

<div class="container">
    <h1>${personDetails.name}</h1>
    <img src="https://image.tmdb.org/t/p/w500${personDetails.profile_path}" alt="${personDetails.name}">
    <p><strong>Known For:</strong> ${personDetails.known_for_department}</p>
    <p><strong>Biography:</strong> ${personDetails.biography}</p>
    <p><strong>Birthday:</strong> ${personDetails.birthday}</p>
    <p><strong>Place of Birth:</strong> ${personDetails.place_of_birth}</p>
    <p><strong>Popularity:</strong> ${personDetails.popularity}</p>
</div>

<div class="buttons">
    <form action="personDetailPage.do" method="post">
        <input type="hidden" name="id" value="${personDetails.id}">
        <input type="hidden" name="action" value="likePerson">
        <button type="submit">Like</button>
    </form>
</div>

<div>
    <!-- 좋아요 수 표시 -->
    <p>좋아요: ${likeCount}</p>
</div>

</body>
</html>

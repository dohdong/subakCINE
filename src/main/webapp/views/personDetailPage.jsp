<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/personDetail.css">
</head>
<body>
<jsp:include page="header.jsp" />

<div class="person-details">
    <div class="person-image">
        <img src="https://image.tmdb.org/t/p/w500${personDetails.profile_path}" alt="${personDetails.name}">
        <div class="like-section">
            <div class="buttons">
                <form action="personDetailPage.do" method="post">
                    <input type="hidden" name="id" value="${personDetails.id}">
                    <input type="hidden" name="action" value="likePerson">
                    <button type="submit" class="like-button ${isLiked ? 'liked' : ''}"></button>
                </form>
            </div>
            <!-- 좋아요 수 표시 -->
            <p class="like-count">좋아요: ${likeCount}</p>
        </div>
    </div>
    <div class="person-info">
        <h2>${personDetails.name}</h2>
        <p><strong>Known For:</strong> ${personDetails.known_for_department}</p>
        <p><strong>Biography:</strong> ${personDetails.biography}</p>
        <p><strong>Birthday:</strong> ${personDetails.birthday}</p>
        <p><strong>Place of Birth:</strong> ${personDetails.place_of_birth}</p>
        <p><strong>Popularity:</strong> ${personDetails.popularity}</p>
    </div>
</div>
</body>
</html>

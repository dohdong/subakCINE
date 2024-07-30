<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/movieDetail.css">
</head>
<body>
<%@ include file="header.jsp" %>

<div class="movie-details">
    <img src="https://image.tmdb.org/t/p/w500${movie.poster_path}" alt="${movie.title}">
    <div class="movie-info">
        <h2>${movie.title}</h2>
        <p><strong>Original Title:</strong> ${movie.original_title}</p>
        <p><strong>Release Year:</strong> ${movie.release_date}</p>
        <p><strong>Genres:</strong>
            <c:forEach var="genre" items="${movie.genres}">
                ${genre.name}
                <c:if test="${!genre.last}"> </c:if>
            </c:forEach>
        </p>
        <p><strong>Rating:</strong> ${movie.vote_average}</p>
        <p><strong>Runtime:</strong> ${movie.runtime} minutes</p>
        <p><strong>Summary:</strong> ${movie.overview}</p>
        <p><strong>Viewers:</strong> ${movie.popularity}</p>


        <div class="like-section">
            <div class="buttons">
                <form action="movieDetailPage.do" method="post">
                    <input type="hidden" name="id" value="${movie.id}">
                    <input type="hidden" name="action" value="likeMovie">
                    <button type="submit" class="like-button ${isLiked ? 'liked' : ''}"></button>
                </form>
            </div>
            <!-- 좋아요 수 표시 -->
            <p class="like-count">좋아요: ${likeCount}</p>
        </div>
      
      

<%--        <c:if test="${not empty message}">--%>
<%--            <p>${message}</p>--%>
<%--        </c:if>--%>
    </div>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/tvShowDetail.css">
</head>
<body>
<jsp:include page="header.jsp" />

<div class="tvshow-details">
    <img src="https://image.tmdb.org/t/p/w500${tvShow.poster_path}" alt="${tvShow.name}">
    <div class="tvshow-info">
        <h2>${tvShow.name}</h2>
        <p><strong>Original Name:</strong> ${tvShow.original_name}</p>
        <p><strong>First Air Date:</strong> ${tvShow.first_air_date}</p>
        <p><strong>Genres:</strong>
            <c:forEach var="genre" items="${tvShow.genres}">
                ${genre.name}
                <c:if test="${!genre.last}"> </c:if>
            </c:forEach>
        </p>
        <p><strong>Rating:</strong> ${tvShow.vote_average}</p>
        <p><strong>Overview:</strong> ${tvShow.overview}</p>
        <p><strong>Popularity:</strong> ${tvShow.popularity}</p>

        <div class="buttons">
            <form action="tvShowDetailPage.do" method="post">
                <input type="hidden" name="id" value="${tvShow.id}">
                <input type="hidden" name="action" value="likeTVShow">
                <button type="submit">Like</button>
            </form>
        </div>

        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>

        <!-- 좋아요 수 표시 -->
        <p>좋아요: ${likeCount}</p>
    </div>
</div>
</body>
</html>

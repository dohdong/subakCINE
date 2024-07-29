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
<%--    <title>Movie Details</title>--%>
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
            color:black;
            position: fixed;
            width: 100%;
            top: 0;
            z-index: 1000;
            height: 70px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
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
        .movie-details {
            max-width: 800px;
            margin: 100px auto 20px;
            padding: 20px;
            background: white;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            display: flex;
            gap: 20px;
        }
        .movie-details img {
            width: 300px;
            height: 450px;
        }
        .movie-info {
            margin-top: 20px;
        }
        .movie-info h2 {
            margin: 0;
            font-size: 24px;
        }
        .movie-info p {
            margin: 5px 0;
        }
        .buttons {
            margin-top: 20px;
        }
        .buttons img {
            width: 50px;
            height: 50px;
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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.like-button').click(function(event) {
                event.preventDefault();

                var $this = $(this);
                var movieId = $this.data('id');
                var isLiked = $this.hasClass('liked');
                var action = isLiked ? 'unlikeMovie' : 'likeMovie';

                $.ajax({
                    url: 'movieDetailPage.do',
                    type: 'POST',
                    data: {
                        id: movieId,
                        action: action,
                        ajax: true
                    },
                    dataType: 'json',
                    success: function(response) {
                        if (response.success) {
                            if (response.isLiked) {
                                $this.attr('src', 'img/like-icon.png').addClass('liked');
                            } else {
                                $this.attr('src', 'img/like-icon-empty.png').removeClass('liked');
                            }
                        } else {
                            console.error('Server response error:', response.message);
                            alert('Failed to update like status: ' + response.message);
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("AJAX Error: ", status, error);
                        console.error("Response Text: ", xhr.responseText);
                        alert('Error while processing the request. Check console for details.');
                    }
                });
            });
        });
    </script>


</head>

<body>
<jsp:include page="header.jsp" />
<%--<jsp:include page="header.jsp" />--%>

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

        <div class="buttons">
            <form action="movieDetailPage.do" method="post">
                <input type="hidden" name="id" value="${movie.id}">
                <input type="hidden" name="action" value="likeMovie">
                <button type="submit">
                    <img src="<c:out value='${isLiked ? "img/like-icon.png" : "img/like-icon-empty.png"}' />" alt="Like">
                </button>
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

<%@ page import="com.subakcine.vo.MovieVO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: WD
  Date: 2024-07-25
  Time: PM 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <title>WatchaPedia Collection Detail</title>
    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/collectionDetail.css">
    <style>
            /* 간단한 스타일링 */
        body { font-family: Arial, sans-serif; text-align: center}
        header { background-color: #fff; padding: 10px; }
        .logo { color: #ff0558; font-weight: bold; font-size: 24px; }
        .search { float: right; }
        .profile { margin-top: 20px; }
        .works { margin-top: 20px; }
        .movie { display: inline-block; margin-right: 10px; text-align: center; }
        .movie img { width: 120px; height: 180px; }
        #center{
            width: 60%; /* 또는 원하는 너비 */
            margin: 0 auto;
            border-radius: 15px;
            background: #999999;
        }
    </style>
</head>
<body>
<jsp:include page="header.html"/>
<main>
    <div id="center">
        <section class="profile">
            <h1><%=request.getAttribute("collectionName")%></h1>
            <img src="./img/dj.jpg" alt="유현진 프로필" width="50" height="50">
            <p>이동진</p>
            <p>좋아요 0 · 팔로워 0 · 1일 전 업데이트</p>
            <button>좋아요</button>
            <button>댓글</button>
            <button>공유</button>
        </section>

        <section class="works">
            <h2>작품들 <%=request.getAttribute("countMovies")%></h2>
            <c:forEach items="${movieList}" var="movie">
                <a href="movieDetailPage.do?id=${movie.id}"><div class="movie" width="100%">
                    <img src="https://image.tmdb.org/t/p/w500${movie.movieImgUrl}" alt="movieImage">
                    <p>${movie.title}</p>
                    <p>평점</p>
                </div></a>
            </c:forEach>
        </section>
    </div>
</main>
</body>
</html>

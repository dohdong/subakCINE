<%@ page import="com.subakcine.vo.CollectionItemVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="css/collectionDetail.css">
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
            background: #A1BDB1;;
        }
    </style>
</head>
<body>
<%@ include file="header.jsp" %>
<main>
    <div id="center">
        <section class="profile">
            <h1 style="padding:15px">${collection.collectionName}</h1>
            <img src="./img/dj.jpg" alt="프로필" width="50" height="50">
            <p>${displayName}</p>
            <p>좋아요 0 · 팔로워 0 · 1일 전 업데이트</p>
            <button>좋아요</button>
            <button>댓글</button>
            <button>공유</button>
        </section>

        <section class="works">
            <h2>작품들</h2>
            <c:forEach items="${collection.items}" var="item">
                <a href="movieDetailPage.do?id=${item.id}"><div class="movie" width="100%">
                    <img src="https://image.tmdb.org/t/p/w500${item.movieImgUrl}" alt="movieImage">
                    <p>${item.title}</p>
                    <p>평점</p>
                </div></a>
            </c:forEach>
        </section>
    </div>
    <section class="buttons">
        <a href="/collectionDelete.do"><input type="button" value="컬렉션 삭제하기"></a>
        <a href="/updateCollection.do"><input type="button" value="컬렉션 수정하기"></a>
    </section>
</main>
</body>
</html>

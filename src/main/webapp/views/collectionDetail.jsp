<%@ page import="com.subakcine.vo.CollectionItemVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" href="css/collectionDetail.css">
    <style>
            /* 간단한 스타일링 */
        body { font-family: 'Segoe UI'; text-align: center}
        header { background-color: #fff; padding: 10px; }
        .logo { color: #ff0558; font-weight: bold; font-size: 24px; }
        .search { float: right; }
        .profile { margin-top: 20px; }
        .works { margin-top: 20px; }
        .movie { display: inline-block; margin-right: 10px; text-align: center; padding: 15px;}
        .movie img { width: 120px; height: 180px; }
        #container{margin-top: 80px}
        #center{
            width: 60%; /* 또는 원하는 너비 */
            margin: 0 auto;
            border-radius: 15px;
            background: #A1BDB1;;
            /*border-style:solid;*/
            /*border-width:2px;*/
            /*border-color:black;*/
            padding-bottom: 20px;
        }
        .buttons{
            background-color: #738E87;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 170px;
            font-size: 16px;
        }
        a:hover { color:#ff0558; }
        a { color:black; }
    </style>
    <script>
        window.onload = function(){
            let deleteFunc=function(){
                let collectionId="${collection.collectionId}";
                console.log(collectionId)
                let data={
                    collectionId:collectionId
                }
                $.ajax({
                    url: "/views/deleteCollection.jsp",
                    data:data,
                    success:function(response){
                        if (response=="1"){
                            console.log(response.re);
                            window.location.href="userCollection.do";
                        }
                    }
                })
            }
            $("#deleteBtn").on("click", function(e){
                if (confirm("삭제하시겠습니까?")==false){
                    e.preventDefault();
                    return false;
                }
                deleteFunc();
            });
        }
    </script>
</head>
<body>
<%@ include file="header.jsp" %>
<div id="container">
    <h1>나의 컬렉션</h1>
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
                <c:choose >
                    <c:when test="${item.type=='tv'}">
                        <a href="tvShowDetailPage.do?id=${item.id}"><div class="movie" width="100%">
                        <div class="imgbox"><img src="https://image.tmdb.org/t/p/w500${item.movieImgUrl}" alt="movieImage"></div>
                        <div class="titlebox" width="100%" heigth="100%"><p>${item.title}</p></div>
                    </c:when>
                    <c:otherwise>
                        <a href="movieDetailPage.do?id=${item.id}"><div class="movie" width="100%">
                        <div class="imgbox"><img src="https://image.tmdb.org/t/p/w500${item.movieImgUrl}" alt="movieImage"></div>
                        <div class="titlebox" width="100%" heigth="100%"><p>${item.title}</p></div>
                        </c:otherwise>
                </c:choose>

                </div></a>
            </c:forEach>
        </section>
    </div>
    <section >
        <input type="button" value="컬렉션 삭제하기" id="deleteBtn" class="buttons">
        <a href="/updateCollection.do?collectionId=${collection.collectionId}"><input type="button" value="컬렉션 수정하기" id="updateBtn" class="buttons"></a>
    </section>
</div>
</body>
</html>

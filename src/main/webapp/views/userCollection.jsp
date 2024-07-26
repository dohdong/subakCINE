<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: WD
  Date: 2024-07-24
  Time: AM 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            border-bottom: 1px solid #ccc;
        }
        nav {
            flex: 1;
            text-align: center;
        }
        nav a {
            margin: 0 15px;
            text-decoration: none;
            color: black;
        }
        .profile {
            text-align: center;
            margin: 20px 0;
        }
        .profile img {
            border-radius: 50%;
        }
        .stats, .library {
            display: flex;
            justify-content: center;
            margin: 20px 0;
        }
        .stats div, .library div {
            margin: 0 20px;
            text-align: center;
        }
        footer {
            text-align: center;
            padding: 20px;
            border-top: 1px solid #ccc;
        }
        .container {
            padding: 20px;
        }
        .collection-header {
            font-size: 24px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .collection-card {
            background-color: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
        }
        .collection-card img {
            width: 100%;
            height: auto;
        }
        .collection-details {
            padding: 15px;
        }
        .collection-details .user-info {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .collection-details .user-info img {
            border-radius: 50%;
            width: 30px;
            height: 30px;
            margin-right: 10px;
        }
        .collection-details .title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .collection-details .description {
            color: #666;
            margin-bottom: 10px;
        }
        .collection-details .interaction {
            font-size: 14px;
            color: #999;
        }
        .collection-add-card {
            background-color: grey;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            overflow: hidden;
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
            height: 100px;
        }
    </style>
    <script>
        $(function(){
            <%--var collectionList=<%= new Gson().toJson(request.getAttribute("collectionList"))%>;--%>
            <%--var imageList=<%= new Gson().toJson(request.getAttribute("imageList"))%>;--%>
            // console.log(collectionList);
            // console.log(imageList);
        });
    </script>
</head>
<body>
<header>
    <div>
        <img src="./img/logo.png" width="120px">
    </div>
    <nav>
        <a href="mainPage.do">영화</a>
        <a href="tvShowsPage.do">TV 프로그램</a>
        <a href="personPage.do">인물</a>
        <a href="#">컬렉션</a>
    </nav>
    <div>
        <input type="text" placeholder="검색">
        <img src="./img/user-icon.png" alt="사용자 아이콘" width="30px">
        <img src="./img/settings-icon.png" alt="설정 아이콘" width="30px">
    </div>
</header>
<h3>나의 컬렉션</h3>
<hr>
<div class="container">
    <div class="collection-card">
        <c:forEach var="i" begin="1" end="${count }" varStatus="count">
            <table>
                <tr>
                    <c:forEach var="image" items="${imageList[count.index-1]}">
                        <td><img src="https://image.tmdb.org/t/p/w500${image}" width="30px"></td>
                    </c:forEach>
                </tr>
            </table>

            <div class="collection-details">
                <div class="user-info">
                    <img src="./img/dj.jpg" alt="User Avatar">
                    <span>이동진</span>
                </div>

                <a href="collectionDetail.do?collectionId=${collectionList[count.index-1].collectionId}"><div class="title">${collectionList[count.index-1].collectionName}</div></a>
                <div class="description">설명</div>
                <div class="interaction">좋아요 0 댓글 0</div>
            </div>
        </c:forEach>
    </div>
    <a href="createCollection.do"><div class="collection-add-card">
            <h4>컬렉션 추가하기</h4>
    </div></a>
</div>
</body>
</html>
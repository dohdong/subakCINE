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
    <link rel="stylesheet" href="./css/header.css">
    <link rel="stylesheet" href="./css/userCollection.css">
    <jsp:include page="header.html"/>
</head>
<body>
<h1>나의 컬렉션</h1>
<div class="container">
    <c:forEach var="collection" items="${collectionList }" varStatus="collections">
        <div class="collection-card">
            <table>
                <tr>
                    <c:forEach var="item" items="${collection.items}" varStatus="itemCnt">
                        <c:if test="${itemCnt.index < 5}">
                            <td class="fixed-width">
                                <c:choose>
                                    <c:when test="${item.movieImgUrl != null}">
                                        <img src="https://image.tmdb.org/t/p/w500${item.movieImgUrl}" />
                                    </c:when>
                                    <c:otherwise>
                                        <div style="background: grey;"></div>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </c:if>
                    </c:forEach>
                    <c:if test="${collection.items.size() < 5}">
                        <c:forEach begin="${collection.items.size()}" end="4">
                            <td class="fixed-width">
                                <div style="width: 30px; height: 50px;"></div>
                            </td>
                        </c:forEach>
                    </c:if>
                </tr>
            </table>

            <div class="collection-details">
                <div class="user-info">
                    <img src="./img/dj.jpg" alt="User Avatar">
                    <span>이동진</span>
                </div>

                <a href="collectionDetail.do?collectionId=${collection.collectionId}">
                    <div class="title">${collection.collectionName}</div>
                </a>
                <div class="interaction">좋아요 0 코멘트 0</div>
            </div>
        </div>
    </c:forEach>
    <a href="createCollection.do"><div class="collection-add-card">
            <h4>컬렉션 추가하기</h4>
    </div></a>
</div>
</body>
</html>
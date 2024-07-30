<!DOCTYPE html>
<html>
<header>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/userCollection.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</header>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h1>나의 컬렉션</h1>
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
                    <span>${sessionScope.displayName}</span>
                </div>

                <a href="collectionDetail.do?collectionId=${collection.collectionId}">
                    <div class="title">${collection.collectionName}</div>
                </a>
                <div class="interaction">좋아요 0 코멘트 0</div>
            </div>
        </div>
    </c:forEach>
    <a href="createCollection.do"><div class="collection-add-card">
            <h1>컬렉션 추가하기</h1>
    </div></a>
</div>
</body>
</html>
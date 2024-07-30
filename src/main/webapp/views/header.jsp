<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.lang.String" %>

<%
    // 세션에서 이메일을 가져옴
    String email = (String) session.getAttribute("email");
    String displayName = null;

    // 이메일이 null이 아니고 비어있지 않다면
    if (email != null && !email.isEmpty()) {
        // '@' 앞부분을 가져옴
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            displayName = email.substring(0, atIndex);
        }
        session.setAttribute("displayName", displayName);
    }
%>

<div class="header">
    <img src="img/logo.png" alt="Logo" onclick="window.location.href='mainPage.do'">
    <div class="nav">
        <a href="moviesPage.do">영화</a>
        <a href="tvShowsPage.do">TV 프로그램</a>
        <a href="personPage.do">인물</a>
        <a href="allCollection.do">컬렉션</a>
    </div>
    <div class="search-container">
        <form action="searchPage.do" method="get">
            <input type="text" name="query" placeholder="영화, tv, 인물 검색">
            <button type="submit">검색</button>
        </form>
        <div class="auth-container">
            <% if (displayName != null) { %>
<%--            <span>환영합니다, <%= displayName %>님</span>--%>
            <a href="userPage.do"><img src="img/user-icon.png" alt="User Icon"></a>
            <a href="userUpdate.do"><img src="img/settings-icon.png" alt="Settings Icon"></a>
            <% } else { %>
            <a href="signIn.do">로그인</a>
            <a href="signUp.do">회원가입</a>
            <% } %>
        </div>
    </div>
</div>

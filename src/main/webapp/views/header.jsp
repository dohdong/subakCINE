<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<div class="header">
    <img src="img/logo.png" alt="Logo" onclick="window.location.href='mainPage.do'">
    <div class="nav">
        <a href="moviesPage.do">영화</a>
        <a href="tvShowsPage.do">TV 프로그램</a>
        <a href="personPage.do">인물</a>
    </div>
    <div>
        <a href="signIn.do">로그인</a>
        <a href="signUp.do">회원가입</a>
    </div>
    <div class="search-container">
        <form action="searchPage.do" method="get">
            <input type="text" name="query" placeholder="Search...">
            <button type="submit">Search</button>
        </form>

        <c:set var="settingIconUri" value="${sessionScope.email!=null?'/userUpdate.do':'signIn.do'}"/>
        <c:set var="userIconUri" value="${sessionScope.email!=null?'/userPage.do':'signIn.do'}"/>
        <a href="${userIconUri}"><img src="./img/user-icon.png" width="20px"></a>
        <a href="${settingIconUri}"><img src="./img/settings-icon.png" width="20px"></a>

    </div>
</div>

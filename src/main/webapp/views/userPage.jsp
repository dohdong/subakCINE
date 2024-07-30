<%@ page import="com.google.gson.Gson" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/userPage.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(function(){
            var movieData = <%= new Gson().toJson(request.getAttribute("popularMovies")) %>;
            var tvData = <%= new Gson().toJson(request.getAttribute("popularTVShows")) %>;
            var personData = <%= new Gson().toJson(request.getAttribute("popularPerson")) %>;

            $("#myMovie").click(function () {
                $("#contents").empty();
                var tr;
                $.each(movieData, function (i, data) {
                    if (i % 4 == 0) {
                        tr = $("<tr></tr>");
                        $("#contents").append(tr);
                    }
                    let img = $("<img width='100px'>").attr("src", "https://image.tmdb.org/t/p/w500" + data.poster_path);
                    let td = $("<td></td>");
                    td.append(img);
                    tr.append(td);
                });
            });

            $("#myTV").click(function () {
                $("#contents").empty();
                var tr;
                $.each(tvData, function (i, data) {
                    if (i % 4 == 0) {
                        tr = $("<tr></tr>");
                        $("#contents").append(tr);
                    }
                    let img = $("<img width='100px'>").attr("src", "https://image.tmdb.org/t/p/w500" + data.poster_path);
                    let td = $("<td></td>");
                    td.append(img);
                    tr.append(td);
                });
            });

            $("#myPerson").click(function () {
                $("#contents").empty();
                var tr;
                $.each(personData, function (i, data) {
                    if (i % 4 == 0) {
                        tr = $("<tr></tr>");
                        $("#contents").append(tr);
                    }
                    let img = $("<img width='100px'>").attr("src", "https://image.tmdb.org/t/p/w500" + data.profile_path);
                    let td = $("<td></td>");
                    td.append(img);
                    tr.append(td);
                });
            });

            $("#update").on("click", function () {
                let email = $("#email").val();
                let password = $("#password").val();
                let data = {
                    email: email,
                    password: password
                };
                $.ajax({
                    url: "/views/updateUser.jsp",
                    data: data,
                    success: function (response) {
                        if (response === "1") {
                            $("#result").empty();
                            $("#result").append("성공했습니다.");
                        }
                    }
                });
            });

            $("#delete").on("click", function (e) {
                if (confirm("정말로 삭제하시겠습니까?") == false) {
                    e.preventDefault();
                    return false;
                }
                let email = $("#email").val();
                let data = {
                    email: email
                };
                $.ajax({
                    url: "/views/deleteUser.jsp",
                    data: data,
                    success: function (response) {
                        let jsonResponse = JSON.parse(response);
                        if (jsonResponse.re == "1") {
                            window.location.href = "mainPage.do"; // 성공 시 리디렉션
                        } else {
                            $("#result").empty();
                            $("#result").append("삭제 실패했습니다.");
                        }
                    }
                });
            });
        });
    </script>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="profile">
        <img src="./img/dj.jpg" alt="프로필 사진" width="100">
        <h2>${sessionScope.displayName}</h2>
        <p>${email}</p>
        <a href="signOut.do" class="logout-button">로그아웃</a> <!-- 로그아웃 버튼 -->
    </div>

    <div class="stats">
        <div>
            <h3>101</h3>
            <p>평가</p>
        </div>
        <div>
            <h3>57</h3>
            <p>코멘트</p>
        </div>
        <div>
            <h3>1</h3>
            <p>컬렉션</p>
        </div>
    </div>

    <div class="library">
        <div>
            <img src="./img/movie-icon.png" id="myMovie" width="50">
            <p>영화</p>
        </div>
        <div>
            <img src="./img/tv-icon.png" id="myTV" width="50">
            <p>TV 프로그램</p>
        </div>
        <div>
            <img src="./img/person-icon.png" id="myPerson" width="50">
            <p>인물</p>
        </div>
        <div>
            <a href='userCollection.do'><img src="./img/collection-icon.png" id="myCollection" width="50"></a>
            <p>컬렉션</p>
        </div>
        <div>
            <a href='userLikePage.do'><img src="./img/like-icon.png" id="myLike" width="50"></a>
            <p style="margin-top: 7px">좋아요한 목록</p>
        </div>
    </div>
    <hr>
    <div>
        <table id="contents" width='100%' style="text-align: center;">
        </table>
    </div>
</div>
</body>
</html>

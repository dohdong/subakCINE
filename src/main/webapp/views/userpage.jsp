<%@ page import="com.google.gson.Gson" %><%--
  Created by IntelliJ IDEA.
  User: WD
  Date: 2024-07-23
  Time: AM 10:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>수박씨네</title>
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
    </style>
    <script>
        $(function(){
            var movieData=<%= new Gson().toJson(request.getAttribute("popularMovies"))%>;
            var tvData=<%= new Gson().toJson(request.getAttribute("popularTVShows"))%>
            var personData=<%= new Gson().toJson(request.getAttribute("popularPeople"))%>

            $("#myMovie").click(function () {
                $("#contents").empty();
                var tr;
                $.each(movieData,function (i,data){
                    if (i%4==0){
                        tr=$("<tr></tr>");
                        $("#contents").append(tr);
                    }
                    let img=$("<img width='100px'>").attr("src","https://image.tmdb.org/t/p/w500"+data.poster_path);
                    let td=$("<td></td>");
                    td.append(img);
                    tr.append(td);
                })
            })
            $("#myTV").click(function () {
                $("#contents").empty()
                var tr;
                $.each(tvData,function (i,data){
                    if (i%4==0){
                        tr=$("<tr></tr>");
                        $("#contents").append(tr);
                    }
                    let img=$("<img width='100px'>").attr("src","https://image.tmdb.org/t/p/w500"+data.poster_path);
                    let td=$("<td></td>");
                    td.append(img);
                    tr.append(td);
                })
            })
            $("#myPerson").click(function () {
                $("#contents").empty()
                var tr;
                $.each(personData,function (i,data){
                    if (i%4==0){
                        tr=$("<tr></tr>");
                        $("#contents").append(tr);
                    }
                    let img=$("<img width='100px'>").attr("src","https://image.tmdb.org/t/p/w500"+data.profile_path);
                    let td=$("<td></td>");
                    td.append(img);
                    tr.append(td);
                })
            })
            $("#myCollection").click(function () {
                $("#contents").empty()
                let tr = $("<tr></tr>")
                let td1 = $("<td></td>").html("col1")
                let td2 = $("<td></td>").html("col2")
                let td3 = $("<td></td>").html("col3")
                let add = $("<a href='createCollection.do'><div>추가</div></a>")
                $(tr).append(td1, td2, td3);
                $("#contents").append(tr, add);
            })
        })
    </script>
</head>
<body>

<header>
    <div>
        <img src="./img/logo.png" width="120px">
    </div>
    <nav>
        <a href="mainPage.do">영화</a>
        <a href="#">TV 프로그램</a>
        <a href="#">인물</a>
        <a href="#">컬렉션</a>
    </nav>
    <div>
        <input type="text" placeholder="검색">
        <img src="./img/user-icon.png" alt="사용자 아이콘" width="30px">
        <img src="./img/settings-icon.png" alt="설정 아이콘" width="30px">
    </div>
</header>

<div class="profile">
    <img src="./img/dj.jpg" alt="프로필 사진" width="100">
    <h2>이동진</h2>
    <p>lifeisntcool@naver.com</p>
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
        <img src="./img/collection-icon.png" id="myCollection" width="50">
        <p>컬렉션</p>
    </div>
</div>
<div>
    <table id="contents" width="100%" style="text-align: center;">
    </table>
</div>
<footer>
    <p>FOOTER(화면 하단 부분)</p>
</footer>

</body>
</html>

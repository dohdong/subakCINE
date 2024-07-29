<%@ page import="com.google.gson.Gson" %>
<<<<<<< HEAD
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
>>>>>>> 77a8ef967ae49f96d96b57879be2816169dff410
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<<<<<<< HEAD
    <title>수박씨네</title>
    <link rel="stylesheet" href="./css/userPage.css">
    <link rel="stylesheet" href="./css/header.css">
=======
    <link rel="stylesheet" type="text/css" href="./css/userPage.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
>>>>>>> 77a8ef967ae49f96d96b57879be2816169dff410
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(function(){
            var movieData=<%= new Gson().toJson(request.getAttribute("popularMovies"))%>;
            var tvData=<%= new Gson().toJson(request.getAttribute("popularTVShows"))%>;
            var personData=<%= new Gson().toJson(request.getAttribute("popularPerson"))%>;

            $("#myMovie").click(function () {
                $("#contents").empty();
                var tr;
                $.each(movieData,function (i,data){
                    console.log(data);
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
        })
    </script>
</head>
<body>
<<<<<<< HEAD
<jsp:include page="header.jsp" />
=======
<%@ include file="header.jsp" %>
<div class="container">
    <div class="profile">
        <img src="./img/dj.jpg" alt="프로필 사진" width="100">
        <h2>이동진</h2>
        <p>lifeisntcool@naver.com</p>
        <p>${email}</p>
    </div>
>>>>>>> 77a8ef967ae49f96d96b57879be2816169dff410

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
    </div>
    <hr>
    <div>
        <table id="contents" width='100%' style="text-align: center;">
        </table>
    </div>
</div>
<<<<<<< HEAD

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
</div>
<hr>
<div>
    <table id="contents" width='100%' style="text-align: center;">
    </table>
</div>

=======
>>>>>>> 77a8ef967ae49f96d96b57879be2816169dff410
</body>
</html>

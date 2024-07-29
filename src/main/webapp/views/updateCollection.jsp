<%@ page import="com.subakcine.dao.SearchDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.subakcine.vo.CollectionItemVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<CollectionItemVO> itemList = (ArrayList<CollectionItemVO>) request.getAttribute("itemList");
    Gson gson = new Gson();
    String itemListJson = gson.toJson(itemList);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>컬렉션 만들기</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="./css/createCollection.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        let selectedMovies = []; // 선택된 이미지 정보를 저장할 배열
        let itemListJson='<%= itemListJson %>';
        let itemList=JSON.parse(itemListJson);
        $(document).ready(function() {
            itemList.forEach(function(item) {
                selectedMovies.push({
                    id: item.id,
                    media_type: item.type,
                    poster_path: item.movieImgUrl
                });
                let span = $("<span></span>");
                let newImg = $("<img>").attr("src", "https://image.tmdb.org/t/p/w500"+item.movieImgUrl).css({"margin": "10px", "width": "100px", "height": "150px"});
                let deleteBtn = $("<img src='img/remove.png' alt='삭제' style='width: 20px; height: 20px;'>").click(function() {
                    span.remove(); //img 요소 삭제
                    selectedMovies = selectedMovies.filter(m => m.id !== item.id || m.media_type !== 'movie');
                });
                span.append(newImg).append(deleteBtn);
                $(".collection").append(span); // collection에 이미지 추가
                $(".add-movie").appendTo(".collection");
            });
            $('#searchBtn').click(function() {
                let a = $('#searchQuery').val();
                let data = {query:a};
                $.ajax({
                    url: 'views/collectionSearch.jsp',
                    type: 'GET',
                    data: data,
                    success: function(data) {
                        console.log(data.movieSearch);
                        $("#movieResults").empty(); // 결과를 새로 고침하기 위해 비움
                        $("#tvResults").empty(); // TV 결과도 비움
                        //movie부문
                        $.each(data.movieSearch, function (i,movie) {
                            let span = $("<span></span>");
                            let img=$("<img width='100px'>").attr("src","https://image.tmdb.org/t/p/w500"+movie.poster_path);
                            span.append(img);
                            // 클릭 이벤트 추가
                            span.click(function() {
                                let span = $("<span></span>");
                                let newImg = $("<img>").attr("src", "https://image.tmdb.org/t/p/w500"+movie.poster_path).css({"margin": "10px", "width": "100px", "height": "150px"});
                                let deleteBtn = $("<img src='img/remove.png' alt='삭제' style='width: 20px; height: 20px;'>").click(function() {
                                    span.remove(); //img 요소 삭제
                                    selectedMovies = selectedMovies.filter(m => m.id !== movie.id || m.media_type !== 'movie');
                                });
                                span.append(newImg).append(deleteBtn);
                                $(".collection").append(span); // collection에 이미지 추가

                                // 선택된 영화 정보를 JSON 형식으로 저장
                                selectedMovies.push({
                                    id: movie.id,
                                    media_type: 'movie',
                                    poster_path: movie.poster_path
                                });

                                // add-movie 요소를 가장 오른쪽으로 이동
                                $(".add-movie").appendTo(".collection");
                            });

                            $("#movieResults").append(span);
                        });
                        //tv부문
                        $.each(data.tvSearch, function (i,tvShow){
                            let span=$("<span></span>");
                            let img=$("<img width='100px'>").attr("src","https://image.tmdb.org/t/p/w500"+tvShow.poster_path);
                            span.append(img);

                            // 클릭 이벤트 추가
                            span.click(function() {
                                let span = $("<span></span>");
                                let newImg = $("<img>").attr("src", "https://image.tmdb.org/t/p/w500"+tvShow.poster_path).css({"margin": "10px", "width": "100px", "height": "150px"});
                                let deleteBtn = $("<img src='img/remove.png' alt='삭제' style='width: 20px; height: 20px;'>").click(function() {
                                    span.remove();
                                    selectedMovies = selectedMovies.filter(m => m.id !== tvShow.id || m.media_type !== 'tv');
                                });
                                span.append(newImg).append(deleteBtn);
                                $(".collection").append(span); // collection에 이미지 추가
                                // 선택된 TV 프로그램 정보를 JSON 형식으로 저장
                                selectedMovies.push({
                                    id: tvShow.id,
                                    media_type: 'tv',
                                    poster_path: tvShow.poster_path
                                });
                                $(".add-movie").appendTo(".collection");
                            });

                            $("#tvResults").append(span);
                        });

                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.error('Error: ' + textStatus, errorThrown);
                    }
                });
            });
            // '만들기' 버튼 클릭 이벤트
            $('#createBtn').click(function(e) {
                e.preventDefault(); // 기본 링크 동작 방지
                let title = $('#collectionTitle').val(); // 제목 데이터를 가져옴
                let userEmail = $('#userEmail').val(); //유저 이메일 가져옴
                console.log(title);
                if (selectedMovies.length > 0 && title) {
                    // 선택된 영화 정보와 컬렉션 제목을 서버에 전송
                    let dataToSend = {
                        title: title,
                        userEmail : userEmail,
                        movies: selectedMovies
                    };
                    $.ajax({
                        url: 'views/collectionSave.jsp', // 데이터를 저장할 서버 URL
                        type: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify(dataToSend), // 객체를 JSON 문자열로 변환
                        success: function(response) {
                            if (response.trim() === "Success") {
                                alert("컬렉션이 저장되었습니다!");
                                window.location.href = "userCollection.do"; // 리다이렉트
                            } else {
                                console.error('Unexpected response: ' + response);
                            }
                        },
                        error: function(jqXHR, textStatus, errorThrown) {
                            console.error('Error: ' + textStatus, errorThrown);
                        }
                    });
                } else {
                    alert("선택된 영화가 없거나 컬렉션 제목이 없습니다.");
                }
            });
        });

        function openPopup() {
            document.getElementById('moviePopup').style.display = 'block';
        }

        function closePopup() {
            document.getElementById('moviePopup').style.display = 'none';
        }

    </script>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <div class="header2">
        <h1>컬렉션 만들기</h1>
    </div>
    <div class="title">
        <label>컬렉션 이름 : </label>
        <input type="text" id="collectionTitle" placeholder="컬렉션 이름 입력"/>
        <input type="hidden" id="userEmail" value="${email}"/>
    </div>
    <div class="collection">
        <div class="add-movie" onclick="openPopup()"><img id="addBtn" src="img/plus.png"/>
            <span>영화 추가</span></div>
    </div>

    <div class="keywords">
        <div class="keyword">스릴러</div>
        <div class="keyword">감동</div>
        <div class="keyword">행복</div>
        <div class="keyword">네</div>
        <div class="keyword">로맨스</div>
        <div class="keyword">우울</div>
        <div class="keyword">최고</div>
        <div class="keyword">더위</div>
    </div>

    <div>
        <a href="#" id="createBtn">만들기</a>
    </div>
</div>

<div id="moviePopup" class="popup">
    <div class="close" onclick="closePopup()">닫기</div>
    <h2>컬렉션 영화 추가</h2>
    <div class="search-bar">
        <input type="text" id="searchQuery" placeholder="검색어 입력" />
        <button id="searchBtn">검색</button>
    </div>
    <div id="results">
        영화 결과
        <div id="movieResults"></div>
        TV 결과
        <div id="tvResults"></div>
    </div>
</div>
</body>
</html>

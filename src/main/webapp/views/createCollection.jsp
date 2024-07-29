<%@ page import="com.subakcine.dao.SearchDAO" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>컬렉션 만들기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .search-bar {
            position: relative;
        }
        .search-bar input {
            padding: 5px 10px;
            width: 200px;
        }
        .watermark-remove {
            cursor: pointer;
            padding: 5px;
            border: 1px solid #ccc;
        }
        .collection {
            display: flex;
            align-items: center;
            overflow-x: auto;
        }
        .collection img {
            margin: 10px;
            width: 100px;
            height: 150px;
        }
        .add-movie {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 100px;
            height: 150px;
            background-color: #f0f0f0;
            cursor: pointer;
        }
        .keywords {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }
        .keyword {
            padding: 10px 20px;
            border: 1px solid #ccc;
            cursor: pointer;
        }
        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 80%;
            max-width: 600px;
            z-index: 1000;
        }
        .popup .close {
            cursor: pointer;
            color: red;
            float: right;
        }
        .popup .search-bar {
            margin-bottom: 20px;
        }
        .popup #movieResults,#tvResults{
            display: flex;
            flex-wrap: nowrap;
            overflow-x: scroll;
        }


        #createBtn{
            text-align: center;
            text-decoration: none;
            color: white;
            padding: 10px 30px;
            display: inline-block;
            position: relative;
            border: 1px solid rgba(0,0,0,0.21);
            border-bottom: 4px solid rgba(0,0,0,0.21);
            border-radius: 4px;
            text-shadow: 0 1px 0 rgba(0,0,0,0.15);
            background: rgba(27,188,194,1);
            background: -webkit-linear-gradient(rgba(27,188,194,1) 0%, rgba(24,163,168,1) 100%);
            background: -moz-linear-gradient(rgba(27,188,194,1) 0%, rgba(24,163,168,1) 100%);
            background: -o-linear-gradient(rgba(27,188,194,1) 0%, rgba(24,163,168,1) 100%);
            background: linear-gradient(rgba(27,188,194,1) 0%, rgba(24,163,168,1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#1bbcc2', endColorstr='#18a3a8', GradientType=0);
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        let selectedMovies = []; // 선택된 이미지 정보를 저장할 배열
        $(document).ready(function() {
            $('#searchBtn').click(function() {
                let a = $('#searchQuery').val();
                let data = {query:a};
                $.ajax({
                    url: 'views/collectionSearch.jsp',
                    type: 'GET',
                    data: data,
                    // data: { query: query },
                    // dataType: 'json',
                    success: function(data) {
                        console.log(data.movieSearch);
                        $("#movieResults").empty(); // 결과를 새로 고침하기 위해 비움
                        $("#tvResults").empty(); // TV 결과도 비움
                        $.each(data.movieSearch, function (i,movie) {
                            let span = $("<span></span>");
                            let img=$("<img width='100px'>").attr("src","https://image.tmdb.org/t/p/w500"+movie.poster_path);
                            span.append(img);
                            // 클릭 이벤트 추가
                            span.click(function() {
                                let newImg = $("<img>").attr("src", "https://image.tmdb.org/t/p/w500"+movie.poster_path).css({"margin": "10px", "width": "100px", "height": "150px"});
                                $(".collection").append(newImg); // collection에 이미지 추가
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
                        $.each(data.tvSearch, function (i,tvShow){
                            let span=$("<span></span>");
                            let img=$("<img width='100px'>").attr("src","https://image.tmdb.org/t/p/w500"+tvShow.poster_path);
                            span.append(img);

                            // 클릭 이벤트 추가
                            span.click(function() {
                                let newImg = $("<img>").attr("src", "https://image.tmdb.org/t/p/w500"+tvShow.poster_path).css({"margin": "10px", "width": "100px", "height": "150px"});
                                $(".collection").append(newImg);
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
                            alert("컬렉션이 저장되었습니다!");
                            // 필요한 후처리 추가
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
<div class="container">
    <div class="header">
        <h1>컬렉션 만들기</h1>
    </div>
    <div class="title">
        <input type="text" id="collectionTitle" placeholder="컬렉션 이름 입력"/>
        <input type="hidden" id="userEmail" value="${email}"/>
    </div>
    <div class="collection">
        <div class="add-movie" onclick="openPopup()">영화 추가하기</div>
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

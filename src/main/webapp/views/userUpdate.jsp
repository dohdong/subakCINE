<%--
  Created by IntelliJ IDEA.
  User: uhyeo
  Date: 2024-07-28
  Time: PM 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/userPage.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        window.onload = function(){
            let insertFunc=function(){
                let email=$("#email").val();
                let password=$("#password").val();
                let data={
                    email:email,
                    password:password
                }
                $.ajax({
                    url:"/views/updateUser.jsp",
                    data:data,
                    success:function (response){
                        if(response.re=="1"){
                            $("#result").empty();
                            let res="성공했습니다."
                            $("#result").append(res);
                        }
                    }
                })
            }
            $("#update").on("click", insertFunc);
            // $("#delete").onclick = deleteFunc();
        }
    </script>
</head>
<body>
<%@ include file="header.jsp" %>
<div class="container">
    <h1> 내 정보 수정</h1>
    <form action="">
        <table>
            <tr>
                <td>USER EMAIL :</td>
                <td><p>${user.email}</p></td>
            </tr>
            <tr>
                <td>USER PASSWORD :</td>
                <td><input type="text" id="password" name="password" value="${user.password}"></td>
            </tr>
        </table>
        <input type="hidden" name="userID" value="${user.userId}">
        <input type="hidden" name="email" id="email" value=${user.email}>
    </form>
    <input type="button" value="수정하기" id="update">
    <input type="button" value="탈퇴하기" id="delete">
    <div id="result"></div>
</div>
</body>
</html>

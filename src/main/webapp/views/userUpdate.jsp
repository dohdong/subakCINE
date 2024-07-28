<%--
  Created by IntelliJ IDEA.
  User: uhyeo
  Date: 2024-07-28
  Time: PM 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="./css/userPage.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        window.onload = function(){
            $("#update").onclick = function(){
                $("form").submit();
            }
            $("#delete").onclick = function(){

            }
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
                <td>USER ID :</td>
                <td><input type="text" name="userID" value="${user.userID}"></td>
            </tr>
            <tr>
                <td>USER EMAIL :</td>
                <td><input type="text" name="email" value=${user.email}></td>
            </tr>
            <tr>
                <td>USER PASSWORD :</td>
                <td><input type="text" name="password" values="${user.password}"></td>
            </tr>
        </table>
    </form>
    <input type="button" value="수정하기" id="update">
    <input type="button" value="탈퇴하기" id="delete">
</div>
</body>
</html>

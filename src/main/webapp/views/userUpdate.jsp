<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Update</title>
    <link rel="stylesheet" type="text/css" href="css/userUpdate.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        window.onload = function(){
            let insertFunc = function(){
                let email = $("#email").val();
                let password = $("#password").val();
                let data = {
                    email: email,
                    password: password
                }
                $.ajax({
                    url: "/views/updateUser.jsp",
                    data: data,
                    success: function (response){
                        if(response.re == "1"){
                            $("#result").empty();
                            let res = "성공했습니다.";
                            $("#result").append(res);
                        }
                    }
                })
            }

            let deleteFunc = function (){
                let email = $("#email").val();
                let data = {
                    email: email
                }
                $.ajax({
                    url: "/views/deleteUser.jsp",
                    data: data,
                    success: function (response){
                        if(response.re == "1"){
                            $("#result").empty();
                            let res = "성공했습니다.";
                            $("#result").append(res);
                        }
                    }
                })
            }
            $("#update").on("click", insertFunc);
            $("#delete").on("click", function(e){
                if(confirm("정말로 삭제하시겠습니까?") == false){
                    e.preventDefault();
                    return false;
                }
                deleteFunc();
            });
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h1>내 정보 수정</h1>
    <form action="">
        <table>
            <tr>
                <th>USER EMAIL :</th>
                <td><p>${user.email}</p></td>
            </tr>
            <tr>
                <th>USER PASSWORD :</th>
                <td><input type="text" id="password" name="password" value="${user.password}"></td>
            </tr>
        </table>
        <input type="hidden" name="userID" value="${user.userId}">
        <input type="hidden" name="email" id="email" value="${user.email}">
    </form>
    <input type="button" value="수정하기" id="update">
    <input type="button" value="탈퇴하기" id="delete">
    <div id="result"></div>
</div>
</body>
</html>

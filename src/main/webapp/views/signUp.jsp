<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>수박씨네 회원가입</title>
    <link rel="stylesheet" href="css/signUpIn.css" type="text/css">
    <script>
        window.onload = function() {
            <% if (request.getAttribute("re") != null) { %>
            var re = '<%= request.getAttribute("re")%>';
            var msg = '<%= request.getAttribute("msg") %>';
            showAlert(msg);
            console.log(re);
            <% } %>
        };
    </script>
</head>
<body>
<div class="container">
    <img src="img/logo.png" alt="수박씨네 로고">
    <h1>회원가입</h1>
    <form action="signUpOK.do" method="post">
        <input type="email" name="email" placeholder="이메일" required>
        <input type="password" name="password" placeholder="비밀번호" required>
        <button type="submit">회원가입</button>
    </form>
    <div class="login-link">
        이미 가입하셨나요? <a href="signIn.do">로그인</a>
    </div>
</div>
</body>
</html>

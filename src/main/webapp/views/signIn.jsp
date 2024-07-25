
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>수박씨네 로그인</title>
    <link rel="stylesheet" href="css/signUpIn.css" type="text/css">
    <script>
        // JavaScript에서 msg를 alert로 표시하는 함수
        function showAlert(message) {
            alert(message);
        }

        // JSP 태그를 사용하여 조건부로 JavaScript 실행
        window.onload = function() {
            <% if (request.getAttribute("re") != null) { %>
            var msg = '<%= request.getAttribute("msg") %>';
            showAlert(msg);
            <% } %>
        };
    </script>
</head>
<body>
<div class="container">
    <img src="img/logo.png" alt="수박씨네 로고">
    <h1>로그인</h1>
    <form action="signInOK.do" method="post">
        <input type="email" name="email" placeholder="이메일" required>
        <input type="password" name="password" placeholder="비밀번호" required>
        <button type="submit">로그인</button>
    </form>
    <div class="login-link">
        아직 가입을 안하셨나요? <a href="signUp.do">회원가입</a>
    </div>
</div>
</body>
</html>

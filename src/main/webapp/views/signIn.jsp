<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>수박씨네 로그인</title>
    <link rel="stylesheet" href="css/signUpIn.css" type="text/css">
    <script>
        window.onload = function() {
            var urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('msg')) {
                var msg = urlParams.get('msg');
                if (msg === 'success') {
                    alert('회원가입이 완료되었습니다. 로그인해 주세요.');
                }
            }

            // 로그인 실패 메시지 표시
            var loginMsg = '<%= request.getAttribute("msg") != null ? request.getAttribute("msg") : "" %>';
            if (loginMsg) {
                alert(loginMsg);
            }
        }
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

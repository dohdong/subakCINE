<%--
  Created by IntelliJ IDEA.
  User: WD
  Date: 2024-07-23
  Time: AM 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>회원가입 완료</title>
</head>
<body>
<c:if test="${re>0}">
    회원가입 성공
    <hr>
    <a href="signIn.do">로그인</a>
</c:if>
<c:if test="${re<=0}">
    회원가입 실패
</c:if>
</body>
</html>

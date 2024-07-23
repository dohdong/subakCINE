<%--
  Created by IntelliJ IDEA.
  User: WD
  Date: 2024-07-23
  Time: PM 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>로그인 결과</title>
</head>
<body>
    <c:if test="${re==1}">
        로그인 완료!
    </c:if>
    <c:if test="${re==0}">
        비밀번호가 틀렸습니다.
    </c:if>
    <c:if test="${re==-1}">
        이메일이 존재하지 않습니다.
    </c:if>
</body>
</html>

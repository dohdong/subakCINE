<%@ page import="com.subakcine.vo.UserVO"%>
<%@ page import="com.subakcine.dao.UserDAO"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String email=request.getParameter("email");
    String password=request.getParameter("password");
    String userId=request.getParameter("userId");

    UserVO uservo=new UserVO(email,password);
    uservo.setEmail(email);
    UserDAO userDAO=new UserDAO();
    int re=userDAO.update(uservo);

    Gson gson=new Gson();
    String result=gson.toJson(re);
%>
<%=result%>
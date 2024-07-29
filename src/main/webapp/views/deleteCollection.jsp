<%@ page import="com.subakcine.vo.UserVO"%>
<%@ page import="com.subakcine.dao.UserDAO"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%><%@ page import="com.subakcine.dao.CollectionDAO"%>
<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
    String collectionId=(String)request.getParameter("collectionId");
    CollectionDAO collectionDAO=new CollectionDAO();
    int re=collectionDAO.delete(collectionId);
    Gson gson=new Gson();
    String result=gson.toJson(re);
%>
<%=result%>
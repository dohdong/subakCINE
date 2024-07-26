<%@ page import="com.google.gson.Gson" %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.apache.catalina.security.DeployXmlPermission"%>
<%@ page import="com.subakcine.dao.SearchDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%><%@ page import="java.util.ArrayList"%><%@ page import="java.util.HashMap"%>

<%
request.setCharacterEncoding("UTF-8");
SearchDAO searchDao = new SearchDAO();
String query = request.getParameter("query");
Gson gson = new Gson();
List<Map<String,Object>> searchResults = searchDao.search(query);
List<Map<String, Object>> movies = new ArrayList<>();
List<Map<String, Object>> tvShows = new ArrayList<>();

for (Map<String, Object> result : searchResults) {
    String mediaType = (String) result.get("media_type");
    if ("movie".equals(mediaType)) {
        movies.add(result);
    } else if ("tv".equals(mediaType)) {
        tvShows.add(result);
    }
}
// JSON 형태로 결과를 반환
Map<String, Object> responseMap = new HashMap<>();
responseMap.put("movieSearch", movies);
responseMap.put("tvSearch", tvShows);

String jsonResponse = gson.toJson(responseMap);
response.setContentType("application/json");
response.getWriter().write(jsonResponse);
//System.out.println(query);
//System.out.println(movies.size());
//System.out.println(responseMap);
%>

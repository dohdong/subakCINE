<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.List"%>
<%@ page import="com.subakcine.vo.CollectionVO"%>
<%@ page import="com.subakcine.dao.CollectionDAO"%>
<%@ page import="java.io.BufferedReader"%><%@ page import="com.subakcine.vo.CollectionItemVO"%>
<%@ page import="com.subakcine.vo.CollectionSelectVO"%>
<%@ page import="java.io.*, javax.servlet.*, javax.servlet.http.*, org.json.*, java.util.*" %><%@ page import="com.subakcine.dao.UserDAO"%><%@ page import="com.subakcine.vo.UserVO"%><%@ page import="com.google.gson.JsonObject"%><%@ page import="com.subakcine.dao.CollectionItemDAO"%>
<%
    // 요청과 응답의 인코딩 설정
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    // 요청의 내용을 문자열로 읽기
    StringBuilder sb = new StringBuilder();
    BufferedReader reader = request.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
        sb.append(line);
    }
    System.out.println("sb.toString => "+sb.toString());
    // JSON 파싱
    String jsonString = sb.toString();
    JSONObject jsonObject = new JSONObject(jsonString);

    // title을 String으로 추출
    String title = jsonObject.getString("title");
    String userEmail = jsonObject.getString("userEmail");
    System.out.println(title);
    System.out.println(userEmail);
    System.out.println("===========================================");
    UserDAO userDao = new UserDAO();
    UserVO userVo = new UserVO();
    userVo = userDao.getUserByEmail(userEmail);
    String userId = userVo.getUserId();
    CollectionDAO collectionDao = new CollectionDAO();
    String collectionId = collectionDao.addCollection(title,userId);
    if(collectionId!=null){
        System.out.println("userId -> "+userId);
        System.out.println("collectionDAO 성공 ! success");
        System.out.println(collectionId);

    }else {
        System.out.println("collectionDAO 성공 ! fail");
    }

    // selectedMovies를 배열로 저장
    JSONArray moviesArray = jsonObject.getJSONArray("movies");
    System.out.println(moviesArray.toString());
    System.out.println("몇개? -> "+moviesArray.length());
    System.out.println("CollectionId => "+collectionId);
    System.out.println("=====================2======================");
    for (int i = 0; i < moviesArray.length(); i++) {
        System.out.println(moviesArray.get(i));
        JSONObject movieJson = moviesArray.getJSONObject(i);
        int itemOrder = i+1;
        String itemId = String.valueOf(movieJson.get("id"));
        String mediaType = movieJson.getString("media_type");
        System.out.println(itemOrder+", "+collectionId+", "+itemId+", "+mediaType);
        CollectionItemDAO collectionItemDao = new CollectionItemDAO();
        int re = collectionItemDao.addCollectionItem(itemOrder,collectionId,itemId,mediaType);
        if(re>0){
            System.out.println("success");
        }else {
            System.out.println("fail");
        }
    }

    // 응답을 클라이언트에 전송
    response.getWriter().write("Success");

//    // 성공적인 응답
//    response.setStatus(200);
//    out.print("{\"message\":\"컬렉션이 성공적으로 저장되었습니다.\"}");
%>
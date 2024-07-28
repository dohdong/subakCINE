<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="java.util.List"%>
<%@ page import="com.subakcine.vo.CollectionVO"%>
<%@ page import="com.subakcine.dao.CollectionDAO"%>
<%@ page import="java.io.BufferedReader"%><%@ page import="com.subakcine.vo.CollectionItemVO"%><%@ page import="com.subakcine.vo.CollectionSelectVO"%>

<%
// JSON 데이터를 받을 BufferedReader 생성
    BufferedReader reader = request.getReader();
    StringBuilder jsonBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        jsonBuilder.append(line);
    }
    System.out.println(jsonBuilder.toString());
    // Gson을 사용하여 JSON 데이터를 CollectionVO 객체 리스트로 변환
    Gson gson = new Gson();
    List<CollectionSelectVO> selectedMovies = gson.fromJson(jsonBuilder.toString(), new com.google.gson.reflect.TypeToken<List<CollectionSelectVO>>(){}.getType());
    System.out.println(selectedMovies.size());
    System.out.println(selectedMovies);
    System.out.println("=====================================================");
    // 이제 selectedMovies 리스트를 사용하여 필요한 작업을 수행
    // 예: DB에 저장
    for (CollectionSelectVO movie : selectedMovies) {
//        System.out.println(movie.toString());

        // movie.getId(), movie.getMediaType(), movie.getPosterPath() 등을 사용
    }

//    // 응답을 클라이언트에 전송
//    response.getWriter().write("Success");
//
//=============================================================================================
//    // JSON 데이터를 받을 객체
//    Gson gson = new Gson();
//    StringBuilder jsonBuilder = new StringBuilder();
//    String line;
//
//    // 요청 본문 읽기
//    java.io.BufferedReader reader = request.getReader();
//    while ((line = reader.readLine()) != null) {
//        jsonBuilder.append(line);
//    }
//    System.out.println(jsonBuilder);
//
//    // JSON 문자열을 CollectionVO 리스트로 변환
//    List<CollectionVO> collectionList = gson.fromJson(jsonBuilder.toString(),
//        new com.google.gson.reflect.TypeToken<List<CollectionVO>>(){}.getType());
//
//    // 각 속성을 변수에 저장
//    for (CollectionVO collection : collectionList) {
//        String title = collection.getTitle();        // 영화 제목
//        String posterPath = collection.getPosterPath(); // 포스터 경로
//        String releaseDate = collection.getReleaseDate(); // 개봉 날짜
//
//        // 변수 값 출력 (선택 사항)
//        System.out.println("제목: " + title);
//        System.out.println("포스터 경로: " + posterPath);
//        System.out.println("개봉 날짜: " + releaseDate);
//    }
//    // 성공적인 응답
//    response.setStatus(200);
//    out.print("{\"message\":\"컬렉션이 성공적으로 저장되었습니다.\"}");
%>
package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * MovieDAO 클래스는 영화 데이터와 관련된 작업을 처리합니다.
 * TMDb API를 통해 영화 데이터를 가져오고, 컬렉션 및 좋아요 기능을 제공합니다.
 */
public class MovieDAO {
    // TMDb API 클라이언트를 사용하여 영화 데이터를 가져오는 객체
    private TmdbApiClient apiClient = new TmdbApiClient();

    // JSON 데이터를 Java 객체로 변환하기 위한 ObjectMapper 인스턴스
    private ObjectMapper objectMapper = new ObjectMapper();

    // 좋아요 기능을 처리하는 DAO 객체
    private LikeItemDAO likeItemDAO = new LikeItemDAO();

    // 컬렉션에 아이템을 추가하는 기능을 처리하는 DAO 객체
    private CollectionItemDAO collectionItemDAO = new CollectionItemDAO();

    /**
     * TMDb API를 통해 인기 있는 영화 목록을 가져옵니다.
     *
     * @return 인기 있는 영화 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getPopularMovies() throws IOException {
        // TMDb API 클라이언트를 통해 인기 있는 영화 목록을 JSON 형식으로 가져옵니다.
        String jsonResponse = apiClient.getPopularMovies();

        // JSON 응답을 Map 객체로 변환합니다.
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // "results" 키의 값을 List<Map<String, Object>> 형태로 반환합니다.
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 특정 영화의 세부 정보를 가져옵니다.
     *
     * @param movieId 영화 ID
     * @return 영화 세부 정보를 포함한 Map 객체
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public Map<String, Object> getMovieDetails(String movieId) throws IOException {
        // TMDb API 클라이언트를 통해 특정 영화의 세부 정보를 JSON 형식으로 가져옵니다.
        String jsonResponse = apiClient.getMovieDetails(movieId);

        // JSON 응답을 Map 객체로 변환합니다.
        Map<String, Object> movieDetails = objectMapper.readValue(jsonResponse, Map.class);

        // 미디어 타입을 "movie"로 설정합니다.
        movieDetails.put("media_type", "movie");

        // 영화 세부 정보를 반환합니다.
        return movieDetails;
    }

    /**
     * TMDb API를 통해 현재 상영 중인 영화 목록을 가져옵니다.
     *
     * @return 현재 상영 중인 영화 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getNowPlayingMovies() throws IOException {
        // TMDb API 클라이언트를 통해 현재 상영 중인 영화 목록을 JSON 형식으로 가져옵니다.
        String jsonResponse = apiClient.getNowPlayingMovies();

        // JSON 응답을 Map 객체로 변환합니다.
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // "results" 키의 값을 List<Map<String, Object>> 형태로 반환합니다.
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 상영 예정인 영화 목록을 가져옵니다.
     *
     * @return 상영 예정인 영화 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getUpcomingMovies() throws IOException {
        // TMDb API 클라이언트를 통해 상영 예정인 영화 목록을 JSON 형식으로 가져옵니다.
        String jsonResponse = apiClient.getUpcomingMovies();

        // JSON 응답을 Map 객체로 변환합니다.
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // "results" 키의 값을 List<Map<String, Object>> 형태로 반환합니다.
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 최고 평점을 받은 영화 목록을 가져옵니다.
     *
     * @return 최고 평점을 받은 영화 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getTopRatedMovies() throws IOException {
        // TMDb API 클라이언트를 통해 최고 평점을 받은 영화 목록을 JSON 형식으로 가져옵니다.
        String jsonResponse = apiClient.getTopRatedMovies();

        // JSON 응답을 Map 객체로 변환합니다.
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // "results" 키의 값을 List<Map<String, Object>> 형태로 반환합니다.
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * 영화 정보를 컬렉션에 추가합니다.
     *
     * @param collectionId 컬렉션 아이디
     * @param itemId 컨텐츠 아이템의 아이디(영화 아이디 또는 TV 아이디)
     * @param itemType 컨텐츠 아이템의 타입(영화 또는 TV)
     * @return 컬렉션 추가 성공 여부
     */
    public boolean addToCollection(String collectionId, String itemId, String itemType) {
        // CollectionItemDAO를 통해 컬렉션에 아이템을 추가합니다.
        return collectionItemDAO.addToCollection(collectionId, itemId, itemType);
    }

    /**
     * 영화를 좋아요 목록에 추가합니다.
     *
     * @param itemId 컨텐츠 아이템의 아이디(영화 아이디 또는 TV 아이디 또는 인물 아이디)
     * @param userId 좋아요를 누른 유저의 아이디
     * @param itemType 컨텐츠 아이템의 타입(영화, TV, 인물)
     * @return 좋아요 추가 성공 여부
     */
    public boolean likeMovie(String itemId, String userId, String itemType) {
        // LikeItemDAO를 통해 좋아요를 추가합니다.
        return likeItemDAO.likeItem(itemId, userId, itemType);
    }
}
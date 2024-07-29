package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.ConnectionProvider;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * TVShowDAO 클래스는 TV 쇼 데이터와 관련된 작업을 처리합니다.
 * TMDb API를 통해 TV 쇼 데이터를 가져오고, 컬렉션 및 좋아요 기능을 제공합니다.
 */
public class TVShowDAO {
    // TMDb API 클라이언트를 사용하여 TV 쇼 데이터를 가져오는 객체
    private TmdbApiClient apiClient = new TmdbApiClient();

    // JSON 데이터를 Java 객체로 변환하기 위한 ObjectMapper 인스턴스
    private ObjectMapper objectMapper = new ObjectMapper();

    // 좋아요 기능을 처리하는 DAO 객체
    private LikeItemDAO likeItemDAO = new LikeItemDAO();

    // 컬렉션에 아이템을 추가하는 기능을 처리하는 DAO 객체
    private CollectionItemDAO collectionItemDAO = new CollectionItemDAO();

    /**
     * TMDb API를 통해 인기 있는 TV 쇼 목록을 가져옵니다.
     *
     * @return 인기 있는 TV 쇼 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getPopularTVShows() throws IOException {
        // API 클라이언트를 사용하여 인기 있는 TV 쇼 목록에 대한 JSON 응답을 가져옴
        String jsonResponse = apiClient.getPopularTVShows();

        // JSON 응답을 Map 객체로 변환
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // Map 객체에서 "results" 키에 해당하는 값을 List<Map<String, Object>>로 캐스팅하여 반환
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 오늘 방송되는 TV 쇼 목록을 가져옵니다.
     *
     * @return 오늘 방송되는 TV 쇼 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getAiringTodayTVShows() throws IOException {
        // API 클라이언트를 사용하여 오늘 방송되는 TV 쇼 목록에 대한 JSON 응답을 가져옴
        String jsonResponse = apiClient.getAiringTodayTVShows();

        // JSON 응답을 Map 객체로 변환
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // Map 객체에서 "results" 키에 해당하는 값을 List<Map<String, Object>>로 캐스팅하여 반환
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 현재 방송 중인 TV 쇼 목록을 가져옵니다.
     *
     * @return 현재 방송 중인 TV 쇼 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getOnTheAirTVShows() throws IOException {
        // API 클라이언트를 사용하여 현재 방송 중인 TV 쇼 목록에 대한 JSON 응답을 가져옴
        String jsonResponse = apiClient.getOnTheAirTVShows();

        // JSON 응답을 Map 객체로 변환
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // Map 객체에서 "results" 키에 해당하는 값을 List<Map<String, Object>>로 캐스팅하여 반환
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 최고 평점을 받은 TV 쇼 목록을 가져옵니다.
     *
     * @return 최고 평점을 받은 TV 쇼 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getTopRatedTVShows() throws IOException {
        // API 클라이언트를 사용하여 최고 평점을 받은 TV 쇼 목록에 대한 JSON 응답을 가져옴
        String jsonResponse = apiClient.getTopRatedTVShows();

        // JSON 응답을 Map 객체로 변환
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // Map 객체에서 "results" 키에 해당하는 값을 List<Map<String, Object>>로 캐스팅하여 반환
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 특정 TV 쇼의 세부 정보를 가져옵니다.
     *
     * @param tvShowId TV 쇼 ID
     * @return TV 쇼 세부 정보를 포함한 Map 객체
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public Map<String, Object> getTVShowDetails(String tvShowId) throws IOException {
        // API 클라이언트를 사용하여 특정 TV 쇼의 세부 정보에 대한 JSON 응답을 가져옴
        String jsonResponse = apiClient.getTVShowDetails(tvShowId);

        // JSON 응답을 Map<String, Object>로 변환
        Map<String, Object> tvShowDetails = objectMapper.readValue(jsonResponse, Map.class);

        // Map 객체에 media_type 키 추가
        tvShowDetails.put("media_type", "tv");

        // 변환된 Map 객체 반환
        return tvShowDetails;
    }

    /**
     * TV 쇼 정보를 컬렉션에 추가합니다.
     *
     * @param collectionId 컬렉션 아이디
     * @param itemId TV 쇼 ID
     * @param itemType TV 쇼 아이템 타입
     * @return 컬렉션 추가 성공 여부
     */
    public boolean addToCollection(String collectionId, String itemId, String itemType) {
        return collectionItemDAO.addToCollection(collectionId, itemId, itemType);
    }

    /**
     * TV 쇼를 좋아요 목록에 추가합니다.
     *
     * @param itemId TV 쇼 ID
     * @param userId 사용자 ID
     * @param itemType TV 쇼 아이템 타입
     * @return 좋아요 추가 성공 여부
     */
    public boolean likeTVShow(String itemId, String userId, String itemType) {
        return likeItemDAO.likeItem(itemId, userId, itemType);
    }

    /**
     * TV 쇼의 좋아요 상태를 토글합니다.
     *
     * @param tvShowId TV 쇼 ID
     * @param usersID 사용자 ID
     * @param itemType 아이템 타입
     * @return 좋아요 토글 성공 여부
     */
    public boolean toggleLikeTVShow(String tvShowId, String usersID, String itemType) {
        if (usersID == null) {
            return false; // 사용자 ID가 null인 경우 처리하지 않음
        }

        String checkSql = "SELECT COUNT(*) FROM LIKES WHERE ITEM_ID = ? AND USERS_ID = ?";
        String insertSql = "INSERT INTO LIKES (ITEM_ID, USERS_ID, ITEM_TYPE) VALUES (?, ?, ?)";
        String deleteSql = "DELETE FROM LIKES WHERE ITEM_ID = ? AND USERS_ID = ?";

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {

            // 현재 좋아요 상태 확인
            checkStmt.setString(1, tvShowId);
            checkStmt.setString(2, usersID);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // 이미 좋아요를 눌렀으면 좋아요 취소
                deleteStmt.setString(1, tvShowId);
                deleteStmt.setString(2, usersID);
                deleteStmt.executeUpdate();
            } else {
                // 좋아요 추가
                insertStmt.setString(1, tvShowId);
                insertStmt.setString(2, usersID);
                insertStmt.setString(3, itemType);
                insertStmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

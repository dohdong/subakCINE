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
 * PersonDAO 클래스는 인물 데이터와 관련된 작업을 처리합니다.
 * TMDb API를 통해 인물 데이터를 가져오고, 컬렉션 및 좋아요 기능을 제공합니다.
 */
public class PersonDAO {
    // TMDb API 클라이언트를 사용하여 인물 데이터를 가져오는 객체
    private TmdbApiClient apiClient = new TmdbApiClient();

    // JSON 데이터를 Java 객체로 변환하기 위한 ObjectMapper 인스턴스
    private ObjectMapper objectMapper = new ObjectMapper();

    // 좋아요 기능을 처리하는 DAO 객체
    private LikeItemDAO likeItemDAO = new LikeItemDAO();

    // 컬렉션에 아이템을 추가하는 기능을 처리하는 DAO 객체
    private CollectionItemDAO collectionItemDAO = new CollectionItemDAO();

    /**
     * TMDb API를 통해 인기 있는 인물 목록을 가져옵니다.
     *
     * @return 인기 있는 인물 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> getPopularPerson() throws IOException {
        // TMDb API 클라이언트를 통해 인기 있는 인물 목록을 JSON 형식으로 가져옵니다.
        String jsonResponse = apiClient.getPopularPerson();

        // JSON 응답을 Map 객체로 변환합니다.
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // "results" 키의 값을 List<Map<String, Object>> 형태로 반환합니다.
        return (List<Map<String, Object>>) result.get("results");
    }

    /**
     * TMDb API를 통해 특정 인물의 세부 정보를 가져옵니다.
     *
     * @param personId 인물 ID
     * @return 인물 세부 정보를 포함한 Map 객체
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public Map<String, Object> getPopularPersonDetails(String personId) throws IOException {
        // TMDb API 클라이언트를 통해 특정 인물의 세부 정보를 JSON 형식으로 가져옵니다.
        String jsonResponse = apiClient.getPopularPersonDetails(personId);

        // JSON 응답을 Map 객체로 변환합니다.
        Map<String, Object> personDetails = objectMapper.readValue(jsonResponse, Map.class);

        // 미디어 타입을 "person"으로 설정합니다.
        personDetails.put("media_type", "person");

        // 인물 세부 정보를 반환합니다.
        return personDetails;
    }

    /**
     * 인물 정보를 컬렉션에 추가합니다.
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
     * 인물을 좋아요 목록에 추가합니다.
     *
     * @param itemId 컨텐츠 아이템의 아이디(영화 아이디 또는 TV 아이디 또는 인물 아이디)
     * @param userId 좋아요를 누른 유저의 아이디
     * @param itemType 컨텐츠 아이템의 타입(영화, TV, 인물)
     * @return 좋아요 추가 성공 여부
     */
    public boolean likePerson(String itemId, String userId, String itemType) {
        // LikeItemDAO를 통해 좋아요를 추가합니다.
        return likeItemDAO.likeItem(itemId, userId, itemType);
    }

    /**
     * 인물의 좋아요 상태를 토글합니다.
     *
     * @param personId 인물 ID
     * @param usersID 사용자 ID
     * @param itemType 아이템 타입
     * @return 좋아요 토글 성공 여부
     */
    public boolean toggleLikePerson(String personId, String usersID, String itemType) {
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
            checkStmt.setString(1, personId);
            checkStmt.setString(2, usersID);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // 이미 좋아요를 눌렀으면 좋아요 취소
                deleteStmt.setString(1, personId);
                deleteStmt.setString(2, usersID);
                deleteStmt.executeUpdate();
            } else {
                // 좋아요 추가
                insertStmt.setString(1, personId);
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

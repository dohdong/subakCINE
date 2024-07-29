package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.ConnectionProvider;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LikeItemDAO {
    // TMDb API 클라이언트를 사용하여 영화 데이터를 가져오는 객체
    private TmdbApiClient apiClient = new TmdbApiClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 좋아요를 추가합니다.
     *
     * @param itemId 컨텐츠 아이템의 아이디(영화 아이디 또는 TV 아이디 또는 인물 아이디)
     * @param userId 좋아요를 누른 유저의 아이디
     * @param itemType 컨텐츠 아이템의 타입(영화, TV, 인물)
     * @return 좋아요 추가 성공 여부
     */
    public boolean likeItem(String itemId, String userId, String itemType) {
        String sql = "INSERT INTO LIKES (ITEM_ID, USERS_ID, ITEM_TYPE) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, itemId);
            pstmt.setString(2, userId);
            pstmt.setString(3, itemType);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 특정 사용자와 아이템 타입에 대한 좋아요한 아이템들을 가져옵니다.
     *
     * @param usersID 사용자의 아이디.
     * @param itemType 아이템 타입 (movie, tv, person).
     * @return 좋아요한 아이템들의 리스트.
     */
    public List<Map<String, Object>> getLikedItems(String usersID, String itemType) {
        List<Map<String, Object>> likedItems = new ArrayList<>();
        String sql = "SELECT ITEM_ID FROM LIKES WHERE USERS_ID = ? AND ITEM_TYPE = ?";

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usersID);
            pstmt.setString(2, itemType);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String itemId = rs.getString("ITEM_ID");
                    Map<String, Object> itemDetails = getItemDetails(itemId, itemType);
                    likedItems.add(itemDetails);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return likedItems;
    }

    /**
     * 아이템의 상세 정보를 가져옵니다.
     *
     * @param itemId 아이템 ID.
     * @param itemType 아이템 타입 (movie, tv, person).
     * @return 아이템의 상세 정보가 담긴 Map 객체.
     */
    private Map<String, Object> getItemDetails(String itemId, String itemType) throws IOException {
        Map<String, Object> itemDetails = new HashMap<>();
        String jsonResponse = "";

        switch (itemType) {
            case "movie":
                jsonResponse = apiClient.getMovieDetails(itemId);
                break;
            case "tv":
                jsonResponse = apiClient.getTVShowDetails(itemId);
                break;
            case "person":
                jsonResponse = apiClient.getPopularPersonDetails(itemId);
                break;
        }

        itemDetails = objectMapper.readValue(jsonResponse, Map.class);
        return itemDetails;
    }
}

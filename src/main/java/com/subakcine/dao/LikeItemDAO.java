package com.subakcine.dao;

import com.subakcine.db.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LikeItemDAO {

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
}

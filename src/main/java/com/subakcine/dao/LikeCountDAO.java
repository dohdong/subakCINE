package com.subakcine.dao;

import com.subakcine.db.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeCountDAO {

    /**
     * 특정 아이템에 대한 좋아요 수를 조회합니다.
     *
     * @param itemId  아이템의 ID (영화 ID 또는 TV 쇼 ID).
     * @param itemType 아이템의 타입 (영화, TV).
     * @return 해당 아이템의 좋아요 수.
     */
    public int getLikeCount(String itemId, String itemType) {
        String sql = "SELECT COUNT(*) FROM LIKES WHERE ITEM_ID = ? AND ITEM_TYPE = ?";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, itemId);
            pstmt.setString(2, itemType);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

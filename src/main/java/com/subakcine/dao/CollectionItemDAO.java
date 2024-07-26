package com.subakcine.dao;

import com.subakcine.db.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CollectionItemDAO {

    /**
     * 아이템을 컬렉션에 추가합니다.
     *
     * @param collectionId 컬렉션 아이디
     * @param itemId 컨텐츠 아이템의 아이디(영화 아이디 또는 TV 아이디 또는 인물 아이디)
     * @param itemType 컨텐츠 아이템의 타입(영화, TV, 인물)
     * @return 컬렉션 추가 성공 여부
     */
    public boolean addToCollection(String collectionId, String itemId, String itemType) {
        String sql = "INSERT INTO COLLECTION_ITEM (COLLECTION_ID, ITEM_ID, ITEM_TYPE) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, collectionId);
            pstmt.setString(2, itemId);
            pstmt.setString(3, itemType);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
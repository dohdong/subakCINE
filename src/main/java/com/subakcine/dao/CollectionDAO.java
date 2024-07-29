package com.subakcine.dao;

import com.subakcine.db.ConnectionProvider;
import com.subakcine.vo.CollectionVO;
import com.subakcine.vo.CollectionItemVO;


import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class CollectionDAO {
    //모든 컬렉션 반환
    public ArrayList<CollectionVO> allCollections(){
        String sql = "select COLLECTION_ID,COLLECTION_NAME,COLLECTION_CREATE_DATE,COLLECTION_UPDATE_DATE,USERS_ID from collection";
        ArrayList<CollectionVO> list = new ArrayList<>();
        try{
            Connection conn = ConnectionProvider.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                CollectionVO collectionVo = new CollectionVO();
                collectionVo.setCollectionId(rs.getString("COLLECTION_ID"));
                collectionVo.setCollectionName(rs.getString("COLLECTION_NAME"));
                collectionVo.setCollectionCreateDate(rs.getDate("COLLECTION_CREATE_DATE"));
                collectionVo.setCollectionUpdateDate(rs.getDate("COLLECTION_UPDATE_DATE"));
                collectionVo.setUserID(rs.getString("USERS_ID"));
                list.add(collectionVo);
            }
        }catch (Exception e){
            System.out.println("CollectionDAO allCollections 예외 ==> "+e.getMessage());

        }
        return list;
    }
    //컬렉션 삭제
    public int delete(String collectionId) {
        String sql = "DELETE COLLECTION WHERE COLLECTION_ID=?";
        int re=-1;
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"COLLECTION_ID"});
            pstmt.setString(1, collectionId);
            re=pstmt.executeUpdate();
            ConnectionProvider.close(pstmt, conn);
        } catch (Exception e) {
            System.out.println("CollectionDao delete 예외==> " + e.getMessage());
        }
        return re;
    }
    //컬렉션 추가
    public String addCollection(String collectionName, String userId) {
        String sql = "INSERT INTO COLLECTION(COLLECTION_NAME, USERS_ID) VALUES(?, ?)";
        String collectionId = "";
        try {
            Connection conn = ConnectionProvider.getConnection();
            // Prepare statement with RETURN_GENERATED_KEYS
            PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"COLLECTION_ID"});
            pstmt.setString(1, collectionName);
            pstmt.setString(2, userId);
            pstmt.executeUpdate();

            // Get generated keys
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                collectionId = rs.getString(1);  // Get the first generated key (collection_id)
            }

            ConnectionProvider.close(rs, pstmt, conn);
        } catch (Exception e) {
            System.out.println("CollectionDao addCollection 예외==> " + e.getMessage());
        }

        return collectionId;
    }

    // collection id를 통해 해당 collection을 찾아서 반환
    public CollectionVO collectionDetail(String collectionId){
        CollectionVO collection=new CollectionVO();
        String sql="SELECT COLLECTION_ID,COLLECTION_NAME,COLLECTION_CREATE_DATE,COLLECTION_UPDATE_DATE,USERS_ID FROM collection WHERE COLLECTION_ID=?";
        try {
            Connection conn=ConnectionProvider.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, collectionId);
            ResultSet rs=ps.executeQuery();
            if (rs.next()){
                collection.setCollectionId(rs.getString("COLLECTION_ID"));
                collection.setCollectionName(rs.getString("COLLECTION_NAME"));
                collection.setCollectionCreateDate(rs.getDate("COLLECTION_CREATE_DATE"));
                collection.setCollectionUpdateDate(rs.getDate("COLLECTION_UPDATE_DATE"));
            }
            ConnectionProvider.close(rs, ps, conn);
        }catch(Exception e){
            e.printStackTrace();
        }

        return collection;
    }

    // 컬렉션 내부의 영화들을 반환
    // 아이템 테이블이 없기 때문에 action에서 id를 이용해서 api에서 찾아온다
    public ArrayList<CollectionItemVO> listCollectionItems(String collectionId) {
        String sql = "SELECT COLLECTION_ITEM_ID,COLLECTION_ITEM_ORDER,COLLECTION_ITEM_CREATE_DATE,COLLECTION_ITEM_UPDATE_DATE,COLLECTION_ID,ITEM_ID,ITEM_TYPE FROM COLLECTION_ITEM WHERE collection_id=? ORDER BY collection_item_order";
        ArrayList<CollectionItemVO> list=new ArrayList<CollectionItemVO>();
        CollectionItemVO vo;
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, collectionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vo=new CollectionItemVO();
                vo.setOrder(rs.getInt("COLLECTION_ITEM_ORDER"));
                vo.setId(rs.getString("ITEM_ID"));
                vo.setType(rs.getString("ITEM_TYPE"));
                System.out.println("vo : "+vo);
                list.add(vo);
            }
            ConnectionProvider.close(rs, ps, conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    //해당하는 유저의 collection list(id, name)를 반환
    public ArrayList<CollectionVO> listCollections(String userid) {
        String sql="SELECT COLLECTION_ID,COLLECTION_NAME,COLLECTION_CREATE_DATE,COLLECTION_UPDATE_DATE FROM collection WHERE users_id=? ORDER BY COLLECTION_CREATE_DATE";
        CollectionVO vo=null;
        ArrayList<CollectionVO> list=new ArrayList<CollectionVO>();
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                vo=new CollectionVO();
                vo.setCollectionId(rs.getString(1));
                vo.setCollectionName(rs.getString(2));
                list.add(vo);
            }
            ConnectionProvider.close(rs, ps, conn);
        }catch(Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }

}

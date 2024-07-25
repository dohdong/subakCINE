package com.subakcine.dao;

import com.subakcine.db.ConnectionProvider;
import com.subakcine.vo.CollectionVO;

import java.sql.*;
import java.util.ArrayList;

public class CollectionDAO {
    // collection count 유저컬렉션의 개수를 센다
    public int countCollections(String userid) {
        String sql="select count(*) from collection where users_id=?";
        int count=0;
        try {
            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count=rs.getInt(1);
            }
        }catch(Exception e){
            System.out.println("e = " + e);
        }
        return count;
    }

    //해당하는 유저의 collection list를 반환
    public ArrayList<CollectionVO> listCollections(String userid) {
        String sql="select collection_id,collection_name from collection where users_id=?";
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

    // userID를 이용해서 collection list를 반환한다(id, name)
    public ArrayList<CollectionVO> findByUser(String userid){
        ArrayList<CollectionVO> list=new ArrayList<CollectionVO>();
        String sql="SELECT c.collection_id,c.collection_name FROM COLLECTION c, COLLECTION_ITEM ci WHERE c.COLLECTION_ID=ci.COLLECTION_ID AND users_id=?";
        try {
            Connection conn= ConnectionProvider.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                CollectionVO vo=new CollectionVO();
                vo.setCollectionId(rs.getString(1));
                vo.setCollectionName(rs.getString(2));
                list.add(vo);
            }
            ConnectionProvider.close(rs, ps, conn);
        }catch (Exception e){
            System.out.println("예외 발생 : "+e.getMessage());
        }
        return list;
    }
    // userID를 이용해서 collection_id에 해당하는 image_url_list를 반환한다
    public ArrayList<ArrayList<String>> findImageByCollection(String userid){
        ArrayList<ArrayList<String>> collectionImagelist=new ArrayList<ArrayList<String>>();
        ArrayList<String> list=null;
        String sql="SELECT c.collection_id,ci.movie_image_url FROM COLLECTION c, COLLECTION_ITEM ci WHERE c.COLLECTION_ID=ci.COLLECTION_ID AND users_id=?";
        try {
            Connection conn= ConnectionProvider.getConnection();
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setString(1, userid);
            ResultSet rs=ps.executeQuery();
//            int i=0;
            String prevCollectionId=null;
            boolean isFirstRecord=true;
            while(rs.next()){
                String currentCollectionId=rs.getString(1);
                String posterUrl=rs.getString(2);
                if (isFirstRecord||!currentCollectionId.equals(prevCollectionId)){
                    if (!isFirstRecord){
                        collectionImagelist.add(list);
                    }
                    isFirstRecord=false;
                    list=new ArrayList<String>();  //poster url list
                }
                list.add(posterUrl);
                prevCollectionId = currentCollectionId;
            }
            // 마지막 리스트가 add되지 않았기 때문에 추가로 담아준다
            if (list!=null){
                collectionImagelist.add(list);
            }
            ConnectionProvider.close(rs, ps, conn);
        }catch (Exception e){
            System.out.println("예외 발생 : "+e.getMessage());
        }
        return collectionImagelist;
    }

}

package com.subakcine.action;

import com.subakcine.dao.CollectionDAO;
import com.subakcine.vo.CollectionVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UserCollectionAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid="B79ECF4B8DB74729A8AC7C2096037F5B";

        CollectionDAO collectionListDao=new CollectionDAO();
        ArrayList<CollectionVO> collectionList=collectionListDao.listCollections(userid);

        CollectionDAO movieImageListDao=new CollectionDAO();
        ArrayList<ArrayList<String>> imageList=movieImageListDao.findImageByCollection(userid);

        CollectionDAO countCollectionDao=new CollectionDAO();
        int count=countCollectionDao.countCollections(userid);

        request.setAttribute("collectionList", collectionList);
        request.setAttribute("imageList", imageList);
        request.setAttribute("count", count);
        System.out.println(collectionList);

        return "/views/userCollection.jsp";
    }
}

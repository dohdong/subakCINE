package com.subakcine.action;

import com.subakcine.dao.*;
import com.subakcine.vo.CollectionItemVO;
import com.subakcine.vo.CollectionVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class AllCollectionAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid=(String)request.getSession().getAttribute("usersID");
        CollectionItemVO collectionItemVO;
        MovieDAO movieDAO;
        TVShowDAO tvShowDAO;

        CollectionDAO collectionDao =new CollectionDAO();
        ArrayList<CollectionVO> collectionList = collectionDao.allCollections();

        for(CollectionVO collection:collectionList){
            // 컬렉션 별 item 리스트 생성
//            CollectionDAO collectionItemListDao=new CollectionDAO();
            ArrayList<CollectionItemVO> collectionItemList=collectionDao.listCollectionItems(collection.getCollectionId());
            System.out.println("collectionItemList.toString ==> "+collectionItemList.toString());
            for(CollectionItemVO collectionItem:collectionItemList){
//                System.out.println("collectionItem.getCollectionId() ==> "+collectionItem.getCollectionId());
                //collectionId로 userId 가져와서 vo에 저장
                String userId = collectionDao.getUserIdByCollectionId(collection.getCollectionId());
                collectionItem.setUserId(userId);
                if (collectionItem.getType().equals("movie")) { //type이 movie일때
                    movieDAO=new MovieDAO();
                    Map<String, Object> movie= movieDAO.getMovieDetails(collectionItem.getId());
//                    System.out.println("movie = " + movie);
                    collectionItem.setMovieImgUrl(movie.get("poster_path").toString());
                    collectionItem.setTitle(movie.get("title").toString());
                    System.out.println("collectionItem ==> "+collectionItem);
                }else { //type이 tv일때
                    tvShowDAO=new TVShowDAO();
                    Map<String, Object> tvShow = tvShowDAO.getTVShowDetails(collectionItem.getId());
                    collectionItem.setMovieImgUrl(tvShow.get("poster_path").toString());
                    collectionItem.setTitle(tvShow.get("original_name").toString());
                }
            }
            collection.setItems(collectionItemList);
            //userId로 userName 가져와서 vo에 저장
            UserDAO userDao = new UserDAO();
            String userName = userDao.getUserEmailById(collection.getUserID());
            collection.setUserName(userName);
            System.out.println("collection 상세정보 ==> "+collection.toString());
        }
//        System.out.println(collectionList);
        request.setAttribute("collectionList", collectionList);

        return "/views/allCollection.jsp";
    }
}

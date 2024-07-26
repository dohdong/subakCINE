package com.subakcine.action;

import com.subakcine.dao.CollectionDAO;
import com.subakcine.dao.MovieDAO;
import com.subakcine.dao.TVShowDAO;
import com.subakcine.vo.CollectionItemVO;
import com.subakcine.vo.CollectionVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UserCollectionAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid="1E4F791305DA49B4BEBD94776A7284B4";
        CollectionItemVO collectionItemVO;
        MovieDAO movieDAO;
        TVShowDAO tvShowDAO;

        CollectionDAO collectionListDao=new CollectionDAO();
        ArrayList<CollectionVO> collectionList=collectionListDao.listCollections(userid);

        for(CollectionVO collection:collectionList){
            // 컬렉션 별 item 리스트 생성
            CollectionDAO collectionItemListDao=new CollectionDAO();
            ArrayList<CollectionItemVO> collectionItemList=collectionItemListDao.listCollectionItems(collection.getCollectionId());
            for(CollectionItemVO collectionItem:collectionItemList){
                if (collectionItem.getType().equals("movie")) { //type이 movie일때
                    movieDAO=new MovieDAO();
                    Map<String, Object> movie= movieDAO.getMovieDetails(collectionItem.getId());
                    System.out.println("movie = " + movie);
                    collectionItem.setMovieImgUrl(movie.get("poster_path").toString());
                    collectionItem.setTitle(movie.get("title").toString());
                }else { //type이 tv일때
                    tvShowDAO=new TVShowDAO();
                    Map<String, Object> tvShow = tvShowDAO.getTVShowDetails(collectionItem.getId());
                    collectionItem.setMovieImgUrl(tvShow.get("poster_path").toString());
                    collectionItem.setTitle(tvShow.get("original_name").toString());
                }
            }
            collection.setItems(collectionItemList);
        }

        request.setAttribute("collectionList", collectionList);

        return "/views/userCollection.jsp";
    }
}

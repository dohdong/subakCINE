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

public class CollectionDetailAction implements SubakcineAction{

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collectionId=request.getParameter("collectionId");
        
        // request로 받아온 collection id로 해당 컬렉션 객체 생성
        CollectionDAO collectionDAO=new CollectionDAO();
        CollectionVO collection = collectionDAO.collectionDetail(collectionId);
        
        //컬렉션의 item list를 반환하고 collection 객체에 담아준다
        ArrayList<CollectionItemVO> itemList=new CollectionDAO().listCollectionItems(collectionId);
        for(CollectionItemVO item:itemList){
            if (item.getType().equals("movie")) { //type이 movie일때
                MovieDAO movieDAO=new MovieDAO();
                Map<String, Object> movie= movieDAO.getMovieDetails(item.getId());
                System.out.println("movie = " + movie);
                item.setMovieImgUrl(movie.get("poster_path").toString());
                item.setTitle(movie.get("title").toString());
            }else { //type이 tv일때
                TVShowDAO tvShowDAO=new TVShowDAO();
                Map<String, Object> tvShow = tvShowDAO.getTVShowDetails(item.getId());
                item.setMovieImgUrl(tvShow.get("poster_path").toString());
                item.setTitle(tvShow.get("original_name").toString());
            }
        }
        collection.setItems(itemList);
        
        // 형태유지 시켜서 보내준다
        request.setAttribute("collection", collection);
        return "/views/collectionDetail.jsp";
    }
}

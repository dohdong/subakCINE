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

public class UpdateCollectionAction implements SubakcineAction{

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collectionId=request.getParameter("collectionId");
        System.out.println("collectionId = " + collectionId);

        //컬렉션의 item list를 반환하고 collection 객체에 담아준다
        ArrayList<CollectionItemVO> itemList=new CollectionDAO().listCollectionItems(collectionId);
        System.out.println("itemList = " + itemList);

        for(CollectionItemVO item:itemList){
            if (item.getType().equals("movie")) { //type이 movie일때
                MovieDAO movieDAO=new MovieDAO();
                Map<String, Object> movie= movieDAO.getMovieDetails(item.getId());
                item.setMovieImgUrl((String)movie.get("poster_path"));
                item.setTitle((String)movie.get("title"));
                System.out.println("item = " + item);
            }else { //type이 tv일때
                TVShowDAO tvShowDAO=new TVShowDAO();
                Map<String, Object> tvShow = tvShowDAO.getTVShowDetails(item.getId());
                item.setMovieImgUrl(tvShow.get("poster_path").toString());
                item.setTitle(tvShow.get("original_name").toString());
            }
        }

        // 형태유지 시켜서 보내준다
        request.setAttribute("itemList", itemList);

        return "/views/updateCollection.jsp";
    }
}

package com.subakcine.action;

import com.subakcine.dao.CollectionDAO;
import com.subakcine.vo.MovieVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CollectionDetailAction implements SubakcineAction{

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String collectionId=request.getParameter("collectionId");

        CollectionDAO movie=new CollectionDAO();
        ArrayList<MovieVO> movieList=movie.collectionDetail(collectionId);

        CollectionDAO collection=new CollectionDAO();
        String collectionName = collection.findCollectionNameById(collectionId);

        CollectionDAO count=new CollectionDAO();
        int countMovies = count.countMovies(collectionId);

        System.out.println("movieList = " + movieList);

        request.setAttribute("movieList", movieList);
        request.setAttribute("collectionName", collectionName);
        request.setAttribute("countMovies", countMovies);


        return "/views/collectionDetail.jsp";
    }
}

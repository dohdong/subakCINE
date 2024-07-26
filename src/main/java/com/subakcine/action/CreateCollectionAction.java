package com.subakcine.action;

import com.subakcine.dao.MovieDAO;
import com.subakcine.dao.PersonDAO;
import com.subakcine.dao.SearchDAO;
import com.subakcine.dao.TVShowDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateCollectionAction implements SubakcineAction{

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieDAO movieDao = new MovieDAO();
        List<Map<String,Object>> popularMovies = movieDao.getPopularMovies();
        TVShowDAO tvShowDao = new TVShowDAO();
        List<Map<String, Object>> popularTVShows = tvShowDao.getPopularTVShows();

        request.setAttribute("popularMovies", popularMovies);
        request.setAttribute("popularTVShows", popularTVShows);

        return "/views/createCollection.jsp";
    }
}

package com.subakcine.action;

import com.subakcine.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserPageAction implements SubakcineAction{
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieDAO movieDao = new MovieDAO();

        List<Map<String, Object>> popularMovies = movieDao.getPopularMovies();
        request.setAttribute("popularMovies", popularMovies);

        return "/views/userpage.jsp";
    }
}

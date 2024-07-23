package com.subakcine.action;

import com.subakcine.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MoviesAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MovieDAO movieDao = new MovieDAO();
        List<Map<String, Object>> popularMovies = movieDao.getPopularMovies();
        List<Map<String, Object>> nowPlayingMovies = movieDao.getNowPlayingMovies();
        List<Map<String, Object>> upcomingMovies = movieDao.getUpcomingMovies();
        List<Map<String, Object>> topRatedMovies = movieDao.getTopRatedMovies();

        request.setAttribute("popularMovies", popularMovies);
        request.setAttribute("nowPlayingMovies", nowPlayingMovies);
        request.setAttribute("upcomingMovies", upcomingMovies);
        request.setAttribute("topRatedMovies", topRatedMovies);
        return "/views/movies.jsp";
    }
}

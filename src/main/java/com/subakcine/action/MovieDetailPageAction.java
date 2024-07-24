package com.subakcine.action;

import com.subakcine.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MovieDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieId = request.getParameter("id");
        String action = request.getParameter("action");
        MovieDAO movieDao = new MovieDAO();
        Map<String, Object> movieDetails = movieDao.getMovieDetails(movieId);
        request.setAttribute("movie", movieDetails);

        if (action != null) {
            String movieTitle = (String) movieDetails.get("title");
            String posterPath = (String) movieDetails.get("poster_path");

            if (action.equals("addToCollection")) {
                boolean success = movieDao.addToCollection(Integer.parseInt(movieId), movieTitle, posterPath);
                request.setAttribute("message", success ? "Added to collection successfully!" : "Failed to add to collection.");
            } else if (action.equals("likeMovie")) {
                boolean success = movieDao.likeMovie(Integer.parseInt(movieId), movieTitle, posterPath);
                request.setAttribute("message", success ? "Liked the movie successfully!" : "Failed to like the movie.");
            }
        }
        return "/views/movieDetailPage.jsp";
    }
}

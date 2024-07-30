package com.subakcine.action;

import com.subakcine.dao.CollectionDAO;
import com.subakcine.dao.LikeCountDAO;
import com.subakcine.dao.LikeItemDAO;
import com.subakcine.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * MovieDetailPageAction handles the movie detail page,
 * including movie details, collection addition, and like functionality.
 */
public class MovieDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String movieId = request.getParameter("id");
        String action = request.getParameter("action");
        boolean isLiked = false;

        try {
            MovieDAO movieDao = new MovieDAO();
            Map<String, Object> movieDetails = movieDao.getMovieDetails(movieId);
            request.setAttribute("movie", movieDetails);

            LikeCountDAO likeCountDAO = new LikeCountDAO();
            int likeCount = likeCountDAO.getLikeCount(movieId, "movie");
            request.setAttribute("likeCount", likeCount);

            String usersID = (String) request.getSession().getAttribute("usersID");

            if (usersID != null) {
                LikeItemDAO likeItemDAO = new LikeItemDAO();
                isLiked = likeItemDAO.isLiked(movieId, usersID, "movie");
                request.setAttribute("isLiked", isLiked);

                if (action != null && action.equals("likeMovie")) {
                    boolean success;
                    if (isLiked) {
                        success = likeItemDAO.unlikeItem(movieId, usersID, "movie");
                        isLiked = !success;
                        request.setAttribute("message", success ? "Removed like from the movie." : "Failed to remove like.");
                    } else {
                        success = likeItemDAO.likeItem(movieId, usersID, "movie");
                        isLiked = success;
                        request.setAttribute("message", success ? "Liked the movie successfully!" : "Failed to like the movie.");
                    }

                    // 좋아요 수 다시 가져오기
                    likeCount = likeCountDAO.getLikeCount(movieId, "movie");
                    request.setAttribute("likeCount", likeCount);
                    request.setAttribute("isLiked", isLiked);
                }

            } else {
                if (action != null && action.equals("likeMovie")) {
                    request.setAttribute("message", "Login required.");
                    return "views/signIn.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception details
            throw e; // Rethrow for non-AJAX requests
        }

        return "/views/movieDetailPage.jsp";
    }
}
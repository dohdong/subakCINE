package com.subakcine.action;

import com.subakcine.dao.CollectionDAO;
import com.subakcine.dao.LikeCountDAO;
import com.subakcine.dao.LikeItemDAO;
import com.subakcine.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        boolean isAjax = "true".equals(request.getParameter("ajax"));
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

                if (action != null) {
                    boolean success;
                    if (action.equals("addToCollection")) {
                        success = movieDao.addToCollection(request.getParameter("collectionId"), movieId, "movie");
                        request.setAttribute("message", success ? "Added to collection successfully!" : "Failed to add to collection.");
                    } else if (action.equals("likeMovie")) {
                        if (isLiked) {
                            success = likeItemDAO.unlikeItem(movieId, usersID, "movie");
                            isLiked = !success;
                            request.setAttribute("message", success ? "Removed like from the movie." : "Failed to remove like.");
                        } else {
                            success = likeItemDAO.likeItem(movieId, usersID, "movie");
                            isLiked = success;
                            request.setAttribute("message", success ? "Liked the movie successfully!" : "Failed to like the movie.");
                        }

                        if (isAjax) {
                            response.setContentType("application/json");
                            response.setCharacterEncoding("UTF-8");
                            PrintWriter out = response.getWriter();
                            out.print("{\"success\":" + success + ", \"isLiked\":" + isLiked + "}");
                            out.flush();
                            return null; // Prevent further processing
                        }
                    }
                }

            } else {
                if (action != null && action.equals("likeMovie")) {
                    request.setAttribute("message", "Login required.");
                    return "views/signIn.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception details
            if (isAjax) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"success\":false, \"message\":\"Error occurred: " + e.getMessage() + "\"}");
                out.flush();
                return null;
            }
            throw e; // Rethrow for non-AJAX requests
        }

        return "/views/movieDetailPage.jsp";
    }
}

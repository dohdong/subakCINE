package com.subakcine.action;

import com.subakcine.dao.LikeItemDAO;
import com.subakcine.dao.MovieDAO;
import com.subakcine.dao.TVShowDAO;
import com.subakcine.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserLikePageAction implements SubakcineAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String usersID = (String) session.getAttribute("usersID");

        LikeItemDAO likeItemDAO = new LikeItemDAO();

        List<Map<String, Object>> likedMovies = likeItemDAO.getLikedItems(usersID, "movie");
        List<Map<String, Object>> likedTVShows = likeItemDAO.getLikedItems(usersID, "tv");
        List<Map<String, Object>> likedPersons = likeItemDAO.getLikedItems(usersID, "person");

        request.setAttribute("likedMovies", likedMovies);
        request.setAttribute("likedTVShows", likedTVShows);
        request.setAttribute("likedPersons", likedPersons);

        return "views/userLikePage.jsp";
    }
}

package com.subakcine.action;

import com.subakcine.dao.TVShowDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TVShowDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tvShowId = request.getParameter("id");
        String action = request.getParameter("action");
        TVShowDAO tvShowDao = new TVShowDAO();
        Map<String, Object> tvShowDetails = tvShowDao.getTVShowDetails(tvShowId);
        request.setAttribute("tvShow", tvShowDetails);

        if (action != null) {
            String tvshowTitle = (String) tvShowDetails.get("name");
            String posterPath = (String) tvShowDetails.get("poster_path");

            if (action.equals("addToCollection")) {
                boolean success = tvShowDao.addToCollection(Integer.parseInt(tvShowId), tvshowTitle, posterPath);
                request.setAttribute("message", success ? "Added to collection successfully!" : "Failed to add to collection.");
            } else if (action.equals("likeTVShow")) {
                boolean success = tvShowDao.likeTVShow(Integer.parseInt(tvShowId), tvshowTitle, posterPath);
                request.setAttribute("message", success ? "Liked the TV show successfully!" : "Failed to like the TV show.");
            }
        }

        return "/views/tvShowDetailPage.jsp";
    }
}

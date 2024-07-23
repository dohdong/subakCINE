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
        TVShowDAO tvShowDao = new TVShowDAO();
        Map<String, Object> tvShowDetails = tvShowDao.getTVShowDetails(tvShowId);
        request.setAttribute("tvShow", tvShowDetails);
        return "/WEB-INF/views/tvShowDetailPage.jsp";
    }
}

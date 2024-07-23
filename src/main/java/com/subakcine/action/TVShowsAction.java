package com.subakcine.action;

import com.subakcine.dao.TVShowDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TVShowsAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TVShowDAO tvShowDao = new TVShowDAO();

        List<Map<String, Object>> popularTVShows = tvShowDao.getPopularTVShows();
        List<Map<String, Object>> airingTodayTVShows = tvShowDao.getAiringTodayTVShows();
        List<Map<String, Object>> onTheAirTVShows = tvShowDao.getOnTheAirTVShows();
        List<Map<String, Object>> topRatedTVShows = tvShowDao.getTopRatedTVShows();

        request.setAttribute("popularTVShows", popularTVShows);
        request.setAttribute("airingTodayTVShows", airingTodayTVShows);
        request.setAttribute("onTheAirTVShows", onTheAirTVShows);
        request.setAttribute("topRatedTVShows", topRatedTVShows);

        return "/views/tvShows.jsp";
    }
}

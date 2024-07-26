package com.subakcine.action;

import com.subakcine.dao.SearchDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        SearchDAO searchDao = new SearchDAO();
        List<Map<String, Object>> searchResults = searchDao.search(query);

        List<Map<String, Object>> movies = new ArrayList<>();
        List<Map<String, Object>> tvShows = new ArrayList<>();
        List<Map<String, Object>> person = new ArrayList<>();

        for (Map<String, Object> result : searchResults) {
            String mediaType = (String) result.get("media_type");
            if ("movie".equals(mediaType)) {
                movies.add(result);
            } else if ("tv".equals(mediaType)) {
                tvShows.add(result);
            } else if ("person".equals(mediaType)) {
                person.add(result);
            }
        }

        request.setAttribute("movies", movies);
        request.setAttribute("tvShows", tvShows);
        request.setAttribute("person", person);

        return "/views/search.jsp";
    }
}

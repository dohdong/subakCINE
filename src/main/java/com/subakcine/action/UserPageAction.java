package com.subakcine.action;

import com.subakcine.dao.MovieDAO;
import com.subakcine.dao.PersonDAO;
import com.subakcine.dao.TVShowDAO;
import com.subakcine.dao.UserDAO;
import com.subakcine.vo.UserVO;

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

        TVShowDAO tvShowDao = new TVShowDAO();
        List<Map<String, Object>> popularTVShows = tvShowDao.getPopularTVShows();

        PersonDAO personDao = new PersonDAO();
        List<Map<String, Object>> popularPerson = personDao.getPopularPerson();

        String email = (String)request.getSession().getAttribute("email");
        UserDAO userDao = new UserDAO();
        UserVO user = userDao.getUserByEmail(email);

        request.setAttribute("popularMovies", popularMovies);
        request.setAttribute("popularTVShows", popularTVShows);
        request.setAttribute("popularPerson", popularPerson);
        request.setAttribute("user", user);

        return "/views/userpage.jsp";
    }
}

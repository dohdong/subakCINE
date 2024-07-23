package com.subakcine.action;

import com.subakcine.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PeopleAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDAO personDao = new PersonDAO();
        List<Map<String, Object>> popularPeople = personDao.getPopularPeople();
        request.setAttribute("popularPeople", popularPeople);
        return "/views/people.jsp";
    }
}

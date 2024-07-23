package com.subakcine.action;

import com.subakcine.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PersonDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String personId = request.getParameter("id");
        PersonDAO personDao = new PersonDAO();
        Map<String, Object> personDetails = personDao.getPopularPeopleDetails(personId);
        request.setAttribute("personDetails", personDetails);
        return "/WEB-INF/views/personDetailPage.jsp";
    }


}

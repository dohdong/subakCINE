package com.subakcine.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CollectionDetailAction implements SubakcineAction{

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("collectionId");
        System.out.println("id = " + id);
        return "/views/collectionDetail.jsp";
    }
}

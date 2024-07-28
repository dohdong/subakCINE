package com.subakcine.action;

import com.subakcine.dao.UserDAO;
import com.subakcine.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserUpdateAction implements SubakcineAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserDAO userDao = new UserDAO();
        UserVO user = userDao.getUserByEmail((String)request.getSession().getAttribute("email"));

        request.setAttribute("user", user);
        
        return "/views/userUpdate.jsp";
    }
}

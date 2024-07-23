package com.subakcine.action;

import com.subakcine.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInOKAction implements SubakcineAction{
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO dao = new UserDAO();
        int re = dao.isUserExist(email,password);
        if(re==1){ //회원 맞음
            // 로그인했다는 표시로 상태유지 합니다.
            // 이것을 위하여 session을 이용하여 상태유지 합니다.
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
        }
        request.setAttribute("re",re);
        return "views/signInOK.jsp";
    }
}

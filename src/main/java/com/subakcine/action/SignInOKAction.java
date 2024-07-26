package com.subakcine.action;

import com.subakcine.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignInOKAction implements SubakcineAction {
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO dao = new UserDAO();
        int re = dao.isUserExist(email, password);
        if (re == 1) { // 회원 맞음
            // 로그인했다는 표시로 상태 유지합니다.
            // 이것을 위하여 session을 이용하여 상태 유지합니다.
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            // 메인 페이지로 리디렉션
            response.sendRedirect("mainPage.do");
            return null; // 리디렉션 후에는 더 이상 진행할 필요가 없음
        } else {
            request.setAttribute("msg", "로그인에 실패하였습니다. 이메일과 비밀번호를 확인하세요.");
            return "views/signIn.jsp"; // 로그인 실패 시 현재 페이지 유지
        }
    }
}

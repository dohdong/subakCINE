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
        String msg = "";
        UserDAO dao = new UserDAO();
        int re = dao.isUserExist(email, password);
        if (re == 1) { // 회원 맞음
            // 로그인했다는 표시로 상태 유지합니다.
            // 이것을 위하여 session을 이용하여 상태 유지합니다.
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            return "mainPage.do";
        }else if(re==0){ //이메일 맞고 비번틀림
            msg = "비밀번호가 틀렸습니다.다시 확인해주세요.";
        }else if(re==-1){
            msg = "존재하지 않는 이메일입니다.";
        }else {
            msg = "예외(확인필요)"; //나중에 삭제 예정
        }
        request.setAttribute("msg", msg);
        request.setAttribute("re",re);
        return "views/signIn.jsp";
    }
}

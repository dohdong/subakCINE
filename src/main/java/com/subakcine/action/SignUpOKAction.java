package com.subakcine.action;

import com.subakcine.dao.UserDAO;
import com.subakcine.vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpOKAction implements SubakcineAction{

    public UserDAO dao = new UserDAO();
    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserVO vo = new UserVO(email,password);
        int re = dao.insert(vo);
        if(re>0){
            request.setAttribute("msg","회원가입이 완료되었습니다.\n로그인 해주세요.");
        }else {
            request.setAttribute("msg", "회원가입에 실패하였습니다.");
        }
        request.setAttribute("re", re);
        return "views/signUp.jsp";
    }
}

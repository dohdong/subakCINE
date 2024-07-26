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
        String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
        String msg = "";
        UserVO vo = new UserVO(email,password);
        int result = dao.emailExist(email);
        int re = dao.insert(vo);
        if(result==1){
            if(re>0){
                msg = "회원가입이 완료되었습니다.로그인 해주세요.";
                return "view/signIn.jsp";
            }else {
                msg = "회원가입에 실패하였습니다.";
            }
        }else{
            re = -1;
            msg = "이미 존재하는 이메일입니다.";
        }
        System.out.println(result);
        request.setAttribute("msg", msg);
        request.setAttribute("re", re);
        return "views/signUp.jsp";
    }
}

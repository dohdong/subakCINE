package com.subakcine.controller;

import com.subakcine.action.SubakcineAction;
import com.subakcine.db.TmdbApiClient;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

// 모든 ".do"로 끝나는 URL 요청을 이 서블릿이 처리하도록 설정
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    // 요청 URI와 처리할 액션 클래스의 매핑을 저장하는 맵
    HashMap<String, SubakcineAction> map;

    // 서블릿 초기화 메서드
    @Override
    public void init(ServletConfig config) throws ServletException {
        map = new HashMap<String, SubakcineAction>();
        ServletContext context = config.getServletContext();
        // 서블릿 컨텍스트의 실제 경로를 가져옴
        String path = context.getRealPath("/WEB-INF/properties");

        // ServletContext를 사용하여 TmdbApiClient 초기화
        TmdbApiClient.init(context);

        try {
            // properties 파일을 읽어옴
            Reader reader = new FileReader(path + "/Subakcine.properties");
            Properties prop = new Properties();
            prop.load(reader);
            Iterator iter = prop.keySet().iterator();
            while (iter.hasNext()) {
                // properties 파일의 키와 값 읽기
                String key = (String) iter.next();
                String clsName = prop.getProperty(key);
                // 클래스 이름을 통해 클래스 객체 생성
                Object obj = Class.forName(clsName).newInstance();
                // 맵에 키와 생성된 객체를 매핑
                map.put(key, (SubakcineAction) obj);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    // 실제 요청 처리를 담당하는 메서드
    public void pro(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 요청 URI를 가져옴
        String uri = request.getRequestURI();
        // 요청 URI에서 마지막 "/" 이후의 문자열을 가져옴
        String cmd = uri.substring(uri.lastIndexOf("/") + 1);
        // URI에 매핑된 액션 객체를 가져옴
        SubakcineAction action = map.get(cmd);

        // 액션 객체의 처리 메서드 호출
        String viewPage = action.pro(request, response);

        // viewPage가 ".do"로 끝나면 리디렉션, 그렇지 않으면 포워딩
        if (viewPage.endsWith(".do")) {
            response.sendRedirect(viewPage);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        }
    }

    // GET 요청 처리
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            pro(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // POST 요청 처리
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            pro(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

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

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
    HashMap<String, SubakcineAction> map;

    @Override
    public void init(ServletConfig config) throws ServletException {
        map = new HashMap<String, SubakcineAction>();
        ServletContext context = config.getServletContext();
        String path = context.getRealPath("/WEB-INF/properties");

        // Initialize TmdbApiClient with ServletContext
        TmdbApiClient.init(context);
        try {
            Reader reader = new FileReader(path + "/Subakcine.properties");
            Properties prop = new Properties();
            prop.load(reader);
            Iterator iter = prop.keySet().iterator();
            while (iter.hasNext()) {
                String key = (String) iter.next();
                String clsName = prop.getProperty(key);
                Object obj = Class.forName(clsName).newInstance();
                map.put(key, (SubakcineAction) obj);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }

    public void pro(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uri = request.getRequestURI();
        String cmd = uri.substring(uri.lastIndexOf("/") + 1);
        SubakcineAction action = map.get(cmd);

        String viewPage = action.pro(request, response);

        if (viewPage.endsWith(".do")) {
            response.sendRedirect(viewPage);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            pro(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            pro(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
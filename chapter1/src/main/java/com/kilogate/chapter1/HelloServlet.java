package com.kilogate.chapter1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * HelloServlet
 *
 * @author fengquanwei
 * @create 2017/11/8 11:28
 **/
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = dateFormat.format(new Date());
        req.setAttribute("currentTime", currentTime);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/hello.jsp");
        dispatcher.forward(req, resp);
    }
}

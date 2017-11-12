package com.kilogate.chapter2.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 查看客户
 *
 * @author fengquanwei
 * @create 2017/11/11 15:57
 **/
@WebServlet("/customer_show")
public class CustomerShowServlet extends HttpServlet {
    /**
     * 进入查看客户页面
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }
}

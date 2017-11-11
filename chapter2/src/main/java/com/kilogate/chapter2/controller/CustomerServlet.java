package com.kilogate.chapter2.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 客户列表
 *
 * @author fengquanwei
 * @create 2017/11/11 15:56
 **/
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    /**
     * 进入客户列表页面
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }
}

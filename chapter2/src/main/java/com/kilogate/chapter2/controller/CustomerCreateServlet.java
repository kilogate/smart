package com.kilogate.chapter2.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 创建客户
 *
 * @author fengquanwei
 * @create 2017/11/11 15:32
 **/
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {
    /**
     * 进入创建客户界面
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }

    /**
     * 处理创建客户请求
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }
}

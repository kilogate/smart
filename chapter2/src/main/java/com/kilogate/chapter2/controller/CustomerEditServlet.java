package com.kilogate.chapter2.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CustomerEditServlet
 *
 * @author fengquanwei
 * @create 2017/11/11 15:56
 **/
@WebServlet("/customer_edit")
public class CustomerEditServlet extends HttpServlet {
    /**
     * 进入编辑客户界面
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }

    /**
     * 编辑客户
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }
}

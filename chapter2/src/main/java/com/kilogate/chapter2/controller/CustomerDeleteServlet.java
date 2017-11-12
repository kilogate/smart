package com.kilogate.chapter2.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 删除客户
 *
 * @author fengquanwei
 * @create 2017/11/11 15:56
 **/
@WebServlet("/customer_delete")
public class CustomerDeleteServlet extends HttpServlet {
    /**
     * 处理删除客户请求
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO
    }
}

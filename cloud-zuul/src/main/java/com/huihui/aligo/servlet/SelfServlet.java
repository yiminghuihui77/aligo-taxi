package com.huihui.aligo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义HttpSerlver
 *
 * @author minghui.y
 * @create 2021-07-24 10:50 上午
 **/
@WebServlet(name = "selfServlet", urlPatterns = "/self")
public class SelfServlet extends HttpServlet {


    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        System.out.println("2021-07-24 于星巴克 学习");
        super.doPost( req, resp );
    }


    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        super.doPost( req, resp );
    }





}

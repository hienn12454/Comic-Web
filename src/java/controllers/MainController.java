/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 *
 * @author SHD
 */
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            request.getRequestDispatcher("home").forward(request, response);
        } else {
            switch (action) {
                 case "login":
                    request.getRequestDispatcher("login").forward(request, response);
                    break;
                case "logout":
                    request.getRequestDispatcher("logout").forward(request, response);
                    break;
                case "register":
                    request.getRequestDispatcher("register").forward(request, response);
                    break;
                case "detail":
                    request.getRequestDispatcher("detail").forward(request, response);
                    break;
                case "deny":
                    request.getRequestDispatcher("deny").forward(request, response);
                    break;
                case "user":
                    request.getRequestDispatcher("users").forward(request, response);
                    break;
                case "story":
                    request.getRequestDispatcher("stories").forward(request, response);
                    break;
                    
                default:
                    request.getRequestDispatcher("home").forward(request, response);
                    break;
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         processRequest(request, response);
    }

}

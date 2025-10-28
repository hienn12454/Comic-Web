/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import models.User;
import utils.PasswordUtil;

/**
 *
 * @author SHD
 */
public class UserController extends HttpServlet {
   
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
         User u =(User) request.getSession().getAttribute("user");
        if(u == null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else if(!u.getRole().equals("Admin")){
            request.getRequestDispatcher("deny.jsp").forward(request, response);
        }
        switch (action) {
            case "list":
                listUsers(request, response);
                break;
            case "view":
                viewUser(request, response);
                break;
            case "edit":
                editUser(request, response);
                break;
            case "delete":
                deleteUser(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            default:
                listUsers(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addUser(request, response);
                break;
            case "update":
                updateUser(request, response);
                break;
            
        }
    }



    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/user/list.jsp").forward(request, response);
    }

    private void viewUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userDAO.getUserById(userId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("./user/view.jsp").forward(request, response);
        } else {
            // Handle case where user with given ID is not found
            response.sendRedirect("error.jsp"); // Example redirect to error page
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to add user form page
        request.getRequestDispatcher("./user/add.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String hashed = PasswordUtil.sha256(password);
        String role = request.getParameter("role");
        boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

        User newUser = new User(0, userName, email, hashed, role, isActive);
        boolean success = userDAO.addUser(newUser);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/users");
        } else {
            request.setAttribute("message", "Failed to add user.");
            request.getRequestDispatcher("./user/add.jsp").forward(request, response);
        }
    }

    private void editUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userDAO.getUserById(userId);
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("./user/edit.jsp").forward(request, response);
        } else {
            // Handle case where user with given ID is not found
            response.sendRedirect("error.jsp"); // Example redirect to error page
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        boolean isActive = Boolean.parseBoolean(request.getParameter("isActive"));

        String finalPassword;
        if (password == null || password.trim().isEmpty()) {
            User existing = userDAO.getUserById(userId);
            finalPassword = existing != null ? existing.getPassword() : "";
        } else {
            finalPassword = PasswordUtil.sha256(password);
        }
        User updatedUser = new User(userId, userName, email, finalPassword, role, isActive);
        boolean success = userDAO.updateUser(updatedUser);

        if (success) {
            response.sendRedirect("main?action=user");
        } else {
            request.setAttribute("message", "Failed to update user.");
            request.getRequestDispatcher("/user/edit.jsp").forward(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        boolean success;
        if(request.getParameter("active").equals("1")) {
            success = userDAO.activeUser(userId);
        } else {
            success = userDAO.disableUser(userId);
        }

        if (success) {
            response.sendRedirect("main?action=user");
        } else {
            request.setAttribute("message", "Failed to delete user.");
            request.getRequestDispatcher("/user/list.jsp").forward(request, response);
        }
    }

}

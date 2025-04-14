/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.StoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import models.Author;
import models.Genre;
import models.Story;
import models.User;
import java.sql.Timestamp;
import java.util.Date;
import models.Chapter;

/**
 *
 * @author SHD
 */
public class StoryController extends HttpServlet {

    private StoryDAO storyDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        storyDAO = new StoryDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        User u = (User) request.getSession().getAttribute("user");
        if (u == null) {
            response.sendRedirect("main?action=login");
        } else if (!u.getRole().equals("Admin")) {
            response.sendRedirect("main?action=deny");
        }
        switch (action) {
            case "list":
                listStories(request, response);
                break;
            case "view":
                viewStory(request, response);
                break;
            case "edit":
                editStory(request, response);
                break;
            case "delete":
                deleteStory(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            default:
                listStories(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addStory(request, response);
                break;
            case "edit":
                updateStory(request, response);
                break;
            default:
                listStories(request, response);
        }
    }

    private String extractAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            return "list";
        }
        return pathInfo.substring(1); // Remove leading slash
    }

    private void listStories(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Story> stories = storyDAO.getAllStories();
        request.setAttribute("stories", stories);
        request.getRequestDispatcher("/story/list.jsp").forward(request, response);
    }

    private void viewStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int storyId = Integer.parseInt(request.getParameter("storyId"));
        Story story = storyDAO.getStoryById(storyId);
        if (story != null) {
            List<Chapter> chapters = storyDAO.getListChapterByStoryId(storyId);
            request.setAttribute("chapters", chapters);
            request.setAttribute("story", story);
            request.getRequestDispatcher("/story/view.jsp").forward(request, response);
        } else {
            // Handle case where story with given ID is not found
            response.sendRedirect("error.jsp"); // Example redirect to error page
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to add story form page
        List<Author> authors = storyDAO.getListAuthor();
        List<Genre> genres = storyDAO.getListGenre();
        request.setAttribute("listAuthor", authors);
        request.setAttribute("listGenre", genres);
        request.getRequestDispatcher("/story/add.jsp").forward(request, response);
    }

    private void addStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        int genreId = Integer.parseInt(request.getParameter("genreId"));
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        Timestamp createdAt = new Timestamp(new Date().getTime());
        String image = request.getParameter("image");

        Story newStory = new Story(0, title, description, authorId, genreId, status, createdAt, image);
        boolean success = storyDAO.addStory(newStory);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/main?action=story");
        } else {
            request.setAttribute("message", "Failed to add story.");
            request.getRequestDispatcher("/story/add.jsp").forward(request, response);
        }
    }

    private void editStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int storyId = Integer.parseInt(request.getParameter("storyId"));
        Story story = storyDAO.getStoryById(storyId);
        if (story != null) {
            request.setAttribute("story", story);
            request.getRequestDispatcher("/story/edit.jsp").forward(request, response);
        } else {
            // Handle case where story with given ID is not found
            response.sendRedirect("error.jsp"); // Example redirect to error page
        }
    }

    private void updateStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int storyId = Integer.parseInt(request.getParameter("storyId"));
        String title = request.getParameter("title");
        int authorId = Integer.parseInt(request.getParameter("authorId"));
        int genreId = Integer.parseInt(request.getParameter("genreId"));
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String image = request.getParameter("image");

        Story updatedStory = new Story(storyId, title, description, authorId, genreId, status, null, image);
        boolean success = storyDAO.updateStory(updatedStory);

        if (success) {
            response.sendRedirect(request.getContextPath() +  "/main?action=story");
        } else {
            request.setAttribute("message", "Failed to update story.");
            request.getRequestDispatcher("/story/edit.jsp").forward(request, response);
        }
    }

    private void deleteStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int storyId = Integer.parseInt(request.getParameter("storyId"));
        boolean success = storyDAO.deleteStory(storyId);

        if (success) {
            response.sendRedirect(request.getContextPath() +  "/main?action=story");
        } else {
            request.setAttribute("message", "Failed to delete story.");
            request.getRequestDispatcher("/story/list.jsp").forward(request, response);
        }
    }

}

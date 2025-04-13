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
//import javax.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import models.Author;
import models.Chapter;
import models.ChapterImage;
import models.Genre;
import models.Story;
import models.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChapterController", urlPatterns = {"/chapters"})
public class ChapterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChapterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChapterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
                listChapters(request, response);
                break;
            case "view":
                viewChapter(request, response);
                break;
            case "edit":
                editChapter(request, response);
                break;
            case "delete":
                deleteChapter(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            default:
                listChapters(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addChapter(request, response);
                break;
            case "edit":
                updateChapter(request, response);
                break;
            default:
                listChapters(request, response);
        }
    }

    private void listChapters(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Story> stories = storyDAO.getAllStories();
        request.setAttribute("stories", stories);
        request.getRequestDispatcher("/story/list.jsp").forward(request, response);
    }

    private void viewChapter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int chapterId = Integer.parseInt(request.getParameter("chapterId"));
        Chapter chapter = storyDAO.getChapterById(chapterId);
        if (chapter != null) {
            List<ChapterImage> listCI = storyDAO.getListChapterImagesByChapterId(chapterId);
            request.setAttribute("listCI", listCI);
            request.setAttribute("chapter", chapter);
            request.getRequestDispatcher("/chapter/view.jsp").forward(request, response);
        } else {
            // Handle case where story with given ID is not found
            response.sendRedirect("error.jsp"); // Example redirect to error page
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Story s = storyDAO.getStoryById(Integer.parseInt(request.getParameter("storyId")));
        int chapterNumber = storyDAO.getChapterNewestOfStory(s.getStoryId());
        request.setAttribute("story", s);
        request.setAttribute("chapterNumberMax", chapterNumber);
        request.getRequestDispatcher("/chapter/add.jsp").forward(request, response);
    }

    private void addChapter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        int storyId = Integer.parseInt(request.getParameter("storyId"));
        int chapterNumber = Integer.parseInt(request.getParameter("chapterNumber"));
        Timestamp createdAt = new Timestamp(new Date().getTime());
        Chapter newChapter = new Chapter(0,storyId, chapterNumber+1, title, createdAt);
        boolean success = storyDAO.addChapter(newChapter);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/stories?action=view&storyId="+storyId);
        } else {
            request.setAttribute("message", "Failed to add story.");
            request.getRequestDispatcher("/story/add.jsp").forward(request, response);
        }
    }

    private void editChapter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int chapterId = Integer.parseInt(request.getParameter("chapterId"));
        Chapter chapter = storyDAO.getChapterById(chapterId);
        if (chapter != null) {
            request.setAttribute("chapter", chapter);
            request.getRequestDispatcher("/chapter/edit.jsp").forward(request, response);
        } else {
            // Handle case where story with given ID is not found
            response.sendRedirect("error.jsp"); // Example redirect to error page
        }
    }

    private void updateChapter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int chapterId = Integer.parseInt(request.getParameter("chapterId"));
        int storyId = Integer.parseInt(request.getParameter("storyId"));
        String title = request.getParameter("title");
        Chapter updatedChapter = new Chapter();
        updatedChapter.setChapterId(chapterId);
        updatedChapter.setTitle(title);
        out.print(updatedChapter.toString());
        boolean success = storyDAO.updateChapter(updatedChapter);
        out.print(success);
        if (success) {
            response.sendRedirect(request.getContextPath() +  "/stories?action=view&storyId="+storyId);
        } else {
            request.setAttribute("message", "Failed to update chapter.");
            request.getRequestDispatcher("/chapter/edit.jsp").forward(request, response);
        }
    }

    private void deleteChapter(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int chapterId = Integer.parseInt(request.getParameter("chapterId"));
        Chapter c = storyDAO.getChapterById(chapterId);
        boolean success = storyDAO.deleteChapterById(chapterId);

        if (success) {
            response.sendRedirect(request.getContextPath() +  "/stories?action=view&storyId="+c.getStoryId());
        } else {
            request.setAttribute("message", "Failed to delete story.");
            request.getRequestDispatcher("/story/list.jsp").forward(request, response);
        }
    }

}

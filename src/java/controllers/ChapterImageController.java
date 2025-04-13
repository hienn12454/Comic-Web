/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.StoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import models.Chapter;
import models.ChapterImage;
import models.Story;
import models.User;

/**
 *
 * @author admin
 */
@WebServlet(name = "ChapterImageController", urlPatterns = {"/chapterimages"})
public class ChapterImageController extends HttpServlet {

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
            out.println("<title>Servlet ChapterImageController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChapterImageController at " + request.getContextPath() + "</h1>");
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
        if (action.equals("add")) {
            showAddForm(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if (action.equals("add")) {
            addChapterImage(request, response);
        }
    }
    
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Chapter c = storyDAO.getChapterById(Integer.parseInt(request.getParameter("chapterId")));
        int orderNumber = storyDAO.getChapterImageNewestOfChapter(c.getChapterId());
        request.setAttribute("orderNumber", orderNumber);
        request.setAttribute("chapter", c);
        request.getRequestDispatcher("/chapterImages/add.jsp").forward(request, response);
    }
    
    private void addChapterImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String imageUrl = request.getParameter("imageUrl");
        int chapterId = Integer.parseInt(request.getParameter("chapterId"));
        int orderNumber = Integer.parseInt(request.getParameter("orderNumber"));
        ChapterImage newChapterImage = new ChapterImage();
        newChapterImage.setChapterId(chapterId);
        newChapterImage.setImageUrl(imageUrl);
        newChapterImage.setOrderNumber(orderNumber+1);
        boolean success = storyDAO.addChapterImage(newChapterImage);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/chapters?action=view&chapterId="+chapterId);
        } else {
            request.setAttribute("message", "Failed to add story.");
            request.getRequestDispatcher("/chapterImages/add.jsp").forward(request, response);
        }
    }

}

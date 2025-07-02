package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.example.dao.BookDAO;
import com.example.model.Book;

import java.io.IOException;

public class BookCreateServlet extends HttpServlet {
    private BookDAO dao = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/createBook.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String title  = req.getParameter("title");
        String author = req.getParameter("author");
        dao.save(new Book(title, author));
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}
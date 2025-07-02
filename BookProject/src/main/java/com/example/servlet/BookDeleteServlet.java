package com.example.servlet;

import com.example.dao.BookDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class BookDeleteServlet extends HttpServlet {
    private BookDAO dao = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        dao.deleteById(id);
        resp.sendRedirect(req.getContextPath() + "/books");
    }
}

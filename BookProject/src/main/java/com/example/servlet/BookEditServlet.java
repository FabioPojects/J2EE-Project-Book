package com.example.servlet;

import com.example.dao.BookDAO;
import com.example.model.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class BookEditServlet extends HttpServlet {
    private BookDAO dao = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String author = req.getParameter("author");

        Book book = new Book(title, author);
        book.setId(id);

        dao.update(book);

        resp.sendRedirect(req.getContextPath() + "/books");
    }
}

package com.example.servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import com.example.dao.BookDAO;
import com.example.model.Book;

import java.io.IOException;
import java.util.List;

public class BookListServlet extends HttpServlet {
    private BookDAO dao = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Book> books = dao.findAll();
        req.setAttribute("books", books);
        RequestDispatcher rd = req.getRequestDispatcher("/livros.jsp");
        rd.forward(req, resp);
    }
}
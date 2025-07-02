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

        String searchId = req.getParameter("searchId");

        if (searchId != null && !searchId.isEmpty()) {
            try {
                Long id = Long.parseLong(searchId);
                Book book = dao.findAll().stream()
                               .filter(b -> b.getId().equals(id))
                               .findFirst()
                               .orElse(null);
                if (book != null) {
                    req.setAttribute("books", List.of(book));
                } else {
                    req.setAttribute("books", List.of());
                    req.setAttribute("notFound", true);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("books", List.of());
                req.setAttribute("invalidSearch", true);
            }
        } else {
            List<Book> books = dao.findAll();
            req.setAttribute("books", books);
        }

        String editIdParam = req.getParameter("editId");
        if (editIdParam != null) {
            try {
                Long editId = Long.parseLong(editIdParam);
                List<Book> books = (List<Book>) req.getAttribute("books");
                for (Book b : books) {
                    if (b.getId().equals(editId)) {
                        req.setAttribute("editBook", b);
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        RequestDispatcher rd = req.getRequestDispatcher("/livros.jsp");
        rd.forward(req, resp);
    }
}


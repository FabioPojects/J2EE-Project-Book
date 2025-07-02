package com.example.dao;

import com.example.model.Book;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookDAO {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream in = 
                 BookDAO.class.getClassLoader()
                              .getResourceAsStream("db.properties")) {

            if (in == null) {
                throw new RuntimeException("db.properties não encontrado no classpath");
            }
            Properties props = new Properties();
            props.load(in);

            URL      = props.getProperty("db.url");
            USER     = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar configurações do banco", e);
        }
    }

    public List<Book> findAll() {
        List<Book> list = new ArrayList<>();
        String sql = "SELECT id, title, author FROM books";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book();
                b.setId(rs.getLong("id"));
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                list.add(b);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Book book) {
        String sql = "INSERT INTO books(title,author) VALUES(?,?)";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteById(Long id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ? WHERE id = ?";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setLong(3, book.getId());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


}
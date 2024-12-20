package com.library.dao;

import com.library.model.Book;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mysql.cj.conf.PropertyKey.logger;

public class BookDAO {

    // Ajoutez la méthode pour obtenir un livre par son ID
    public Optional<Book> getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                );
                return Optional.of(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Ajoutez la méthode pour obtenir tous les livres
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Connection conn = DbConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Ajoutez la méthode pour ajouter un livre
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, isbn, published_year, available,id) VALUES (?, ?, ?, ?, ?,?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getIsbn());
            stmt.setInt(4, book.getPublishedYear());
            stmt.setBoolean(5, book.isAvailable());  // Insertion de la propriété "available"
            stmt.setInt(6,book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Supprimer tous les livres
    // Ajoutez la méthode pour mettre à jour un livre
    public void updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, available = ? WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isAvailable());
            stmt.setInt(4, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ajoutez la méthode pour supprimer un livre
    public void deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Book findBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                );
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Supprimer tous les livres
    public String deleteAll() {
        String sql = "DELETE FROM books";
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            System.out.println("delete from books");
            return "Tous les livres ont été supprimés avec succès !";
        } catch (SQLException e) {
//            logger.severe("Erreur lors de la suppression de tous les livres : " + e.getMessage());
            return "Erreur lors de la suppression de tous les livres : ";
        }
    }

}

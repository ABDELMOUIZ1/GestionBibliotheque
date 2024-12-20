package com.library.dao;

import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;
import com.library.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    public void addBorrow(Borrow borrow) {
        String sql = "INSERT INTO borrows (student_id, book_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, borrow.getStudent().getId());
            preparedStatement.setInt(2, borrow.getBook().getId());
            preparedStatement.setDate(3, new java.sql.Date(borrow.getBorrowDate().getTime()));
            preparedStatement.setDate(4, borrow.getReturnDate() != null ? new java.sql.Date(borrow.getReturnDate().getTime()) : null);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT * FROM borrows";
        try (Connection connection = DbConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                // Vous devrez récupérer les détails de l'étudiant et du livre à partir des autres tables
                borrows.add(new Borrow(
                        resultSet.getInt("id"),
                        new Student(resultSet.getInt("student_id"), "Nom de l'étudiant"), // Remplacer par un lookup réel
                        new Book(resultSet.getInt("book_id"), "Titre du livre", "Auteur", "ISBN", 2020), // Remplacer par un lookup réel
                        resultSet.getDate("borrow_date"),
                        resultSet.getDate("return_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }
}

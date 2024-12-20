package com.library.service;

import com.library.dao.BookDAO;
import com.library.dao.BorrowDAO;
import com.library.dao.StudentDAO;
import com.library.model.Book;
import com.library.model.Borrow;
import com.library.model.Student;

import java.util.List;

public class BorrowService {
    private final BorrowDAO borrowDAO;
    private final BookDAO bookDAO= new BookDAO();
    private final StudentDAO studentDAO = new StudentDAO();


    public BorrowService() {
        this.borrowDAO = new BorrowDAO();
    }
    // Constructeur avec BorrowDAO
    public BorrowService(BorrowDAO borrowDAO) {
        this.borrowDAO = borrowDAO;
    }

    public void borrowBook(Borrow borrow) {
        borrowDAO.addBorrow(borrow);
        System.out.println("Livre emprunté : " + borrow.getBook().getTitle());
    }

    public void displayBorrows() {
        List<Borrow> borrows = borrowDAO.getAllBorrows();
        if (borrows.isEmpty()) {
            System.out.println("Aucun emprunt enregistré.");
        } else {
            for (Borrow borrow : borrows) {
                System.out.println(borrow);
            }
        }
    }
    // Méthode pour emprunter un livre
    public String borrowBook(int studentId, int bookId) {
        // Vérifier l'existence de l'étudiant
        Student student = studentDAO.getStudentById(studentId).orElse(null);
        if (student == null) {
            return "Étudiant ou livre non trouvé.";
        }

        // Vérifier l'existence et la disponibilité du livre
        Book book = bookDAO.getBookById(bookId).orElse(null);
        if (book == null) {
            return "Étudiant ou livre non trouvé.";
        }

        if (!book.isAvailable()) {
            return "Le livre n'est pas disponible.";
        }

        // Marquer le livre comme non disponible
        book.setAvailable(false);
        bookDAO.updateBook(book);
        return "Livre emprunté avec succès!";
    }
    // Méthode pour retourner un livre
    public String returnBook(int studentId, int bookId) {
        // Vérifier l'existence de l'étudiant
        Student student = studentDAO.getStudentById(studentId).orElse(null);
        if (student == null) {
            return "Étudiant non trouvé.";
        }

        // Vérifier l'existence du livre
        Book book = bookDAO.getBookById(bookId).orElse(null);
        if (book == null) {
            return "Livre non trouvé.";
        }

        // Marquer le livre comme disponible
        book.setAvailable(true);
        bookDAO.updateBook(book);
        return "Livre retourné avec succès!";
    }

}

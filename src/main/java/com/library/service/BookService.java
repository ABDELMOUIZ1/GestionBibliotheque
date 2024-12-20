package com.library.service;

import com.library.dao.BookDAO;
import com.library.model.Book;

import java.util.List;

public class BookService {
    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }
    public String deleteAllBooks() {
        return bookDAO.deleteAll();
    }
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    public void displayBooks() {
        List<Book> books = bookDAO.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("Aucun livre dans la bibliothèque.");
        } else {
            for (Book book : books) {
                System.out.println(book.getTitle() + " - " + book.getAuthor());
            }
        }
    }

    public Book findBookById(int id) {
        return bookDAO.findBookById(id);
    }
    public void updateBook(int id, String title, String author, boolean available) {
        Book book = bookDAO.getBookById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(title);
        book.setAuthor(author);
        book.setAvailable(available);
        bookDAO.updateBook(book);
    }

    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }
}

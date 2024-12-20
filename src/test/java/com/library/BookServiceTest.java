package com.library;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.service.BookService;
import com.library.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private BookService bookService;
    private BookDAO bookDAO ;
    private StudentService studentService=new StudentService();

    @BeforeEach
    void setUp() {
        bookDAO = new BookDAO();
        bookService = new BookService();
        bookDAO.deleteAll();
        studentService.deleteAllStudents();
    }

    @Test
    void testAddBook() {
        Book book = new Book(1, "Java Programming", "John Doe", true);
        bookService.addBook(book);
        assertEquals(1, bookDAO.getAllBooks().size());
        assertEquals("Java Programming", bookDAO.getBookById(1).get().getTitle());
    }

    @Test
    void testUpdateBook() {
        Book book = new Book(2, "Java Programming", "John Doe", true);
        bookService.addBook(book);
        bookService.updateBook(2, "Advanced Java", "Jane Doe", false);
        assertEquals("Advanced Java", bookDAO.getBookById(2).get().getTitle());
        assertFalse(bookDAO.getBookById(2).get().isAvailable());
    }

    @Test
    void testDeleteBook() {
        Book book = new Book(1, "Java Programming", "John Doe", true);
        bookService.addBook(book);
        bookService.deleteBook(1);
        assertTrue(bookDAO.getBookById(1).isEmpty());
    }
}

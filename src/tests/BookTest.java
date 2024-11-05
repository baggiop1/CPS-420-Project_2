package com.example.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("123456789", "Test Title", "Test Author", 5);
    }

    @Test
    public void testBookCreation() {
        assertNotNull(book);
        assertEquals("123456789", book.getIsbn());
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals(5, book.getCopiesAvailable());
    }

    @Test
    public void testEmptyISBN() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("", "Title", "Author", 5);
        });
        assertEquals("ISBN cannot be empty.", exception.getMessage());
    }
}

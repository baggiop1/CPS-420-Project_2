package com.example.library;

import org.junit.jupiter.api.BeforeEach;
import junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class LibrarySystemTest {

    private LibrarySystem librarySystem;
    private Book book1;
    private Book book2;
    private Member member1;

    @BeforeEach
    public void setUp() {
        librarySystem = new LibrarySystem();
        book1 = new Book("123456789", "The Great Gatsby", "F. Scott Fitzgerald", 3);
        book2 = new Book("987654321", "1984", "George Orwell", 2);
        member1 = new Member("M001", "John Doe");

        librarySystem.addBook(book1);
        librarySystem.addBook(book2);
        librarySystem.registerMember(member1);
    }

    @Test
    public void testSearchBooksByTitle() {
        List<Book> foundBooks = librarySystem.searchBooksByTitle("The Great Gatsby");
        assertEquals(1, foundBooks.size());
        assertEquals("The Great Gatsby", foundBooks.get(0).getTitle());
    }

    @Test
    public void testIssueLoan() {
        Loan loan = librarySystem.issueLoan("M001", "123456789");
        assertNotNull(loan);
        assertEquals("The Great Gatsby", loan.getBook().getTitle());
        assertEquals("John Doe", loan.getMember().getName());
        assertEquals(2, loan.getBook().getCopiesAvailable()); // Check that copies available decreases
    }

    @Test
    public void testRemoveMemberWithOutstandingLoans() {
        librarySystem.issueLoan("M001", "123456789");
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            librarySystem.removeMember(member1);
        });
        assertEquals("Member has outstanding loans.", exception.getMessage());
    }
}

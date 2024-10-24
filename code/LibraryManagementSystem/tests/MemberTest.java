package com.example.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class MemberTest {

    private Member member;
    private Book book1;
    private Book book2;

    @BeforeEach
    public void setUp() {
        member = new Member("M001", "John Doe");
        book1 = new Book("123456789", "The Great Gatsby", "F. Scott Fitzgerald", 3);
        book2 = new Book("987654321", "1984", "George Orwell", 2);
    }

    @Test
    public void testConstructorWithInvalidInputs() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> {
            new Member("", "John Doe");
        });
        assertEquals("Member ID cannot be empty.", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            new Member("M001", "");
        });
        assertEquals("Name cannot be empty.", exception2.getMessage());
    }

    @Test
    public void testBorrowBook() {
        Loan loan = member.borrowBook(book1);
        assertNotNull(loan);
        assertEquals("The Great Gatsby", loan.getBook().getTitle());
        assertEquals(1, member.getBorrowedBooks().size()); // Verify that the book is added to the list of borrowed
                                                           // books
        assertEquals(2, book1.getCopiesAvailable()); // Verify that available copies decrease
    }

    @Test
    public void testBorrowBookExceedingLoanLimit() {
        // Borrow 5 books to hit the limit
        for (int i = 0; i < 5; i++) {
            Book book = new Book("ISBN" + i, "Book " + i, "Author " + i, 1);
            member.borrowBook(book);
        }

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            member.borrowBook(book1); // Attempt to borrow more than 5 books
        });
        assertEquals("Loan limit reached. Cannot borrow more books.", exception.getMessage());
    }

    @Test
    public void testReturnBook() {
        Loan loan = member.borrowBook(book1);
        assertEquals(1, member.getBorrowedBooks().size());

        member.returnBook(loan);
        assertEquals(0, member.getBorrowedBooks().size()); // Verify that the book is removed from the borrowed list
        assertEquals(3, book1.getCopiesAvailable()); // Verify that the book's available copies are restored
        assertTrue(loan.isReturned()); // Verify that the loan is marked as returned
    }

    @Test
    public void testReturnNonExistentLoan() {
        Loan loan = new Loan(member, book2);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            member.returnBook(loan); // Attempt to return a book that wasn't borrowed
        });
        assertEquals("This loan does not exist for this member.", exception.getMessage());
    }
}

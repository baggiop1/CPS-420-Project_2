package com.example.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoanTest {

    private Member mockMember;
    private Book mockBook;
    private Loan loan;

    @BeforeEach
    void setUp() {
     
        mockMember = mock(Member.class);
        mockBook = mock(Book.class);

     
        when(mockMember.getName()).thenReturn("John Doe");
        when(mockBook.getTitle()).thenReturn("Test Driven Development");

        loan = new Loan(mockMember, mockBook);
    }

    @Test
    void testLoanCreation() {
        assertEquals(mockMember, loan.getMember());
        assertEquals(mockBook, loan.getBook());
        assertEquals(LocalDate.now(), loan.getLoanDate());
        assertEquals(LocalDate.now().plusDays(14), loan.getDueDate());
        assertFalse(loan.isReturned());
    }

    @Test
    void testSetReturned() {
        loan.setReturned(true);
        assertTrue(loan.isReturned());
    }

    @Test
    void testIsOverdue_NotOverdue() {
        assertFalse(loan.isOverdue());
    }

    @Test
    void testIsOverdue_Overdue() {
        Loan overdueLoan = new Loan(mockMember, mockBook);
        overdueLoan.setReturned(false);
        overdueLoan = spy(overdueLoan);
        when(overdueLoan.getLoanDate()).thenReturn(LocalDate.now().minusDays(15));

        assertTrue(overdueLoan.isOverdue());
    }

    @Test
    void testToString() {
        String expected = String.format("Loan[Member=%s, Book=%s, Due Date=%s, Returned=%s]",
                "John Doe", "Test Driven Development", loan.getDueDate(), loan.isReturned());

        assertEquals(expected, loan.toString());
    }
}

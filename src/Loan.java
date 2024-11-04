package com.example.library;

import java.time.LocalDate;

/**
 * The Loan class represents a loan transaction.
 */
public class Loan {
    private Member member;
    private Book book;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean isReturned;

    private static final int LOAN_PERIOD_DAYS = 14;

    /**
     * Constructor for the Loan class.
     *
     * @param member The member who is borrowing the book.
     * @param book   The book being borrowed.
     */
    public Loan(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.loanDate = LocalDate.now();
        this.dueDate = loanDate.plusDays(LOAN_PERIOD_DAYS);
        this.isReturned = false;
    }

    // Getters and Setters
    public Member getMember() { return member; }
    public Book getBook() { return book; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isReturned() { return isReturned; }
    public void setReturned(boolean returned) { isReturned = returned; }

    /**
     * Checks if the loan is overdue.
     *
     * @return True if the loan is overdue, false otherwise.
     */
    public boolean isOverdue() {
        return !isReturned && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return String.format("Loan[Member=%s, Book=%s, Due Date=%s, Returned=%s]",
                member.getName(), book.getTitle(), dueDate, isReturned);
    }
}

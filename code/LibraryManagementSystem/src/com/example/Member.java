package com.example.library;

import java.util.ArrayList;
import java.util.List;

/**
 * The Member class represents a library member.
 */
public class Member {
    private String memberId;
    private String name;
    private List<Loan> borrowedBooks;
    private static final int MAX_LOAN_LIMIT = 5;

    /**
     * Constructor for the Member class.
     *
     * @param memberId The unique ID of the member.
     * @param name     The name of the member.
     */
    public Member(String memberId, String name) {
        if (memberId == null || memberId.isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters and Setters
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) {
        if (memberId == null || memberId.isEmpty()) {
            throw new IllegalArgumentException("Member ID cannot be empty.");
        }
        this.memberId = memberId;
    }

    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    public List<Loan> getBorrowedBooks() { return borrowedBooks; }

    /**
     * Method for a member to borrow a book.
     *
     * @param book The book to be borrowed.
     * @return The Loan object representing the borrowing transaction.
     * @throws IllegalStateException if the member has reached the loan limit.
     */
    public Loan borrowBook(Book book) {
        if (borrowedBooks.size() >= MAX_LOAN_LIMIT) {
            throw new IllegalStateException("Loan limit reached. Cannot borrow more books.");
        }
        book.borrowBook();
        Loan loan = new Loan(this, book);
        borrowedBooks.add(loan);
        return loan;
    }

    /**
     * Method for a member to return a book.
     *
     * @param loan The Loan object representing the borrowing transaction.
     */
    public void returnBook(Loan loan) {
        if (borrowedBooks.contains(loan)) {
            loan.getBook().returnBook();
            borrowedBooks.remove(loan);
            loan.setReturned(true);
        } else {
            throw new IllegalArgumentException("This loan does not exist for this member.");
        }
    }

    @Override
    public String toString() {
        return String.format("Member[ID=%s, Name=%s, Borrowed Books=%d]",
                memberId, name, borrowedBooks.size());
    }
}

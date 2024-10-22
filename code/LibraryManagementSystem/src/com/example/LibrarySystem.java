package com.example.library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The LibrarySystem class manages library operations.
 */
public class LibrarySystem {
    private List<Book> books;
    private List<Member> members;
    private List<Loan> loans;

    /**
     * Constructor for the LibrarySystem class.
     */
    public LibrarySystem() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Book Management
    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        if (book.getCopiesAvailable() > 0) {
            books.remove(book);
        } else {
            throw new IllegalStateException("Cannot remove a book that is currently on loan.");
        }
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    // Member Management
    public void registerMember(Member member) {
        members.add(member);
    }

    public void removeMember(Member member) {
        if (member.getBorrowedBooks().isEmpty()) {
            members.remove(member);
        } else {
            throw new IllegalStateException("Member has outstanding loans.");
        }
    }

    public Member searchMemberById(String memberId) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(memberId))
                .findFirst()
                .orElse(null);
    }

    // Loan Management
    public Loan issueLoan(String memberId, String isbn) {
        Member member = searchMemberById(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found.");
        }
        Book book = books.stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
        if (book == null) {
            throw new IllegalArgumentException("Book not found.");
        }
        Loan loan = member.borrowBook(book);
        loans.add(loan);
        return loan;
    }

    public void returnLoan(Loan loan) {
        loan.getMember().returnBook(loan);
        loan.setReturned(true);
    }

    public List<Loan> getOverdueLoans() {
        return loans.stream()
                .filter(Loan::isOverdue)
                .collect(Collectors.toList());
    }

    // Other Methods
    public void displayAllBooks() {
        books.forEach(System.out::println);
    }

    public void displayAllMembers() {
        members.forEach(System.out::println);
    }

    public void displayAllLoans() {
        loans.forEach(System.out::println);
    }
}

package com.example.library;

/**
 * The Book class represents a book in the library system.
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private int copiesAvailable;

    /**
     * Constructor for the Book class.
     *
     * @param isbn            The ISBN of the book.
     * @param title           The title of the book.
     * @param author          The author of the book.
     * @param copiesAvailable The number of copies available in the library.
     */
    public Book(String isbn, String title, String author, int copiesAvailable) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        if (copiesAvailable < 0) {
            throw new IllegalArgumentException("Copies available cannot be negative.");
        }
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    // Getters and Setters
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) {
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        this.isbn = isbn;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        this.title = title;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) {
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty.");
        }
        this.author = author;
    }

    public int getCopiesAvailable() { return copiesAvailable; }
    public void setCopiesAvailable(int copiesAvailable) {
        if (copiesAvailable < 0) {
            throw new IllegalArgumentException("Copies available cannot be negative.");
        }
        this.copiesAvailable = copiesAvailable;
    }

    /**
     * Method to borrow a book.
     *
     * @throws IllegalStateException if no copies are available.
     */
    public void borrowBook() {
        if (copiesAvailable <= 0) {
            throw new IllegalStateException("No copies available to borrow.");
        }
        copiesAvailable--;
    }

    /**
     * Method to return a book.
     */
    public void returnBook() {
        copiesAvailable++;
    }

    @Override
    public String toString() {
        return String.format("Book[ISBN=%s, Title=%s, Author=%s, Copies Available=%d]",
                isbn, title, author, copiesAvailable);
    }
}

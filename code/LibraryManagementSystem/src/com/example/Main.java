package com.example.library;

public class Main {
    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();

        // Adding books to the library
        Book book1 = new Book("1234567890", "The Great Gatsby", "F. Scott Fitzgerald", 3);
        Book book2 = new Book("0987654321", "1984", "George Orwell", 2);
        library.addBook(book1);
        library.addBook(book2);

        // Registering members
        Member member1 = new Member("M001", "Alice Johnson");
        Member member2 = new Member("M002", "Bob Smith");
        library.registerMember(member1);
        library.registerMember(member2);

        // Issuing loans
        Loan loan1 = library.issueLoan("M001", "1234567890");
        Loan loan2 = library.issueLoan("M002", "0987654321");

        // Displaying current state
        System.out.println("Books in Library:");
        library.displayAllBooks();

        System.out.println("\nMembers:");
        library.displayAllMembers();

        System.out.println("\nLoans:");
        library.displayAllLoans();

        // Returning a book
        library.returnLoan(loan1);

        // Checking overdue loans (assuming some time has passed)
        System.out.println("\nOverdue Loans:");
        library.getOverdueLoans().forEach(System.out::println);
    }
}

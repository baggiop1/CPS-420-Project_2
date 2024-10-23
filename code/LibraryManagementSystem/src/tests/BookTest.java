package tests;

import com.example.Book;

public class BookTest {
    // Create private Book object
    private Book book;

    @BeforeEach
    public void setup() {
        book = new Book("123456789", "John Johnson Jr.", "5");
    }

    // Test valid and invalid constructor cases
    @Test
    public void testConstructor_ValidParameters() {
        assertNotNull(book, "Book should be created with valid parameters.");
    }

    @Test
    public void testConstructor_InvalidISBN_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("", "Title", "Author", 5);
        });
        assertEquals("ISBN cannot be empty.", exception.getMessage());
    }
}
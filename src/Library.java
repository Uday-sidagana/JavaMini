import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN) && book.isAvailable()) {
                return book;
            }
        }
        return null; // Book not found or not available
    }

    public boolean checkOutBook(String ISBN) {
        Book book = findBook(ISBN);
        if (book != null) {
            book.setAvailable(false);
            System.out.println("Book [" + book.getTitle() + "] checked out successfully.");
            return true;
        } else {
            System.out.println("Book is not available.");
            return false;
        }
    }

    public boolean returnBook(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                book.setAvailable(true);
                System.out.println("Book [" + book.getTitle() + "] returned successfully.");
                return true;
            }
        }
        return false; // Book not found
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibraryManagementSystem {
    private static Library library = new Library();

    public static void main(String[] args) {
        // Adding sample books
        addSampleBooks();
        SwingUtilities.invokeLater(LibraryManagementSystem::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create main panel with border layout for better arrangement
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Text Area
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Control Panel for buttons
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.add(controlPanel, BorderLayout.PAGE_END);

        // Styling Buttons
        JButton checkoutButton = new JButton("Check Out");
        JButton returnButton = new JButton("Return");
        styleButton(checkoutButton);
        styleButton(returnButton);

        // Adding Actions
        checkoutButton.addActionListener(e -> performCheckOut(textArea));
        returnButton.addActionListener(e -> performReturn(textArea));

        controlPanel.add(checkoutButton);
        controlPanel.add(returnButton);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Update the book list initially
        updateBooksList(textArea);
    }

    private static void performCheckOut(JTextArea textArea) {
        String ISBN = JOptionPane.showInputDialog("Enter ISBN to check out:");
        if (ISBN != null && library.checkOutBook(ISBN)) {
            updateBooksList(textArea);
        } else {
            JOptionPane.showMessageDialog(null, "Book is not available or not found.");
        }
    }

    private static void performReturn(JTextArea textArea) {
        String ISBN = JOptionPane.showInputDialog("Enter ISBN to return:");
        if (ISBN != null && library.returnBook(ISBN)) {
            updateBooksList(textArea);
        } else {
            JOptionPane.showMessageDialog(null, "Book not found.");
        }
    }

    private static void updateBooksList(JTextArea textArea) {
        StringBuilder booksList = new StringBuilder();
        for (Book book : library.getAllBooks()) {
            booksList.append(book.getTitle())
                    .append(" - ").append(book.getAuthor())
                    .append(" (ISBN: ").append(book.getISBN())
                    .append(") - ").append(book.isAvailable() ? "Available" : "Checked Out")
                    .append("\n");
        }
        textArea.setText(booksList.toString());
    }

    private static void styleButton(JButton button) {
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private static void addSampleBooks() {
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "1234567890"));
        library.addBook(new Book("1984", "George Orwell", "0987654321"));
        library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", "1122334455"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "2233445566"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "3344556677"));
        // Add more sample books as needed
    }
}

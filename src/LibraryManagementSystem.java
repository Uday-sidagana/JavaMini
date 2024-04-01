import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LibraryManagementSystem {
    private static Library library = new Library();

    public static void main(String[] args) {
        // Adding sample books
        addSampleBooks();
        SwingUtilities.invokeLater(LibraryManagementSystem::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Library Management System(21BCS-7418,6480,4308)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Main panel with a more vibrant background color
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(231, 231, 117)); // Light gray background

        // Text Area with a bit of styling
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.BOLD, 12));
        textArea.setForeground(new Color(33, 37, 41)); // Darker text for better readability
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(255, 151, 75), 2)); // Blue border for the scroll pane
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Control Panel for buttons with a complementary background color
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        controlPanel.setBackground(new Color(224, 242, 241)); // A soft, mint-like background
        mainPanel.add(controlPanel, BorderLayout.PAGE_END);

        // Styling Buttons with vibrant colors and hover effects
        JButton checkoutButton = new JButton("Check Out");
        JButton returnButton = new JButton("Return");
        styleButton(checkoutButton, new Color(0, 255, 52), new Color(38, 166, 154)); // Teal color scheme
        styleButton(returnButton, new Color(255, 87, 34), new Color(230, 74, 25)); // Deep orange color scheme

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

    private static void styleButton(JButton button, Color bgColor, Color hoverColor) {
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(hoverColor);
            }

            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
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

    private static void addSampleBooks() {
        library.addBook(new Book("The Hobbit", "J.R.R. Tolkien", "1234567890"));
        library.addBook(new Book("1984", "George Orwell", "0987654321"));
        library.addBook(new Book("The Lord of the Rings", "J.R.R. Tolkien", "1122334455"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "2233445566"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "3344556677"));
    }
}
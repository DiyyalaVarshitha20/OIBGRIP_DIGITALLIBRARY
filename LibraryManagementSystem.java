import java.util.ArrayList;
import java.util.List;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", available=" + available +
                '}';
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}

class Member {
    private String memberId;
    private String name;
    private List<Book> borrowedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            borrowedBooks.add(book);
            System.out.println("Book successfully borrowed: " + book.getTitle());
        } else {
            System.out.println("Book is not available for borrowing: " + book.getTitle());
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.setAvailable(true);
            borrowedBooks.remove(book);
            System.out.println("Book successfully returned: " + book.getTitle());
        } else {
            System.out.println("You did not borrow this book: " + book.getTitle());
        }
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("B001", "Introduction to Java", "John Doe");
        Book book2 = new Book("B002", "Data Structures and Algorithms", "Jane Smith");

        library.addBook(book1);
        library.addBook(book2);

        Member member1 = new Member("M001", "Alice");
        Member member2 = new Member("M002", "Bob");

        library.getAllBooks().forEach(System.out::println);

        member1.borrowBook(book1);
        member1.borrowBook(book2);
        member2.borrowBook(book1);

        member1.returnBook(book1);
        member2.borrowBook(book1);

        member1.getBorrowedBooks().forEach(System.out::println);
        member2.getBorrowedBooks().forEach(System.out::println);
    }
}
package version2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Human implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

class Author extends Human {
    public Author(String firstName, String lastName) {
        super(firstName, lastName);
    }
}

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient String title; // Позначаємо як transient
    private List<Author> authors;

    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    // Ручне керування серіалізацією
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Зберігаємо автоматично серіалізовані поля
        out.writeObject(title); // Зберігаємо title вручну
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Відновлюємо автоматично серіалізовані поля
        title = (String) in.readObject(); // Відновлюємо title вручну
    }
}

class BookReader extends Human implements Serializable {
    private static final long serialVersionUID = 1L;
    private int registrationNumber;
    private List<Book> borrowedBooks;

    public BookReader(String firstName, String lastName, int registrationNumber) {
        super(firstName, lastName);
        this.registrationNumber = registrationNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    @Override
    public String toString() {
        return super.toString() + " (Reg. No: " + registrationNumber + ")";
    }
}

class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Book> books;
    private List<BookReader> readers;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<BookReader> getReaders() {
        return readers;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addReader(BookReader reader) {
        readers.add(reader);
    }

    // Ручне керування серіалізацією
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Зберігаємо автоматично серіалізовані поля
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Відновлюємо автоматично серіалізовані поля
    }
}


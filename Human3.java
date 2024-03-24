package version3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Human implements Externalizable {
    String firstName;
    String lastName;

    public Human() {
    }

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
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(lastName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName = (String) in.readObject();
        lastName = (String) in.readObject();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

class Author extends Human {
    public Author() {
    }

    public Author(String firstName, String lastName) {
        super(firstName, lastName);
    }
}

class Book implements Externalizable {
    private String title;
    private List<Author> authors;

    public Book() {
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeObject(authors);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        authors = (List<Author>) in.readObject();
    }
}

class BookReader extends Human implements Externalizable {
    private int registrationNumber;
    private List<Book> borrowedBooks;

    public BookReader() {
    }

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
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(lastName);
        out.writeInt(registrationNumber);
        out.writeObject(borrowedBooks);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName = (String) in.readObject();
        lastName = (String) in.readObject();
        registrationNumber = in.readInt();
        borrowedBooks = (List<Book>) in.readObject();
    }

    @Override
    public String toString() {
        return super.toString() + " (Reg. No: " + registrationNumber + ")";
    }
}

class Library implements Externalizable {
    private String name;
    private List<Book> books;
    private List<BookReader> readers;

    public Library() {
    }

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeObject(books);
        out.writeObject(readers);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        books = (List<Book>) in.readObject();
        readers = (List<BookReader>) in.readObject();
    }
}


package version3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDriver {
    public static void serializeObject(String fileName, Object obj) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) {
        Object obj = null;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))) {
            obj = is.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void main(String[] args) {
        // Creating authors
        Author author1 = new Author("John", "Doe");
        Author author2 = new Author("Jane", "Smith");

        // Creating books
        List<Author> book1Authors = new ArrayList<>();
        book1Authors.add(author1);
        Book book1 = new Book("Book 1", book1Authors);

        List<Author> book2Authors = new ArrayList<>();
        book2Authors.add(author2);
        Book book2 = new Book("Book 2", book2Authors);

        // Creating readers
        BookReader reader1 = new BookReader("Alice", "Johnson", 1001);
        BookReader reader2 = new BookReader("Bob", "Williams", 1002);

        // Creating library
        Library library = new Library("Central Library");

        // Adding books to the library
        library.addBook(book1);
        library.addBook(book2);

        // Adding readers to the library
        library.addReader(reader1);
        library.addReader(reader2);

        // Borrowing books
        reader1.borrowBook(book1);
        reader2.borrowBook(book2);

        // Serializing library
        serializeObject("library.ser", library);

        // Deserializing library
        Library deserializedLibrary = (Library) deSerializeObject("library.ser");

        // Showing the state of deserialized library
        System.out.println("Deserialized Library Name: " + deserializedLibrary.getName());
        System.out.println("Books in Deserialized Library:");
        for (Book book : deserializedLibrary.getBooks()) {
            System.out.println(book.getTitle());
        }
        System.out.println("Readers in Deserialized Library:");
        for (BookReader reader : deserializedLibrary.getReaders()) {
            System.out.println(reader.getFirstName() + " " + reader.getLastName());
        }
    }
}

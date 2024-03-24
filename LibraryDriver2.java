package version2;

import version2.Author;

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
        version2.Author author1 = new version2.Author("John", "Doe");
        version2.Author author2 = new version2.Author("Jane", "Smith");

        // Creating books
        List<version2.Author> book1Authors = new ArrayList<>();
        book1Authors.add(author1);
        version2.Book book1 = new version2.Book("version2.Book 1", book1Authors);

        List<version2.Author> book2Authors = new ArrayList<>();
        book2Authors.add(author2);
        version2.Book book2 = new version2.Book("version2.Book 2", book2Authors);

        // Creating readers
        version2.BookReader reader1 = new version2.BookReader("Alice", "Johnson", 1001);
        version2.BookReader reader2 = new version2.BookReader("Bob", "Williams", 1002);

        // Creating library
        version2.Library library = new version2.Library("Central version2.Library");

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
        version2.Library deserializedLibrary = (version2.Library) deSerializeObject("library.ser");

        // Showing the state of deserialized library
        System.out.println("Deserialized version2.Library Name: " + deserializedLibrary.getName());
        System.out.println("Books in Deserialized version2.Library:");
        for (version2.Book book : deserializedLibrary.getBooks()) {
            System.out.println(book.getTitle());
        }
        System.out.println("Readers in Deserialized version2.Library:");
        for (version2.BookReader reader : deserializedLibrary.getReaders()) {
            System.out.println(reader.getFirstName() + " " + reader.getLastName());
        }
    }
}

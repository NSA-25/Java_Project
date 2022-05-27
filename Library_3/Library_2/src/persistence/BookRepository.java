package persistence;

import objects.Author;
import objects.Book;
import service.DBService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository implements GenericInterface<Book>
{
    private Map<String, Book> books= new HashMap<>();
    private DBService conn;
    public BookRepository(DBService conn) {
        this.conn = conn;
    }
    public Map<String, Book> getBooks() { return this.books; }
    public void create(Book b)
    {
        books.put(b.getBookId(), b);
    }
    public void read(String id)
    {
        if (books.containsKey(id))
        {
            System.out.println(books.get(id));
            return;
        }
        System.out.println("Nu exista nicio carte cu acest id");
    }
    public void readAll()
    {
        for (Book b: books.values())
        {
            System.out.println(b);
        }
    }

    public List<Book> readall() throws SQLException
    {
//        private String bookId;
//        private String title;
//        private int publicationYear;
//        private String category;
//        private Author author;
        PreparedStatement stmt = conn.prepareStatement("Select * from Books");
        ResultSet rs = stmt.executeQuery();
        List<Book> result = new ArrayList<>();
        while (rs.next()) {
            int authorId = rs.getInt("authorId");
            String bookId = rs.getString("bookid");
            String title = rs.getString("title");
            int publicationYear = rs.getInt("publicationYear");
            String category = rs.getString("category");
            result.add(new Book(title, publicationYear, category, authorId, bookId));
        }
        return result;
    }
    public void delete(String id)
    {
        if (books.containsKey(id))
        {
            books.remove(id);
            return;
        }
        System.out.println("Nu exista nicio carte cu acest id");
    }
}

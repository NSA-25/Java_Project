package persistence;

import objects.Book;

import java.util.HashMap;
import java.util.Map;

public class BookRepository implements GenericInterface<Book>
{
    private Map<String, Book> books= new HashMap<>();

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
    public void delete(String id)
    {
        if (books.containsKey(id))
        {
            books.get(id).getAuthor().deleteBook(id);
            books.remove(id);
            return;
        }
        System.out.println("Nu exista nicio carte cu acest id");
    }
}

package persistence;

import objects.Author;
import objects.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AuthorRepository implements AuthorInterface<Author>
{

    private Map<Integer, Author> authors = new HashMap<>();

    public Map<Integer, Author> getAuthors() { return this.authors; }

    public void create(Author a)
    {
        authors.put(a.getAuthorId(), a);
    }
    public void read(int id)
    {
        if (authors.containsKey(id))
        {
                System.out.println(authors.get(id));
                return;
        }
        System.out.println("Nu exista niciun autor cu datele specificate");
    }
    public void readAll()
    {
        for (Author a: authors.values())
        {
            System.out.println(a);
        }
    }
    public void delete(int id, Map<String, Book> books)
    {
        for (int i = 0; i < authors.size(); i++)
        {
            if (authors.containsKey(id))
            {
                for (Book b: books.values())
                {
                    if (b.getAuthor() == id)
                    {
                        System.out.println("Acest autor a scris niste carti care inca sunt in baza de date. Modificati cartile respective!");
                        return;
                    }
                }
                authors.remove(i);
                return;
            }
        }
        System.out.println("Nu exista niciun autor cu datele specificate");
    }
}

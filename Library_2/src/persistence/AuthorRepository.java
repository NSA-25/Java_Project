package persistence;

import objects.Author;

import java.util.ArrayList;
import java.util.Scanner;

public class AuthorRepository implements AuthorInterface<Author>
{

    private ArrayList<Author> authors = new ArrayList<>();

    public ArrayList<Author> getAuthors() { return this.authors; }
    public void create(Author a)
    {
        authors.add(a);
    }
    public void read(String first, String last)
    {
        for (Author a: authors)
        {
            if (a.getLastName().equals(last) && a.getFirstName().equals(first))
            {
                System.out.println(a);
                return;
            }
        }
        System.out.println("Nu exista niciun autor cu datele specificate");
    }
    public void readAll()
    {
        for (Author a: authors)
        {
            System.out.println(a);
        }
    }
    public void delete(String first, String last)
    {
        for (int i = 0; i < authors.size(); i++)
        {
            if (authors.get(i).getLastName().equals(last) && authors.get(i).getFirstName().equals(first))
            {
                if (authors.get(i).getBooks().size() > 0)
                {
                    System.out.println("Acest autor a scris niste carti care inca sunt in baza de date. Modificati cartile respective!");
                    return;
                }
                authors.remove(i);
                return;
            }
        }
        System.out.println("Nu exista niciun autor cu datele specificate");
    }
}

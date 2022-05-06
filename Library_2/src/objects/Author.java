package objects;


import java.util.ArrayList;
import service.Service;
public class Author extends Person
{
    private ArrayList<String> books = new ArrayList<>();
    public Author(String firstName, String lastName)
    {
        super(firstName, lastName);
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public ArrayList<String> getBooks() { return this.books; }
    public void addBook(String b)
    {
        this.books.add(b);
    }
    public void deleteBook(String s)
    {
        for (int i = 0; i < books.size(); i++)
        {
            if (books.get(i).equals(s))
            {
                books.remove(i);
                return;
            }
        }
    }
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (String i: books)
        {
            s.append(i);
        }
        return "Prenume: " + this.firstName + " Nume: " + this.lastName + " Carti publicate: " + s;
    }
}

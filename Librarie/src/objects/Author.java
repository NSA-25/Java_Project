package objects;



import java.util.ArrayList;

public class Author extends Person
{
    ArrayList<Book> books = new ArrayList<>();
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
    public void addBook(Book b)
    {
        this.books.add(b);
    }
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        for (Book i: books)
        {
            s.append(i.getTitle());
            s.append("\n");
        }
        return "Preume: " + this.firstName + " Nume" + this.lastName + " Carti publicate: ";
    }
}

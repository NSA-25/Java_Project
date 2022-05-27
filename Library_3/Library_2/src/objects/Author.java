package objects;


import java.util.ArrayList;
import service.Service;
public class Author extends Person
{
    private int AuthorId;
    public Author(String firstName, String lastName, int AuthorId)
    {
        super(firstName, lastName);
        this.AuthorId = AuthorId;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public void setLast(String last) {this.lastName = last;}
    public String getFirstName()
    {
        return this.firstName;
    }
    public void setFirst(String first) {this.firstName = first;}
    public int getId() {return this.AuthorId;}

    public int getAuthorId(){return this.AuthorId;}
    @Override
    public String toString()
    {
        StringBuilder s = new StringBuilder();
        return "Prenume: " + this.firstName + " Nume: " + this.lastName + " Id: " + this.AuthorId;
    }
}

package objects;

import objects.Person;

public class Client extends Person
{
    private String CNP;
    private static int id = 0;
    private int clientId;
    public Client(String firstName, String lastName, String CNP)
    {
        super(firstName, lastName);
        this.CNP = CNP;
        this.clientId = id;
        id++;
    }
    public String getCNP()
    {
        return this.CNP;
    }
    public int getID()
    {
        return this.clientId;
    }
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    @Override
    public String toString()
    {
        return "Id: " + this.clientId + " Nume: "+ this.lastName + " Prenume: " + this.firstName + " CNP: " + this.CNP;
    }
}

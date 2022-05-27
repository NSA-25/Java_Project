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
    public void setCNP(String CNP) {this.CNP = CNP;}
    public int getID()
    {
        return this.clientId;
    }
    public void setID(int id) {this.clientId = id;}
    public String getFirstName()
    {
        return this.firstName;
    }
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName()
    {
        return this.lastName;
    }
    public void setLastName(String lasttName) {this.lastName = lasttName;}
    @Override
    public String toString()
    {
        return "Id: " + this.clientId + " Nume: "+ this.lastName + " Prenume: " + this.firstName + " CNP: " + this.CNP;
    }
}

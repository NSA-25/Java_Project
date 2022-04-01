package objects;

import objects.Person;

import java.time.LocalDate;

public abstract class Employee extends Person
{
    private static int id = 0;
    private int employeeId;
    protected int salary;
    protected LocalDate dataAngajarii;
    public Employee(String firstName, String lastName, int salary, int year, int month, int day)
    {
        super(firstName, lastName);
        this.dataAngajarii = LocalDate.of(year, month, day);
        this.salary = salary;
        this.employeeId = id;
        id++;
    }
    public int getId()
    {
        return this.employeeId;
    }
    public abstract void jobDescription();
    public abstract void info();
    @Override
    public String toString()
    {
        return "Id: " + this.employeeId + " Nume: "+ this.lastName + " Prenume: " + this.firstName + " Salariu: " + this.salary + " Data anajarii: " + this.dataAngajarii;
    }

}

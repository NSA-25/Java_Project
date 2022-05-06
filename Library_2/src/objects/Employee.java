package objects;

import objects.Person;

import java.time.LocalDate;

public abstract class Employee extends Person
{
    private static int id = 0;
    private int employeeId;
    protected int salary;
    protected LocalDate hire_date;
    protected String type = "Placeholder";
    public Employee(String firstName, String lastName, int salary, int year, int month, int day)
    {
        super(firstName, lastName);
        this.hire_date= LocalDate.of(year, month, day);
        this.salary = salary;
        this.employeeId = id;
        id++;
    }
    public int getId()
    {
        return this.employeeId;
    }
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public int getSalary() {return this.salary;}
    public LocalDate getDate() {return this.hire_date;}

    public String getType() {return this.type;}
    public abstract void jobDescription();
    public abstract void info();
    @Override
    public String toString()
    {
        return "Id: " + this.employeeId + " Nume: "+ this.lastName + " Prenume: " + this.firstName + " Salariu: " + this.salary + " Data angajarii: " + this.hire_date + " Tipul jobului: " + this.type;
    }

}

package objects;

import objects.Person;

import java.time.LocalDate;

public abstract class Employee extends Person
{
    private int employeeId;
    protected int salary;
    protected LocalDate hire_date;
    protected String type = "Placeholder";
    public Employee(String firstName, String lastName, int salary, int year, int month, int day)
    {
        super(firstName, lastName);
        this.hire_date= LocalDate.of(year, month, day);
        this.salary = salary;
        this.employeeId = 0;;
    }
    public int getId()
    {
        return this.employeeId;
    }
    public void setId(int id1) {this.employeeId = id1;}
    public String getFirstName() {return this.firstName;}
    public void setFirst(String first) {this.firstName = first;}
    public String getLastName() {return this.lastName;}
    public void setLast(String last) {this.lastName = last;}
    public int getSalary() {return this.salary;}
    public void setSalary(int salary) {this.salary = salary;}
    public LocalDate getDate() {return this.hire_date;}
    public int getYear() {return this.hire_date.getYear();}
    public int getMonth() {return this.hire_date.getMonthValue();}
    public int getDay() {return this.hire_date.getDayOfMonth();}
    public String getType() {return this.type;}
    public abstract void jobDescription();
    public abstract void info();
    @Override
    public String toString()
    {
        return "Id: " + this.employeeId + " Nume: "+ this.lastName + " Prenume: " + this.firstName + " Salariu: " + this.salary + " Data angajarii: " + this.hire_date + " Tipul jobului: " + this.type;
    }

}

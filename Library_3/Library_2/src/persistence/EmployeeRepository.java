package persistence;

import objects.Client;
import objects.Employee;

import java.util.Map;

public class EmployeeRepository implements EmployeeInterface<Employee>
{
    private Employee[] employees = new Employee[10];
    public Employee[] getEmployees(){ return this.employees; }
    public void create(Employee e)
    {
        for (int i = 0; i < employees.length; i++)
        {
            if (employees[i] == null)
            {
                employees[i] = e;
                e.setId(i);
                return;
            }
        }
        System.out.println("Spatiu insuficient");
    }
    public void read(int id)
    {
        for (Employee employee : employees)
        {
            if (employee != null)
            {
                if (employee.getId() == id)
                {
                    System.out.println(employee);
                    employee.jobDescription();
                    employee.info();
                    return;
                }
            }
        }
        System.out.println("Nu a fost gasit un angajat cu acest id");
    }
    public void readAll()
    {
        for (Employee employee : employees)
        {
            if (employee != null)
            {
                System.out.println(employee);
            }
        }
    }
    public void delete(int id)
    {
        for (int i = 0; i < employees.length; i++)
        {
            if (employees[i] != null)
            {
                if (employees[i].getId() == id)
                {
                    employees[i] = null;
                    return;
                }
            }
        }
        System.out.println("Nu a fost gasit un angajat cu acest id");
    }
}

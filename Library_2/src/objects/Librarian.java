package objects;

public class Librarian extends Employee
{
    private String educationLevel;
    public Librarian(String firstName, String lastName, int salary, int year, int month, int day, String educationLevel)
    {
        super(firstName, lastName, salary, year, month, day);
        this.educationLevel = educationLevel;
        this.type = "Bibliotecar";
    }

    public String getEducationLevel() {return this.educationLevel;}
    @Override
    public void jobDescription()
    {
        System.out.println("Un bibliotecar este responsabil cu colectarea, organizarea și eliberarea resurselor bibliotecii. Atribuțiile lor includ emiterea de resurse, catalogarea cărților și efectuarea de audituri regulate.");
    }
    @Override
    public void info()
    {
        System.out.println(this.firstName + " " +  this.lastName + " are nivelul de educatie " + this.educationLevel + "\n");
    }
}

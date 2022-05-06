package objects;

public class IT extends Employee
{
    private int experienceYears;
    public IT(String firstName, String lastName, int salary, int year, int month, int day, int experience)
    {
        super(firstName, lastName, salary, year, month, day);
        this.experienceYears = experience;
        this.type = "IT";
    }

    public int getXP() {return this.experienceYears;}
    @Override
    public void jobDescription()
    {
        System.out.println("Un profesionist in informatii sau un specialist in informatii este cineva care colecteaza, inregistreaza, organizeaza, stocheaza, pastreaza, preia si difuzeaza informatii tiparite sau digitale.");
    }
    @Override
    public void info()
    {
        System.out.println(this.firstName + " " +  this.lastName + " are ani de experienta: " + this.experienceYears + "\n");
    }
}

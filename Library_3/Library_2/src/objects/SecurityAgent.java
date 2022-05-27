package objects;

import java.time.LocalDate;
import java.time.Period;

public class SecurityAgent extends Employee
{
    private String securityFirm;
    private int contractLength;
    public SecurityAgent(String firstName, String lastName, int salary, int year, int month, int day, String securityFirm, int contractDuration)
    {
        super(firstName, lastName, salary, year, month, day);
        this.securityFirm = securityFirm;
        this.contractLength = contractDuration;
        this.type = "Securitate";
    }

    public String getCompany() {return this.securityFirm;}
    public int getContractLength() {return this.contractLength;}

    @Override
    public void jobDescription()
    {
        System.out.println("Ofiterii de securitate au sarcina de a securiza sediul si personalul prin starea in patrulare, monitorizarea echipamentelor de supraveghere, efectuarea inspectiilor, paza punctelor de intrare si verificarea vizitatorilor.");
    }
    @Override
    public void info()
    {
        LocalDate a = this.hire_date;
        LocalDate b = LocalDate.now();
        Period dif = Period.between(a, b);
        System.out.println("Durata contractului: " + this.contractLength);
        System.out.println("Firma angajatului: " + this.securityFirm);
        System.out.println("Angajatul a lucrat in aceasta librarie timp de:\nAni: " + dif.getYears() + " Luni: " + dif.getMonths() + " Zile: " + dif.getDays() + "\n");
    }
}

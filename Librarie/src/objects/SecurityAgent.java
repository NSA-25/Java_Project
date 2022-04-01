package objects;

import java.time.LocalDate;
import java.time.Period;

public class SecurityAgent extends Employee
{
    private String securityFirm;
    private int contractDuration;
    public SecurityAgent(String firstName, String lastName, int salary, int year, int month, int day, String securityFirm, int contractDuration)
    {
        super(firstName, lastName, salary, year, month, day);
        this.securityFirm = securityFirm;
        this.contractDuration = contractDuration;
    }
    @Override
    public void jobDescription()
    {
        System.out.println("Ofiterii de securitate au sarcina de a securiza sediul si personalul prin starea in patrulare, monitorizarea echipamentelor de supraveghere, efectuarea inspectiilor, paza punctelor de intrare si verificarea vizitatorilor.\n");
    }
    @Override
    public void info()
    {
        LocalDate a = this.dataAngajarii;
        LocalDate b = LocalDate.now();
        Period dif = Period.between(a, b);
        System.out.println("Durata contractului: " + this.contractDuration);
        System.out.println("Angajatul a lucrat in aceasta librarie timp de:\nAni: " + dif.getYears() + " Luni: " + dif.getMonths() + " Zile: " + dif.getDays());
    }
}

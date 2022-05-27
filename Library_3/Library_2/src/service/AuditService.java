package service;

import java.io.*;
import java.sql.Timestamp;

public class AuditService
{
    private AuditService() {}

    private static AuditService instance = null;

    public static AuditService getInstance()
    {
        if (instance == null) {instance = new AuditService();}
        return instance;
    }

    public void write(String Action)
    {
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        BufferedWriter j = null;
        try
        {
            j = new BufferedWriter(new FileWriter("src/data/audit.csv", true));
            StringBuilder s = new StringBuilder();
            s.append(Action);
            s.append(',');
            s.append(tm);
            System.out.println(s);
            j.write(s.toString());
            j.write("\n");
        }
        catch (RuntimeException | IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {
                if (j != null)
                {
                    j.close();
                }
            }
            catch (RuntimeException | IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}



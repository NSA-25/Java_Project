package service;

import java.util.ArrayList;
import java.io.*;
public class CSVService
{
    private CSVService() {}

    private static CSVService instance = null;

    public static CSVService getInstance()
    {
        if (instance == null) {instance = new CSVService();}
        return instance;
    }

    public ArrayList<String[]> read(String file)
    {
        ArrayList<String[]> arr = new ArrayList<>();
        String l;
        BufferedReader f = null;
        try
        {
            f = new BufferedReader(new FileReader(file));
            f.readLine();
            while((l = f.readLine()) != null)
            {
                String[] values = l.split(",");
                arr.add(values);
            }
        }
        catch (RuntimeException | IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {
                if (f != null)
                {
                    f.close();
                }
            }
            catch (RuntimeException | IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return arr;
    }

    public void write(ArrayList<String[]> arr, String file, String first)
    {
        BufferedWriter g = null;
        try
        {
            g = new BufferedWriter(new FileWriter(file));
            g.write(first);
            for (String[] str:arr)
            {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < str.length - 1; i++)
                {
                    s.append(str[i]);
                    s.append(',');
                }
                s.append(str[str.length - 1]);
                g.write(s.toString());
                g.write("\n");
            }
        }
        catch (RuntimeException | IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try {
                if (g != null)
                {
                    g.close();
                }
            }
            catch (RuntimeException | IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}

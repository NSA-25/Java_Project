package persistence;

import objects.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientRepository implements GenericInterface<Client>
{
    private Map<String, Client> clients = new HashMap<>();

    public Map<String, Client> getClients(){ return this.clients; }
    public void create(Client c)
    {
        clients.put(c.getCNP(), c);
    }
    public void read(String id)
    {
        if (clients.containsKey(id))
        {
            System.out.println(clients.get(id));
            return;
        }
        System.out.println("Nu exista niciun client cu acest CNP");
    }
    public void readAll()
    {
        for (Client c: clients.values())
        {
            System.out.println(c);
        }
    }
    public void delete(String id)
    {
        for (String s: clients.keySet())
        {
            if (s.equals(id))
            {
                clients.remove(id);
                return;
            }
        }
        System.out.println("Nu exista niciun client cu acest CNP");
    }
}

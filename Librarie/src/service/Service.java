package service;

import java.time.LocalDate;
import java.util.*;

import objects.*;

public class Service
{
    private ArrayList<Client> clients = new ArrayList<>();
    private Map<String, Book> books= new HashMap<>();
    private ArrayList<Author> authors = new ArrayList<>();
    private Employee[] employees = new Employee[10];
    private ArrayList<Borrowed> borrowedBooks = new ArrayList<>();
    public void createClient()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti prenumele clientului");
        String first = s.nextLine();
        System.out.println("Introduceti numele clientului");
        String last = s.nextLine();
        System.out.println("Introduceti CNP-ul clientului");
        String CNP = s.nextLine();
        Client c = new Client(first, last, CNP);
        clients.add(c);
    }
    public void printClient(int id)
    {
        if (clients.size() < id - 1)
        {
            System.out.println("Id invalid");
            return;
        }
        System.out.println(clients.get(id));
        System.out.println("CNP: " + clients.get(id).getCNP());
    }
    public void printClients()
    {
        for (Client c: clients)
        {
            System.out.println(c);
        }
    }
    public int exists (ArrayList<Author> authors, String last, String first)
    {
        int i = 0;
        for (Author a: authors)
        {
            i++;
            if (a.getLastName().equals(last) && a.getFirstName().equals(first))
            {
                return i;
            }
        }
        return 0;
    }
    public void createBook()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("1 Introduceti o carte noua.\n2 Adaugati un examplar al unei carti deja existente");
        String ans = s.nextLine();
        if (ans.equals("2"))
        {
            System.out.println("Introduceti id-ul cartii");
            String id = s.nextLine();
            if (!books.containsKey(id))
            {
                System.out.println("Nu exista nicio carte cu acest id");
                return;
            }
            books.get(id).addCopy();
        }
        else if (ans.equals("1"))
        {
            System.out.println("Introduceti titlul");
            String title = s.nextLine();
            System.out.println("Introduceti anul publicarii");
            int year = s.nextInt();
            s.nextLine();
            System.out.println("Introduceti categoria cartii");
            String category = s.nextLine();
            System.out.println("Introduceti numele autorului");
            String first = s.nextLine();
            System.out.println("Introduceti prenumele autorului");
            String last = s.nextLine();
            System.out.println("Creati un id unic pentru carte");
            String id = s.nextLine();
            while (true)
            {
                boolean flag;
                String finalId = id;
                flag = books.containsKey(finalId);
                if (flag)
                {
                    System.out.println("Id utilizat, introduceti altul");
                    id = s.nextLine();
                    continue;
                }
                break;
            }
            int ind = exists(authors, last, first);
            if (ind > 0)
            {
                Book b = new Book(title, year, category, authors.get(ind - 1), id);
                authors.get(ind - 1).addBook(b);
                books.put(b.getBookId(), b);
            }
            else
            {
                Author a = new Author(first, last);
                authors.add(a);
                Book b = new Book(title, year, category, a, id);
                books.put(b.getBookId(), b);
            }
        }
        else
        {
            System.out.println("Input invalid!");
        }
    }
    public void printBook(String id)
    {
        if (books.containsKey(id))
        {
            System.out.println(books.get(id));
            return;
        }
        System.out.println("Nu exista nicio carte cu acest id");
    }
    public void printBooks()
    {
        for (Book b: books.values())
        {
            System.out.println(b);
        }
    }
    public void deleteBook(String id)
    {
        if (books.containsKey(id))
        {
            books.remove(id);
            return;
        }
        System.out.println("Nu exista nicio carte cu acest id");
    }
    public void createAuthor()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Prenumele autorului: ");
        String first = s.nextLine();
        System.out.println("Numele autorului: ");
        String last = s.nextLine();
        Author a = new Author(first, last);
        authors.add(a);
    }
    public void printAuthor(String first, String last)
    {
        for (Author a: authors)
        {
            if (a.getLastName().equals(last) && a.getFirstName().equals(first))
            {
                System.out.println(a);
                return;
            }
        }
        System.out.println("Nu exista niciun autor cu datele specificate");
    }
    public void printAuthors()
    {
        for (Author a: authors)
        {
            System.out.println(a);
        }
    }
    public void createEmployee()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti jobul angajatului:\n1 Agent de paza\n2 Bibliotecar\n3 Personal IT");
        int type = s.nextInt();
        s.nextLine();
        if (type != 1 && type != 2 && type != 3)
        {
            System.out.println("Input invalid!");
            return;
        }
        System.out.println("Introduceti prenumele angajatului");
        String first = s.nextLine();
        System.out.println("Introduceti numele angajatului");
        String last = s.nextLine();
        System.out.println("Introduceti salariul angajatului");
        int salary = s.nextInt();
        System.out.println("Introduceti anul angajarii (in cifre)");
        int year = s.nextInt();
        System.out.println("Introduceti luna angajarii (in cifre)");
        int month = s.nextInt();
        System.out.println("Introduceti ziua angajarii (in cifre)");
        int day = s.nextInt();
        s.nextLine();
        if (type == 1)
        {
            System.out.println("Introduceti firma angajatului");
            String firm = s.nextLine();
            System.out.println("Introduceti cati ani dureaza contractul");
            int y = s.nextInt();
            s.nextLine();
            objects.SecurityAgent o = new objects.SecurityAgent(first, last, salary, year, month, day, firm, y);
            for (int i = 0; i < employees.length; i++)
            {
                if (employees[i] == null)
                {
                    employees[i] = o;
                    return;
                }
            }
            System.out.println("Spatiu insuficient");
        }
        if (type == 3)
        {
            System.out.println("Introduceti cati ani experienta are angajatul");
            int y = s.nextInt();
            s.nextLine();
            objects.IT o = new objects.IT(first, last, salary, year, month, day, y);
            for (int i = 0; i < employees.length; i++)
            {
                if (employees[i] == null)
                {
                    employees[i] = o;
                    return;
                }
            }
            System.out.println("Spatiu insuficient");
        }
        else
        {
            System.out.println("Introduceti studiile angajatului");
            String edu = s.nextLine();
            objects.Librarian o = new objects.Librarian(first, last, salary, year, month, day, edu);
            for (int i = 0; i < employees.length; i++)
            {
                if (employees[i] == null)
                {
                    employees[i] = o;
                   return;
                }
            }
            System.out.println("Spatiu insuficient");
        }
    }
    public void printEmployee()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti id-ul angajatului");
        int id = s.nextInt();
        s.nextLine();
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
    public void deleteEmployee()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti id-ul angajatului");
        int id = s.nextInt();
        s.nextLine();
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
    public void printEmployees()
    {
        for (Employee employee : employees)
        {
            if (employee != null)
            {
                System.out.println(employee);
            }
        }
    }
    public void borrowBook()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti id-ul clientului");
        int clientId = s.nextInt();
        s.nextLine();
        System.out.println("Introduceti id-ul cartii");
        String bookId = s.nextLine();
        Book book = null;
        if (books.containsKey(bookId))
        {
            if (books.get(bookId).getCount() > 0)
            {
                book = books.get(bookId);
                books.get(bookId).removecopy();
                LocalDate a = LocalDate.now();
                LocalDate b = LocalDate.now().plusDays(21);
                Borrowed c = new Borrowed(clientId, book, a, b);
                borrowedBooks.add(c);
                return;
            }
            System.out.println("Nu mai sunt exemplare disponibile");
        }
        else
        {
            System.out.println("Nu exista nicio carte cu acest id");
        }
    }
    public void returnBook()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti id-ul clientului");
        int clientId = s.nextInt();
        s.nextLine();
        System.out.println("Introduceti id-ul cartii");
        String bookId = s.nextLine();
        for (Borrowed b: borrowedBooks)
        {
            if (b.getBorrower() == clientId && b.getBook().equals(bookId))
            {
                books.get(b.getBookId()).addCopy();
                return;
            }
            borrowedBooks.remove(b);
        }
        System.out.println("Nu exista niciun imprumut cu datele specificate");
    }
    public void showHistory()
    {
        for (Borrowed b: borrowedBooks)
        {
            System.out.println(b);
        }
    }
}

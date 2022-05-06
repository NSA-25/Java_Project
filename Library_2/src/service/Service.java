package service;

import java.time.LocalDate;
import java.util.*;

import objects.*;
import persistence.AuthorRepository;
import persistence.BookRepository;
import persistence.ClientRepository;
import persistence.EmployeeRepository;

public class Service
{
    private ClientRepository clientRepository = new ClientRepository();
    private AuthorRepository authorRepository = new AuthorRepository();
    private BookRepository bookRepository = new BookRepository();
    private EmployeeRepository employeeRepository= new EmployeeRepository();

    private CSVService CSV = CSVService.getInstance();

//    private Map<String, Client> clients = new HashMap<>();
//    private Map<String, Book> books= new HashMap<>();
//    private ArrayList<Author> authors = new ArrayList<>();
//    private Employee[] employees = new Employee[10];
    private ArrayList<Borrowed> borrowedBooks = new ArrayList<>();

    public void startup()
    {
        ArrayList<String[]> clients = CSV.read("src/data/clients.csv");
        ArrayList<String[]> employees = CSV.read("src/data/employees.csv");
        ArrayList<String[]> authors = CSV.read("src/data/authors.csv");
        ArrayList<String[]> books = CSV.read("src/data/books.csv");
        for (String[] s: clients)
        {
            Client temp = new Client(s[1], s[2], s[3]);
            clientRepository.create(temp);
        }
        for (String[] s: employees)
        {
            String[] values = s[4].split("-");
            switch (s[6])
            {
                case "Bibliotecar" ->
                        employeeRepository.create(new Librarian(s[1], s[2], Integer.parseInt(s[3]), Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), s[5]));
                case "IT" ->
                        employeeRepository.create(new IT(s[1], s[2], Integer.parseInt(s[3]), Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(s[5])));
                case "Securitate" ->
                        employeeRepository.create(new SecurityAgent(s[1], s[2], Integer.parseInt(s[3]), Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), s[5], Integer.parseInt(s[7])));
                default -> System.out.println("Date invalide la angajatul: " + s[0]);
            }
        }
        for (String[] s: books)
        {
            String[] author = s[3].split(" ");
            Author a = new Author(author[0], author[1]);
            authorRepository.create(a);
            Book temp = new Book(s[0], Integer.parseInt(s[1]), s[2], a, s[4]);
            a.addBook(temp.getBookId());
            temp.changeCount(Integer.parseInt(s[5]));
            bookRepository.create(temp);
        }
        for (String[] s: authors)
        {
            boolean flag = true;
            for (Author a: authorRepository.getAuthors())
            {
                if (a.getLastName().equals(s[1]) && a.getFirstName().equals(s[0]))
                {
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                Author a = new Author(s[0], s[1]);
                authorRepository.create(a);
            }
        }
    }

    public void save()
    {
        String l1 = "id,first_name,last_name,cnp\n";
        String l2 = "title,year,category,author,bookid,nr_copies\n";
        String l3 = "first_name,last_name,books\n";
        String l4 = "id,firstname,lastname,salary,hire_date,education/experience/company,-/-/contract_length\n";
        ArrayList<String[]> clients = new ArrayList<>();
        ArrayList<String[]> employees = new ArrayList<>();
        ArrayList<String[]> authors = new ArrayList<>();
        ArrayList<String[]> books = new ArrayList<>();
        for (Client client : clientRepository.getClients().values())
        {
            String[] values;
            values = new String[4];
            values[0] = Integer.toString(client.getID());
            values[1] = client.getFirstName();
            values[2] = client.getLastName();
            values[3] = client.getCNP();
            clients.add(values);
        }
        CSV.write(clients, "src/data/clients.csv", l1);
        for (Book book : bookRepository.getBooks().values())
        {
            String[] values;
            values = new String[6];
            values[0] = book.getTitle();
            values[1] = Integer.toString(book.getYear());
            values[2] = book.getCategory();
            values[3] = book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName();
            values[4] = book.getBookId();
            values[5] = Integer.toString(book.getCount());
            books.add(values);
        }
        CSV.write(books, "src/data/books.csv", l2);
        for (Author author : authorRepository.getAuthors())
        {
            String[] values;
            values = new String[author.getBooks().size() + 2];
            values[0] = author.getFirstName();
            values[1] = author.getLastName();
            for (int i = 2; i < values.length; i++)
            {
                values[i] = author.getBooks().get(i-2);
            }
            authors.add(values);
        }
        CSV.write(authors, "src/data/authors.csv", l3);
        for (int i = 0; i < employeeRepository.getEmployees().length; i++)
        {
            String[] values;
            if (employeeRepository.getEmployees()[i] == null)
            {
                continue;
            }
            if (employeeRepository.getEmployees()[i] instanceof SecurityAgent)
            {
                values = new String[8];
                values[7] = String.valueOf(((SecurityAgent) employeeRepository.getEmployees()[i]).getContractLength());
            }
            else
            {
                values = new String[7];
            }
                values[0] = Integer.toString(employeeRepository.getEmployees()[i].getId());
                values[1] = employeeRepository.getEmployees()[i].getFirstName();
                values[2] = employeeRepository.getEmployees()[i].getLastName();
                values[3] = Integer.toString(employeeRepository.getEmployees()[i].getSalary());
                LocalDate ld = employeeRepository.getEmployees()[i].getDate();
                values[4] = ld.toString();
            if (employeeRepository.getEmployees()[i] instanceof SecurityAgent)
            {
                values[5] = ((SecurityAgent) employeeRepository.getEmployees()[i]).getCompany();
            }
            else if (employeeRepository.getEmployees()[i] instanceof Librarian)
            {
                values[5] = ((Librarian) employeeRepository.getEmployees()[i]).getEducationLevel();
            }
            else
            {
                values[5] = Integer.toString(((IT) employeeRepository.getEmployees()[i]).getXP());
            }
            values[6] = employeeRepository.getEmployees()[i].getType();
            employees.add(values);
        }
        CSV.write(employees, "src/data/employees.csv", l4);
    }
    public void createClient()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti prenumele clientului");
        String first = s.nextLine();
        System.out.println("Introduceti numele clientului");
        String last = s.nextLine();
        System.out.println("Introduceti CNP-ul clientului");
        String CNP = s.nextLine();
        while (true)
        {
            boolean flag;
            String finalCNP = CNP;
            flag = clientRepository.getClients().containsKey(finalCNP);
            if (flag)
            {
                System.out.println("CNP utilizat!");
                CNP = s.nextLine();
                continue;
            }
            break;
        }
        Client c = new Client(first, last, CNP);
        clientRepository.create(c);
    }
    public void printClient(String id)
    {
        clientRepository.read(id);
    }
    public void printClients()
    {
        clientRepository.readAll();
    }
    public void deleteClient(String id)
    {
        clientRepository.delete(id);
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
            if (!bookRepository.getBooks().containsKey(id))
            {
                System.out.println("Nu exista nicio carte cu acest id");
                return;
            }
            bookRepository.getBooks().get(id).addCopy();
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
                flag = bookRepository.getBooks().containsKey(finalId);
                if (flag)
                {
                    System.out.println("Id utilizat, introduceti altul");
                    id = s.nextLine();
                    continue;
                }
                break;
            }
            int ind = exists(authorRepository.getAuthors(), last, first);
            if (ind > 0)
            {
                Book b = new Book(title, year, category, authorRepository.getAuthors().get(ind - 1), id);
                authorRepository.getAuthors().get(ind - 1).addBook(b.getTitle());
                bookRepository.create(b);
            }
            else
            {
                Author a = new Author(first, last);
                authorRepository.create(a);
                Book b = new Book(title, year, category, a, id);
                authorRepository.getAuthors().get(authorRepository.getAuthors().size() - 1).addBook(b.getTitle());
                bookRepository.getBooks().put(b.getBookId(), b);

            }
        }
        else
        {
            System.out.println("Input invalid!");
        }
    }
    public void printBook(String id)
    {
        bookRepository.read(id);
    }
    public void printBooks()
    {
        bookRepository.readAll();
    }
    public void deleteBook(String id)
    {
        bookRepository.delete(id);
    }
    public void createAuthor()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Prenumele autorului: ");
        String first = s.nextLine();
        System.out.println("Numele autorului: ");
        String last = s.nextLine();
        Author a = new Author(first, last);
        authorRepository.create(a);
    }
    public void printAuthor(String first, String last)
    {
        authorRepository.read(first, last);
    }
    public void printAuthors()
    {
        authorRepository.readAll();
    }
    public void deleteAuthor(String first, String last)
    {
        authorRepository.delete(first, last);
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
            for (int i = 0; i < employeeRepository.getEmployees().length; i++)
            {
                if (employeeRepository.getEmployees()[i] == null)
                {
                    employeeRepository.getEmployees()[i] = o;
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
            for (int i = 0; i <  employeeRepository.getEmployees().length; i++)
            {
                if ( employeeRepository.getEmployees()[i] == null)
                {
                     employeeRepository.getEmployees()[i] = o;
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
            for (int i = 0; i <  employeeRepository.getEmployees().length; i++)
            {
                if (employeeRepository.getEmployees()[i] == null)
                {
                     employeeRepository.getEmployees()[i] = o;
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
        for (Employee employee :  employeeRepository.getEmployees())
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
    public void printEmployees()
    {
        employeeRepository.readAll();
    }
    public void deleteEmployee(int id)
    {
        for (int i = 0; i <  employeeRepository.getEmployees().length; i++)
        {
            if ( employeeRepository.getEmployees()[i] != null)
            {
                if ( employeeRepository.getEmployees()[i].getId() == id)
                {
                     employeeRepository.getEmployees()[i] = null;
                    return;
                }
            }
        }
        System.out.println("Nu a fost gasit un angajat cu acest id");
    }
    public void borrowBook()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Introduceti id-ul clientului");
        int clientId = s.nextInt();
        s.nextLine();
        System.out.println("Introduceti id-ul cartii");
        String bookId = s.nextLine();
        Book book;
        if (bookRepository.getBooks().containsKey(bookId))
        {
            if (bookRepository.getBooks().get(bookId).getCount() > 0)
            {
                book = bookRepository.getBooks().get(bookId);
                bookRepository.getBooks().get(bookId).removecopy();
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
                bookRepository.getBooks().get(b.getBookId()).addCopy();
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

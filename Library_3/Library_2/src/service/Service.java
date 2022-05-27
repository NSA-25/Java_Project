package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import objects.*;
import persistence.AuthorRepository;
import persistence.BookRepository;
import persistence.ClientRepository;
import persistence.EmployeeRepository;

public class Service
{
    private CSVService CSV = CSVService.getInstance();
    private AuditService audit = AuditService.getInstance();
    private DBService connection;
    {
        try
        {
            connection = DBService.getInstance();
        } catch (SQLException | ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    private ClientRepository clientRepository = new ClientRepository();
    private AuthorRepository authorRepository = new AuthorRepository();
    private BookRepository bookRepository = new BookRepository(connection);
    private EmployeeRepository employeeRepository= new EmployeeRepository();

//    private Map<String, Client> clients = new HashMap<>();
//    private Map<String, Book> books= new HashMap<>();
//    private ArrayList<Author> authors = new ArrayList<>();
//    private Employee[] employees = new Employee[10];
    private ArrayList<Borrowed> borrowedBooks = new ArrayList<>();

//    public void startup()
//    {
//        ArrayList<String[]> clients = CSV.read("src/data/clients.csv");
//        ArrayList<String[]> employees = CSV.read("src/data/employees.csv");
//        ArrayList<String[]> authors = CSV.read("src/data/authors.csv");
//        ArrayList<String[]> books = CSV.read("src/data/books.csv");
//        for (String[] s: clients)
//        {
//            Client temp = new Client(s[1], s[2], s[3]);
//            clientRepository.create(temp);
//        }
//        for (String[] s: employees)
//        {
//            String[] values = s[4].split("-");
//            switch (s[6])
//            {
//                case "Bibliotecar" ->
//                        employeeRepository.create(new Librarian(s[1], s[2], Integer.parseInt(s[3]), Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), s[5]));
//                case "IT" ->
//                        employeeRepository.create(new IT(s[1], s[2], Integer.parseInt(s[3]), Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Integer.parseInt(s[5])));
//                case "Securitate" ->
//                        employeeRepository.create(new SecurityAgent(s[1], s[2], Integer.parseInt(s[3]), Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), s[5], Integer.parseInt(s[7])));
//                default -> System.out.println("Date invalide la angajatul: " + s[0]);
//            }
//        }
//        for (String[] s: books)
//        {
//            Book temp = new Book(s[0], Integer.parseInt(s[1]), s[2], Integer.parseInt(s[3]), s[4]);
//            temp.changeCount(Integer.parseInt(s[5]));
//            bookRepository.create(temp);
//        }
//        for (String[] s: authors)
//        {
//            Author a = new Author(s[0], s[1], Integer.parseInt(s[2]));
//            authorRepository.create(a);
//        }
//    }
    public void startup() throws SQLException
    {
        PreparedStatement stmt = connection.prepareStatement("Select * from Clients");
        ResultSet rs = stmt.executeQuery();
        while (rs.next())
        {
            Client ob = new Client(rs.getString("first_name"), rs.getString("last_name"), rs.getString("Cnp"));
            clientRepository.create(ob);
            PreparedStatement upd = connection.prepareStatement("update Clients set id ="+ob.getID()+" where id ="+rs.getInt("id"));
            upd.execute();
        }
        stmt = connection.prepareStatement("Select * from Employees");
        rs = stmt.executeQuery();
        while (rs.next())
        {
            String type = rs.getString("type");
            switch (type)
            {
                case "Bibliotecar" ->
                        employeeRepository.create(new Librarian(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("salary"), rs.getInt("year"), rs.getInt("month"), rs.getInt("day"), rs.getString("a1")));
                case "IT" ->
                        employeeRepository.create(new IT(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("salary"), rs.getInt("year"), rs.getInt("month"), rs.getInt("day"), rs.getInt("a2")));
                case "Securitate" ->
                        employeeRepository.create(new SecurityAgent(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("salary"), rs.getInt("year"), rs.getInt("month"), rs.getInt("day") ,rs.getString("a1"),  rs.getInt("a2")));
                default -> System.out.println("Date invalide la startup: angajati");
            }
        }
        stmt = connection.prepareStatement("Select * from Authors");
        rs = stmt.executeQuery();
        while (rs.next())
        {
            Author ob = new Author(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("id"));
            authorRepository.create(ob);
        }
        stmt = connection.prepareStatement("Select * from Books");
        rs = stmt.executeQuery();
        while (rs.next())
        {
            Book ob = new Book(rs.getString("title"), rs.getInt("publication_year"), rs.getString("category"), rs.getInt("author_id"), rs.getString("id"));
            ob.changeCount(rs.getInt("count"));
            bookRepository.create(ob);
        }
    }
//    public void save()
//    {
//        String l1 = "id,first_name,last_name,cnp\n";
//        String l2 = "title,year,category,author,bookid,nr_copies\n";
//        String l3 = "first_name,last_name,books\n";
//        String l4 = "id,firstname,lastname,salary,hire_date,education/experience/company,-/-/contract_length\n";
//        ArrayList<String[]> clients = new ArrayList<>();
//        ArrayList<String[]> employees = new ArrayList<>();
//        ArrayList<String[]> authors = new ArrayList<>();
//        ArrayList<String[]> books = new ArrayList<>();
//        for (Client client : clientRepository.getClients().values())
//        {
//            String[] values;
//            values = new String[4];
//            values[0] = Integer.toString(client.getID());
//            values[1] = client.getFirstName();
//            values[2] = client.getLastName();
//            values[3] = client.getCNP();
//            clients.add(values);
//        }
//        CSV.write(clients, "src/data/clients.csv", l1);
//        for (Book book : bookRepository.getBooks().values())
//        {
//            String[] values;
//            values = new String[6];
//            values[0] = book.getTitle();
//            values[1] = Integer.toString(book.getYear());
//            values[2] = book.getCategory();
//            values[3] = Integer.toString(book.getAuthor());
//            values[4] = book.getBookId();
//            values[5] = Integer.toString(book.getCount());
//            books.add(values);
//        }
//        CSV.write(books, "src/data/books.csv", l2);
//        for (Author author : authorRepository.getAuthors().values())
//        {
//            String[] values;
//            values = new String[author.getBooks().size() + 2];
//            values[0] = author.getFirstName();
//            values[1] = author.getLastName();
//            values[2] = Integer.toString(author.getId());
//            for (int i = 3; i < values.length; i++)
//            {
//                values[i] = author.getBooks().get(i-2);
//            }
//            authors.add(values);
//        }
//        CSV.write(authors, "src/data/authors.csv", l3);
//        for (int i = 0; i < employeeRepository.getEmployees().length; i++)
//        {
//            String[] values;
//            if (employeeRepository.getEmployees()[i] == null)
//            {
//                continue;
//            }
//            if (employeeRepository.getEmployees()[i] instanceof SecurityAgent)
//            {
//                values = new String[8];
//                values[7] = String.valueOf(((SecurityAgent) employeeRepository.getEmployees()[i]).getContractLength());
//            }
//            else
//            {
//                values = new String[7];
//            }
//                values[0] = Integer.toString(employeeRepository.getEmployees()[i].getId());
//                values[1] = employeeRepository.getEmployees()[i].getFirstName();
//                values[2] = employeeRepository.getEmployees()[i].getLastName();
//                values[3] = Integer.toString(employeeRepository.getEmployees()[i].getSalary());
//                LocalDate ld = employeeRepository.getEmployees()[i].getDate();
//                values[4] = ld.toString();
//            if (employeeRepository.getEmployees()[i] instanceof SecurityAgent)
//            {
//                values[5] = ((SecurityAgent) employeeRepository.getEmployees()[i]).getCompany();
//            }
//            else if (employeeRepository.getEmployees()[i] instanceof Librarian)
//            {
//                values[5] = ((Librarian) employeeRepository.getEmployees()[i]).getEducationLevel();
//            }
//            else
//            {
//                values[5] = Integer.toString(((IT) employeeRepository.getEmployees()[i]).getXP());
//            }
//            values[6] = employeeRepository.getEmployees()[i].getType();
//            employees.add(values);
//        }
//        CSV.write(employees, "src/data/employees.csv", l4);
//    }7
    public void createClient()
    {
        audit.write("createClient");
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
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("insert into Clients values("+c.getID()+","+c.getFirstName()+","+c.getLastName()+","+c.getCNP()+")");
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void printClientByCnp(String id)
    {
        audit.write("printClientByCnp");
//        clientRepository.read(id);
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("Select * from Clients where cnp ="+id);
            ResultSet rs = stmt.executeQuery();
            clientRepository.read(rs.getString("cnp"));
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void printClientById(int id)
    {
        audit.write("printClientById");
        clientRepository.read(id);
    }
    public void printClients()
    {
        audit.write("printClients");
        clientRepository.readAll();
    }
    public void deleteClientByCnp(String id)
    {
        audit.write("deleteClientByCnp");
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("Delete from Clients where cnp ="+id);
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        clientRepository.delete(id);
    }
    public void deleteClientById(int id)
    {
        audit.write("deleteClientById");
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("Delete from Clients where id ="+id);
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        clientRepository.delete(id);
    }

    public void updateClientById(int id)
    {
        Scanner s = new Scanner(System.in);
        boolean flag = false;
        String cnpi = null;
        for (Client ob: clientRepository.getClients().values())
        {
            if (ob.getID() == id)
            {
                flag = true;
                cnpi = ob.getCNP();
                break;
            }
        }
        if (!flag)
        {
            System.out.println("Nu exista niciun client cu acest id");
            return;
        }
        System.out.println("Introduceti CNP-ul nou al clientului");
        String CNP = s.nextLine();
        System.out.println("Introduceti prenumele nou al clientului");
        String first = s.nextLine();
        System.out.println("Introduceti numele nou al clientului");
        String last = s.nextLine();
        audit.write("updateClientByCnp");
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("update Clients set cnp = "+CNP+", first_name = "+first+",last_name= "+last+"  where id ="+id);
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        Client ob = clientRepository.getClients().get(cnpi);
        ob.setFirstName(first);
        ob.setLastName(last);
        ob.setCNP(CNP);
        clientRepository.getClients().replace(cnpi, ob);
    }
    public void createBook()
    {
        audit.write("createBook");
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
            try
            {
                PreparedStatement stmt = connection.prepareStatement("update Books set count ="+bookRepository.getBooks().get(id).getCount()+" where id ="+id);
                stmt.execute();
            } catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
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
            System.out.println("Introduceti id-ul autorului");
            int id1 = s.nextInt();
            s.nextLine();
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
            boolean flag = authorRepository.getAuthors().containsKey(id1);
            if (flag)
            {
                Book b = new Book(title, year, category, id1, id);
                bookRepository.create(b);
                try
                {
                    PreparedStatement stmt = connection.prepareStatement("insert into Books values("+b.getId()+","+b.getYear()+","+b.getTitle()+","+b.getCategory()+","+b.getCount()+","+b.getAuthor()+")");
                    stmt.execute();
                } catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }
            else
            {
                Author a = new Author(first, last, id1);
                authorRepository.create(a);
                try
                {
                    PreparedStatement stmt = connection.prepareStatement("insert into Authors values("+a.getId()+","+a.getFirstName()+","+a.getLastName()+")");
                    stmt.execute();
                } catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
                Book b = new Book(title, year, category, id1, id);
                bookRepository.create(b);
                try
                {
                    PreparedStatement stmt = connection.prepareStatement("insert into Books values("+b.getId()+","+b.getYear()+","+b.getTitle()+","+b.getCategory()+","+b.getCount()+","+b.getAuthor()+")");
                    stmt.execute();
                } catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
        else
        {
            System.out.println("Input invalid!");
        }
    }
    public void printBook(String id)
    {
        audit.write("printBook");
        bookRepository.read(id);
    }
    public void printBooks()
    {
        audit.write("printBooks");
        bookRepository.readAll();
    }
    public void deleteBook(String id)
    {
        audit.write("deleteBook");
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("delete from Books where id ="+id);
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        bookRepository.delete(id);
    }
    public void updateBook(String id)
    {
        Scanner s = new Scanner(System.in);
        if (!bookRepository.getBooks().containsKey(id))
        {
            System.out.println("Nu exista nicio carte cu acest id");
            return;
        }
        System.out.println("Introduceti anul nou al cartii");
        int year = s.nextInt();
        s.nextLine();
        System.out.println("Introduceti titlul nou al cartii");
        String title = s.nextLine();
        System.out.println("Introduceti categoria noua al cartii");
        String category = s.nextLine();
        System.out.println("Introduceti numarul exemplarelor");
        int count = s.nextInt();
        s.nextLine();
        audit.write("updateBook");
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("update Books set publication_year = "+year+",title= "+title+", category = "+category+", count ="+count+"  where id ="+id);
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        Book ob = bookRepository.getBooks().get(id);
        ob.setYear(year);
        ob.setTitle(title);
        ob.setCategory(category);
        ob.setCount(count);
        bookRepository.getBooks().replace(id, ob);
    }
    public void createAuthor()
    {
        audit.write("createAuthor");
        Scanner s = new Scanner(System.in);
        System.out.println("Prenumele autorului: ");
        String first = s.nextLine();
        System.out.println("Numele autorului: ");
        String last = s.nextLine();
        System.out.println("Id-ul autorului: ");
        int id = s.nextInt();
        s.nextLine();
        if (authorRepository.getAuthors().containsKey(id))
        {
            System.out.println("Exista deja un autor cu acest id");
            return;
        }
        Author a = new Author(first, last, id);
        authorRepository.create(a);
        try
        {
            PreparedStatement stmt = connection.prepareStatement("insert into Authors values("+a.getId()+","+a.getFirstName()+","+a.getLastName()+")");
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        authorRepository.create(a);
    }
    public void printAuthor(int id)
    {
        audit.write("printAuthor");
        authorRepository.read(id);
    }
    public void printAuthors()
    {
        audit.write("printAuthors");
        authorRepository.readAll();
    }
    public void deleteAuthor(int id)
    {
        audit.write("deleteAuthor");
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("Delete from Authors where id ="+id);
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        authorRepository.delete(id, bookRepository.getBooks());
    }
    public void updateAuthor(int id)
    {
        audit.write("updateAuthor");
        Scanner s = new Scanner(System.in);
        if (!authorRepository.getAuthors().containsKey(id))
        {
            System.out.println("Nu exista niciun autor cu acest id");
            return;
        }
        System.out.println("Introduceti numele nou al autorului");
        String last = s.nextLine();
        System.out.println("Introduceti prenumele nou al autorului");
        String first = s.nextLine();
        PreparedStatement stmt = null;
        try
        {
            stmt = connection.prepareStatement("update Authors set first_name = "+first+", last_name = "+last+" where id = "+id);
            stmt.execute();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        Author ob = authorRepository.getAuthors().get(id);
        ob.setFirst(first);
        ob.setLast(last);
        authorRepository.getAuthors().replace(id, ob);
    }
    public void createEmployee()
    {
        audit.write("createEmployee");
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
            SecurityAgent o = new SecurityAgent(first, last, salary, year, month, day, firm, y);
            for (int i = 0; i < employeeRepository.getEmployees().length; i++)
            {
                if (employeeRepository.getEmployees()[i] == null)
                {
                    employeeRepository.getEmployees()[i] = o;
                    o.setId(i);
                    try
                    {
                        PreparedStatement stmt = connection.prepareStatement("insert into Employees values("+o.getId()+","+o.getFirstName()+","+o.getLastName()+","+o.getSalary()+","+o.getYear()+","+o.getMonth()+","+o.getDay()+","+o.getCompany()+","+o.getContractLength()+",'Securitate')");
                        stmt.execute();
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
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
            IT o = new IT(first, last, salary, year, month, day, y);
            for (int i = 0; i <  employeeRepository.getEmployees().length; i++)
            {
                if (employeeRepository.getEmployees()[i] == null)
                {
                    employeeRepository.getEmployees()[i] = o;
                    o.setId(i);
                    try
                    {
                        PreparedStatement stmt = connection.prepareStatement("insert into Employees values("+o.getId()+","+o.getFirstName()+","+o.getLastName()+","+o.getSalary()+","+o.getYear()+","+o.getMonth()+","+o.getDay()+",null,"+o.getXP()+",'IT')");
                        stmt.execute();
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
            System.out.println("Spatiu insuficient");
        }
        else
        {
            System.out.println("Introduceti studiile angajatului");
            String edu = s.nextLine();
            Librarian o = new Librarian(first, last, salary, year, month, day, edu);
            for (int i = 0; i <  employeeRepository.getEmployees().length; i++)
            {
                if (employeeRepository.getEmployees()[i] == null)
                {
                    employeeRepository.getEmployees()[i] = o;
                    o.setId(i);
                    try
                    {
                        PreparedStatement stmt = connection.prepareStatement("insert into Employees values("+o.getId()+","+o.getFirstName()+","+o.getLastName()+","+o.getSalary()+","+o.getYear()+","+o.getMonth()+","+o.getDay()+","+o.getEducationLevel()+",null,'Bibliotecar')");
                        stmt.execute();
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
            System.out.println("Spatiu insuficient");
        }
    }
    public void printEmployee()
    {
        audit.write("printEmployee");
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
        audit.write("printEmployees");
        employeeRepository.readAll();
    }
    public void deleteEmployee(int id)
    {
        audit.write("deleteEmployee");
        for (int i = 0; i <  employeeRepository.getEmployees().length; i++)
        {
            if ( employeeRepository.getEmployees()[i] != null)
            {
                if ( employeeRepository.getEmployees()[i].getId() == id)
                {
                    employeeRepository.getEmployees()[i] = null;
                    try
                    {
                        PreparedStatement stmt = connection.prepareStatement("Delete from Employees where id ="+id);
                        stmt.execute();
                    } catch (SQLException e)
                    {
                        throw new RuntimeException(e);
                    }
                    return;
                }
            }
        }
        System.out.println("Nu a fost gasit un angajat cu acest id");
    }
    public void borrowBook()
    {
        audit.write("borrowBook");
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
                try
                {
                    PreparedStatement stmt = connection.prepareStatement("Update books set count ="+bookRepository.getBooks().get(bookId).getCount()+" where id ="+bookId);
                    stmt.execute();
                } catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
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
        audit.write("returnBook");
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
                try
                {
                    PreparedStatement stmt = connection.prepareStatement("Update books set count ="+bookRepository.getBooks().get(b.getBookId()).getCount()+" where id ="+bookId);
                    stmt.execute();
                } catch (SQLException e)
                {
                    throw new RuntimeException(e);
                }
                return;
            }
            borrowedBooks.remove(b);
        }
        System.out.println("Nu exista niciun imprumut cu datele specificate");
    }
    public void showHistory()
    {
        audit.write("showHistory");
        for (Borrowed b: borrowedBooks)
        {
            System.out.println(b);
        }
    }
}

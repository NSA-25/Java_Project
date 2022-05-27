package main;

import service.CSVService;
import service.Service;
import service.DBService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            DBService db = DBService.getInstance();
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        Service s = new Service();
        try
        {
            s.startup();
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
//        s.createClient();
//        s.createClient();
//        s.createClient();
//        s.printClient(1);
//        s.printClients();
//        s.createBook();
//        s.createBook();
//        s.createBook();
//        s.printBook("test");
//        s.printBooks();
//        s.createEmployee();
//        s.createEmployee();
//        s.createEmployee();
//        s.printEmployee();
//        s.printEmployees();

        while (true)
        {
            Scanner scan = new Scanner(System.in);
            System.out.println("0 Inchide");
            System.out.println("1 Adauga client");
            System.out.println("2 Afiseaza client dupa cnp");
            System.out.println("3 Afiseaza toti clientii");
            System.out.println("4 Adauga carte");
            System.out.println("5 Afiseaza carte");
            System.out.println("6 Afiseaza toate cartile");
            System.out.println("7 Adauga autor");
            System.out.println("8 Afiseaza autor");
            System.out.println("9 Afiseaza toti autorii");
            System.out.println("10 Adauga angajat");
            System.out.println("11 Afiseaza angajat");
            System.out.println("12 Afiseaza toti angajatii");
            System.out.println("13 Efectueaza imprumut");
            System.out.println("14 Returneaza carte");
            System.out.println("15 Afiseaza istoric imprumuturi");
            System.out.println("16 Sterge carte");
            System.out.println("17 Sterge angajat");
            System.out.println("18 Sterge client dupa cnp");
            System.out.println("19 Sterge autor");
            System.out.println("20 Afiseaza client dupa id");
            System.out.println("21 Sterge client dupa id");
            System.out.println("22 Actualizare client");
            System.out.println("23 Actualizare carte");
            System.out.println("24 Actualizare autor");
            int x  = scan.nextInt();
            scan.nextLine();
            switch (x)
            {
                case 0:
//                    s.save();
                    return;
                case 1:
                    s.createClient();
                    break;
                case 2:
                    System.out.println("introduceti cnp-ul clientului");
                    String id = scan.nextLine();
                    s.printClientByCnp(id);
                    break;
                case 3:
                    s.printClients();
                    break;
                case 4:
                    s.createBook();
                    break;
                case 5:
                    System.out.println("introduceti id-ul cartii");
                    String id2 = scan.nextLine();
                    s.printBook(id2);
                    break;
                case 6:
                    s.printBooks();
                    break;
                case 7:
                    s.createAuthor();
                    break;
                case 8:
                    int id1 = scan.nextInt();
                    scan.nextLine();
                    s.printAuthor(id1);
                    break;
                case 9:
                    s.printAuthors();
                    break;
                case 10:
                    s.createEmployee();
                    break;
                case 11:
                    s.printEmployee();
                    break;
                case 12:
                    s.printEmployees();
                    break;
                case 13:
                    s.borrowBook();
                    break;
                case 14:
                    s.returnBook();
                    break;
                case 15:
                    s.showHistory();
                    break;
                case 16:
                    System.out.println("introduceti id-ul cartii:");
                    String id3 = scan.nextLine();
                    s.deleteBook(id3);
                    break;
                case 17:
                    System.out.println("introduceti id-ul angajatului");
                    int id5 = scan.nextInt();
                    scan.nextLine();
                    s.deleteEmployee(id5);
                    break;
                case 18:
                    System.out.println("introduceti cnp-ul clientului:");
                    String id4 = scan.nextLine();
                    s.deleteClientByCnp(id4);
                    break;
                case 19:
                    System.out.println("introduceti id-ul autorului:");
                    int id7 = scan.nextInt();
                    scan.nextLine();
                    s.deleteAuthor(id7);
                    break;
                case 20:
                    System.out.println("introduceti id-ul clientului:");
                    int id8 = scan.nextInt();
                    scan.nextLine();
                    s.printClientById(id8);
                    break;
                case 21:
                    System.out.println("introduceti id-ul clientului:");
                    int id9 = scan.nextInt();
                    scan.nextLine();
                    s.deleteClientById(id9);
                    break;
                case 22:
                    System.out.println("introduceti id-ul clientului:");
                    int id10 = scan.nextInt();
                    scan.nextLine();
                    s.updateClientById(id10);
                    break;
                case 23:
                    System.out.println("introduceti id-ul cartii:");
                    String id11 = scan.nextLine();;
                    s.updateBook(id11);
                    break;
                case 24:
                    System.out.println("introduceti id-ul autorului:");
                    int id12 = scan.nextInt();
                    scan.nextLine();
                    s.updateAuthor(id12);
                    break;
                default:
                    System.out.println("Input invalid");
                    break;
            }
        }
    }
}

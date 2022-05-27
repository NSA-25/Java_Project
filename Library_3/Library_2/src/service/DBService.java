package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBService {

    private Connection conn;
    private final String url = "jdbc:sqlite:DB.sqlite";
//    private String user =;
//    private String pass =;
    private static DBService instance = null;
    public DBService() throws SQLException, ClassNotFoundException
    {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);
    }

    public void close() throws SQLException
    {
        conn.close();
    }

    public static DBService getInstance() throws SQLException, ClassNotFoundException
    {
        if (instance == null) {instance = new DBService();}
        return instance;
    }

    public PreparedStatement prepareStatement(String stmt) throws SQLException
    {
        if (conn.isClosed())
        {
            conn = DriverManager.getConnection(url); // reconnect
        }
        return conn.prepareStatement(stmt);
    }
    public Connection getConnection() {return conn;}

}
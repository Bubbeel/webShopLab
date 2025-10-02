package com.kth.DB;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/db", loadOnStartup = 1)
public class DBManager extends HttpServlet {
    private static DBManager instance = null;
    private Connection con = null;
    private boolean yesno = false;

    public static DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }
        return instance;
    }

    public DBManager(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/webshop?user=root&password=root");
            yesno = true;
        } catch (Exception e) {
            yesno = false;
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return getInstance().con;
    }
}

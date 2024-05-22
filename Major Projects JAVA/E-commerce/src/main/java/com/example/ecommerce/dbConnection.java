package com.example.ecommerce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbConnection {
    private final String url = "jdbc:mysql://localhost:3306/ecommerce";

    private final String userName = "root";

    private final String password = "qwerty123";

    public Statement getStatement() {
        try {
            Connection connection = DriverManager.getConnection(url , userName , password);
            return connection.createStatement();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getQueryTable(String query) {
        try{
            Statement statement = getStatement();
            return statement.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int updateQuery(String query){
        try{
            Statement statement = getStatement();
            return statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
        dbConnection db = new dbConnection();
        ResultSet rs = db.getQueryTable("select * from customer");
        if(rs != null){
            System.out.println("Connection successful");
        }
        else{
            System.out.println("Connection failed");
        }
    }
}

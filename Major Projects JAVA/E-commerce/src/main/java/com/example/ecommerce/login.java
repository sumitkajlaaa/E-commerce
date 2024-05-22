package com.example.ecommerce;

import java.sql.ResultSet;

public class login {

    public Customer customerLogin(String username, String password) {
        String query = "SELECT * FROM customer WHERE email = '" + username + "' AND password = '"+password+"'";
        dbConnection db = new dbConnection();
        try{
            ResultSet rs = db.getQueryTable(query);
            if(rs.next()) {
                return new Customer(rs.getInt("id") , rs.getString("name") ,
                        rs.getString("email"), rs.getString("mobile"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        login login = new login();
        Customer customer = login.customerLogin("admin", "admin");
        System.out.println("Welcome " + customer.getName());
    }
}

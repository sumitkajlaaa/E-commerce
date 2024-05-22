package com.example.ecommerce;

import java.sql.ResultSet;

public class CreateAccount {

    static dbConnection db = new dbConnection();
    public static boolean createAccountInDB(String name , String email , String mobile , String password){
        String createAccountQuery = "INSERT into customer(name,email,mobile,password) VALUES ('"+name+"','"+email+"','"+mobile+"','"+password+"')";

        try{
            return db.updateQuery(createAccountQuery) != 0;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkEmailDuplicacy(String email){
        String checkEmailQuery = "SELECT * FROM customer where email = '"+email+"'";
        try{
            ResultSet rs = db.getQueryTable(checkEmailQuery);
            if(rs.next()){
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public static boolean checkPhoneDuplicacy(String phone){
        String checkPhoneQuery = "SELECT * FROM customer where mobile = '"+phone+"'";
        try{
            ResultSet rs = db.getQueryTable(checkPhoneQuery);
            if(rs.next()){
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }
}

package com.example.ecommerce;

import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class Order {

    public static boolean placeOrder(Customer customer , Product product){
        String groupOrderIdQuery = "SELECT max(group_order_id)+1 as groupId  FROM orders";
        dbConnection db = new dbConnection();
        try{
            ResultSet rs = db.getQueryTable(groupOrderIdQuery);
            if(rs.next() == true){
                String placeOrderQuery = "INSERT into orders(group_order_id ,customer_id ,product_id) VALUES ("+rs.getInt("groupId")  +","+customer.getId()+","+product.getId()+")";
                return db.updateQuery(placeOrderQuery) != 0;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static int placeMultipleOrder(Customer customer , ObservableList<Product> cartList){
        String groupOrderIdQuery = "SELECT max(group_order_id)+1 as groupId  FROM orders";
        dbConnection db = new dbConnection();
        int count = 0;

        try{
            ResultSet rs = db.getQueryTable(groupOrderIdQuery);
            if(rs.next() == true){
                for(Product product : cartList){

                    String placeOrderQuery = "INSERT into orders(group_order_id ,customer_id ,product_id) VALUES ("+rs.getInt("groupId")  +","+customer.getId()+","+product.getId()+")";
                    db.updateQuery(placeOrderQuery);
                    count++;
                }
                return count;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }



}

package com.example.ecommerce;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDateTime;

public class MyOrders {
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleIntegerProperty group_order_id;
    private LocalDateTime order_time;
    private SimpleStringProperty order_status;

    public MyOrders(String name, int group_order_id, double price, LocalDateTime order_time, String order_status) {
        this.name = new SimpleStringProperty(name);
        this.group_order_id = new SimpleIntegerProperty(group_order_id);
        this.price = new SimpleDoubleProperty(price);
        this.order_time = order_time;
        this.order_status = new SimpleStringProperty(order_status);
    }

    public static ObservableList<MyOrders> fetchMyOrders(String query) {
        ObservableList<MyOrders> data = FXCollections.observableArrayList();
        dbConnection db = new dbConnection();

        try{
            ResultSet rs = db.getQueryTable(query);
            while(rs.next()){
                MyOrders myOrder = new MyOrders(rs.getString("name") , rs.getInt("group_order_id"),rs.getDouble("price") ,
                                        rs.getTimestamp("order_date").toLocalDateTime(), rs.getString("order_status"));
                data.add(myOrder);
            }
            return data;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public String getName() {
        return name.get();
    }

    public double getPrice() {
        return price.get();
    }

    public int getGroup_order_id() {
        return group_order_id.get();
    }

    public LocalDateTime getOrder_time() {
        return order_time;
    }

    public String getOrder_status() {
        return order_status.get();
    }
}

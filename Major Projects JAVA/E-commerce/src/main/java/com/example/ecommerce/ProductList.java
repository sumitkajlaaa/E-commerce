package com.example.ecommerce;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

public class ProductList {
    private TableView<Product> productTable;
    private VBox vbox;

    private TableView<Product> cartTable;
    private VBox cartBox;

    public VBox createCartTable(ObservableList<Product> data) {
        cartTable = new TableView<>();
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        cartTable.setItems(data);
        cartTable.getColumns().addAll(id, name, price);
        cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        cartTable.setPrefHeight(390);

        cartBox = new VBox();
        cartBox.setPadding(new Insets(10));
        cartBox.getChildren().addAll(cartTable);

        return cartBox;
    }

    public VBox createTable(ObservableList<Product> data){
        productTable = new TableView();
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price = new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        productTable.setItems(data);
        productTable.getColumns().addAll(id, name, price);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productTable.setPrefHeight(390);

        vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(productTable);

        return vbox;

    }


    public VBox getAllProducts(int page){
        ObservableList<Product> data = Product.getALlProducts(page);
        if(productTable != null){
            productTable.setItems(data);
            return vbox;
        }
        return createTable(data);
    }

    public VBox getSearchResults(String searchQuery){
        ObservableList<Product> data = Product.fetchProductDataFromDB(searchQuery);
        productTable.setItems(data);
        return vbox;
    }

    public Product getSelectedProduct(){
        return productTable.getSelectionModel().getSelectedItem();
    }

    public VBox getProductsInCart(ObservableList<Product> data){
        return createCartTable(data);
    }

    TableView<MyOrders> customerOrderList;
    VBox customerOrderBox;

    public VBox createMyOrdersTable(ObservableList<MyOrders> data) {
        customerOrderList = new TableView<>();

        TableColumn<MyOrders, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MyOrders, Integer> idColumn = new TableColumn<>("Group Order ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("group_order_id"));

        TableColumn<MyOrders, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<MyOrders, LocalDateTime> orderDateColumn = new TableColumn<>("Order Date");
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("order_time"));

        TableColumn<MyOrders, String> orderStatusColumn = new TableColumn<>("Order Status");
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>("order_status"));

        customerOrderList.setItems(data);
        customerOrderList.getColumns().addAll(nameColumn, idColumn, priceColumn, orderDateColumn, orderStatusColumn);
        customerOrderList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        customerOrderList.setPrefHeight(390);

        customerOrderBox = new VBox();
        customerOrderBox.setPadding(new Insets(10));
        customerOrderBox.getChildren().addAll(customerOrderList);

        return customerOrderBox;
    }

    public VBox getMyOrders(int id){// int id = customer.getId();
        String query = "SELECT p.name as name , o.group_order_id as group_order_id , p.price as price , o.order_date as order_date , o.order_status as order_status \n" +
                "FROM orders o INNER JOIN products p ON o.product_id = p.id \n" +
                "WHERE o.customer_id = "+id+"\n" +
                "ORDER BY o.order_date DESC";
        ObservableList<MyOrders> data = MyOrders.fetchMyOrders(query);

        return createMyOrdersTable(data);
    }

    public static void main(String[] args) {
        ProductList p1 = new ProductList();
        System.out.println(p1.getMyOrders(3));
    }


}

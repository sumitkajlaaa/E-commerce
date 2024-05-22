package com.example.ecommerce;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Objects;

public class UserInterface {

    GridPane loginPage;
    GridPane createAccountPage;

    ObservableList<Product> productsInCart = FXCollections.observableArrayList();


    HBox headerBar;

    Customer loggedInCustomer;

    Label welcomeLabel;

    Button signInButton;

    VBox body;

    ProductList productList = new ProductList();
    VBox productPage;

    HBox footerBar;

    HBox paginationBar;

    int page = 1;

    int maxPage = 4;

    HBox loginPageBar;

    VBox cartBar;

    VBox customerOrderBar;

    Text messageLebel;

    // global buttons
    Button backBtn;
    Button nextBtn;
    Button prevBtn;
    Button signOutBtn;
    Button placeOrderBtn;
    Button cartBtn;
    Button homeBtn;
    Button myOrdersBtn;



   BorderPane createContent(){
        BorderPane root = new BorderPane();

        root.setPrefSize(700,700);
        root.setTop(headerBar);
       root.setBottom(footerBar);
       // root.getChildren().add(loginPage);
       // root.setCenter(loginPage);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
        root.setCenter(body);
        productPage = productList.getAllProducts(page);
        //root.setCenter(productPage);
        body.getChildren().addAll(productPage, paginationBar);
        root.setStyle("-fx-background-color:#ffffff");

        return root;
    }

    public UserInterface(){
        createLoginPage();
        createHeaderBar();
        createFooterBar();
        createPagination();
    }



    private void createLoginPage(){
        Text username = new Text("Username");
        Text password = new Text("Password");
        messageLebel = new Text("Welcome");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Type your user name here");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Type your password here");

        Button loginButton = new Button("Login");
        Button signUpBtn = new Button("Sign Up");

        loginPage = new GridPane();
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(10);
        loginPage.setVgap(10);
        loginPage.add(username,0,0);
        loginPage.add(usernameField,1,0);
        loginPage.add(password,0,1);
        loginPage.add(passwordField,1,1);

        loginPageBar = new HBox();
        loginPage.add(loginPageBar , 1, 2);
        loginPageBar.setSpacing(10);
        loginPageBar.getChildren().addAll(loginButton,signUpBtn);


        loginPage.add(messageLebel , 1 , 3);

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = usernameField.getText();
                String pass = passwordField.getText();

                login login = new login();
                loggedInCustomer = login.customerLogin(name , pass);

                if(loggedInCustomer != null){
                   // messageLebel.setText("You have successfully logged in " + loggedInCustomer.getName() + "!");
                    welcomeLabel.setText("Welcome-" + loggedInCustomer.getName() + "!");
                    headerBar.getChildren().addAll(welcomeLabel ,myOrdersBtn, signOutBtn);
                    body.getChildren().clear();
                    body.getChildren().addAll(productPage , paginationBar);
                    usernameField.clear();
                    passwordField.clear();
                    footerBar.setVisible(true);
                }else{
                    messageLebel.setText("Invalid Username or Password");
                }
            }
        });

        signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                createSignUpPage();
                VBox temp = body;
                body.getChildren().clear();
                body.getChildren().addAll(createAccountPage);
                headerBar.getChildren().add(signInButton);

            }
        });
    }

    private void createSignUpPage(){
       Text name = new Text("Full name");
       Text email = new Text("Email");
       Text phone = new Text("Phone/mobile");
       Text password = new Text("Password");
       Text confirmPassword = new Text("Confirm password");

       TextField nameField = new TextField();
       nameField.setPromptText("Enter your full name");
       TextField emailField = new TextField();
       emailField.setPromptText("Enter your email");
       TextField phoneField = new TextField();
       phoneField.setPromptText("Enter your phone/mobile number");
       PasswordField passwordField = new PasswordField();
       passwordField.setPromptText("Enter your password");
       PasswordField confrimPasswordField = new PasswordField();
       confrimPasswordField.setPromptText("Enter your password again");

       Button createAccountBtn = new Button("Create account");

        createAccountPage = new GridPane();
        createAccountPage.setAlignment(Pos.CENTER);
        createAccountPage.setHgap(10);
        createAccountPage.setVgap(10);
        createAccountPage.add(name , 0 , 0);
        createAccountPage.add(nameField , 1, 0);
        createAccountPage.add(email , 0, 1);
        createAccountPage.add(emailField , 1 , 1);
        createAccountPage.add(phone , 0 , 2);
        createAccountPage.add(phoneField,1,2);
        createAccountPage.add(password , 0 , 3);
        createAccountPage.add(passwordField,1, 3);
        createAccountPage.add(confirmPassword , 0 , 4);
        createAccountPage.add(confrimPasswordField , 1, 4);
        createAccountPage.add(createAccountBtn, 1, 5);

       createAccountBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               String name = nameField.getText();
               String email = emailField.getText();
               String phone = phoneField.getText();
               String password = passwordField.getText();
               String confirmPass = confrimPasswordField.getText();

               if(name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){
                   showDialog("please dont leave any credential empty/null");
                   createSignUpPage();
                   return;
               }
               if(!Objects.equals(passwordField.getText(), confrimPasswordField.getText())){
                   showDialog("password does not match. Enter again correctly");
                   createSignUpPage();
                   return;
               }

               boolean checkEmail = CreateAccount.checkEmailDuplicacy(email);
               if(checkEmail == false){
                   showDialog("Email already exist! Please enter different email");
                   createSignUpPage();
                   return;
               }

               boolean checkPhone = CreateAccount.checkPhoneDuplicacy(phone);
               if(checkPhone == false){
                   showDialog("Phone number already exist! Please enter different number");
                   createSignUpPage();
                   return;
               }

               boolean status = CreateAccount.createAccountInDB(name,email,phone,password);
               if(status == true){
                   showDialog("Account created successully");
               } else{
                   showDialog("Account creation failed");
               }

           }
       });


    }

    private void createHeaderBar(){
        homeBtn = new Button();
        Image homeImage = new Image("homelogo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(homeImage);
        imageView.setFitHeight(25);
        imageView.setFitWidth(25);
        homeBtn.setGraphic(imageView);

       TextField searchField = new TextField();
       searchField.setPromptText("Search here");
       searchField.setPrefWidth(280);

       Button searchButton = new Button("Search");
       signInButton = new Button("Sign in");
       welcomeLabel = new Label();


       backBtn = new Button("Back");
       signOutBtn = new Button("Sign out");
       cartBtn = new Button("Cart");
       placeOrderBtn = new Button("Place order");
       myOrdersBtn = new Button("My orders");


        headerBar = new HBox();
        headerBar.setAlignment(Pos.CENTER);
        headerBar.setPadding(new Insets(20));
        //headerBar.setStyle("-fx-background-color:grey");
        headerBar.setSpacing(10);
        headerBar.getChildren().addAll(homeBtn ,searchField,searchButton , signInButton , cartBtn);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signInButton);
                footerBar.setVisible(false);
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(searchField.getText() != null){
                    String searchQuery = "SELECT * FROM products where name like '%" + searchField.getText() +"%'";
                    productPage =  productList.getSearchResults(searchQuery);
                    paginationBar.getChildren().clear();
                    paginationBar.getChildren().add(backBtn);
                }
            }
        });

        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                productPage = productList.getAllProducts(page);
                paginationBar.getChildren().clear();
                updatePaginationButtons(nextBtn,prevBtn);
            }
        });

        signOutBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                headerBar.getChildren().removeAll(welcomeLabel,signOutBtn,myOrdersBtn);
                headerBar.getChildren().add(signInButton);
                messageLebel.setText("Welcome");
                loggedInCustomer = null;
            }
        });

        cartBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                cartBar = productList.createCartTable(productsInCart);
                cartBar.setSpacing(10);
                placeOrderBtn.setVisible(true);
                cartBar.getChildren().add(placeOrderBtn);
                cartBar.setAlignment(Pos.CENTER);
                body.getChildren().add(cartBar);
                footerBar.setVisible(false);
            }
        });

        placeOrderBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(productsInCart.isEmpty()){
                    showDialog("add some products in cart to place order");
                    return;
                }
                if(loggedInCustomer == null){
                    showDialog("Please login first");
                    return;
                }

                int order_count = Order.placeMultipleOrder(loggedInCustomer , productsInCart);

                if(order_count != -1){
                    showDialog("Order for "+order_count+" items placed successfully!");
                }
                else{
                    showDialog("Order failed!");
                }
            }
        });

        homeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                page = 1;
                body.getChildren().clear();
                productPage = productList.getAllProducts(page);
                body.getChildren().addAll(productPage,paginationBar);
                updatePaginationButtons(nextBtn,prevBtn);
                footerBar.setVisible(true);
            }
        });

        myOrdersBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                int id = loggedInCustomer.getId();
                customerOrderBar = productList.getMyOrders(id);
                customerOrderBar.setSpacing(10);
                customerOrderBar.getChildren().add(placeOrderBtn);
                customerOrderBar.setAlignment(Pos.CENTER);
                body.getChildren().add(customerOrderBar);
                updatePaginationButtons(nextBtn,prevBtn);
                footerBar.setVisible(false);
                placeOrderBtn.setVisible(false);
            }
        });

    }

    public void createFooterBar(){
       footerBar = new HBox();


       Button buyNowBtn = new Button("Buy now");
       Button addToCartBtn = new Button("Add to cart");

       footerBar.getChildren().addAll(buyNowBtn,addToCartBtn);
       footerBar.setPadding(new Insets(10));
       footerBar.setSpacing(10);
       footerBar.setAlignment(Pos.CENTER);

       buyNowBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               Product product = productList.getSelectedProduct();
               if(product == null){
                    showDialog("please select a product first to place order");
                    return;
               }
               if(loggedInCustomer == null){
                   showDialog("please sign in first to place order");
                   return;
               }

               boolean status = Order.placeOrder(loggedInCustomer , product);
               if(status == true){
                   showDialog("Order placed successfully");
               }
               else{
                   showDialog("Order failed!");
               }

           }
       });
       addToCartBtn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               Product product = productList.getSelectedProduct();
               if(product == null){
                   showDialog("please select a product first to add it to cart");
                   return;
               }
               if(loggedInCustomer == null){
                   showDialog("please sign in first to place order");
                   return;
               }
               productsInCart.add(product);
               showDialog("Item added to cart successfully");
           }
       });

    }

    private void showDialog(String dialog){
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setTitle("Message");
       alert.setHeaderText(null);
       alert.setContentText(dialog);
       alert.showAndWait();
    }

    public void createPagination(){
       nextBtn = new Button("Next");
       prevBtn = new Button("Prev");

       paginationBar = new HBox();
       paginationBar.getChildren().add(nextBtn);
       paginationBar.setPadding(new Insets(20));
       paginationBar.setSpacing(10);
       paginationBar.setAlignment(Pos.BASELINE_RIGHT);

        nextBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (page < maxPage) {
                    page++;
                    productPage = productList.getAllProducts(page);
                }
                updatePaginationButtons(nextBtn,prevBtn);
            }
        });

        prevBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (page > 1) {
                    page--;
                    productPage = productList.getAllProducts(page);
                }
                updatePaginationButtons(nextBtn,prevBtn);
            }
        });

    }

    private void updatePaginationButtons(Button nextBtn , Button prevBtn) {
        if (page == 1 && maxPage > 1) {
            paginationBar.getChildren().clear();
            paginationBar.getChildren().add(nextBtn);
        } else if (page > 1 && page < maxPage) {
            paginationBar.getChildren().clear();
            paginationBar.getChildren().addAll(prevBtn, nextBtn);
        } else if (page == maxPage && maxPage > 1) {
            paginationBar.getChildren().clear();
            paginationBar.getChildren().add(prevBtn);
        }
    }

}


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ClothingStoreApp extends Application {

    private TableView<Product> productTable;
    private ObservableList<Product> productList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Setup initial login scene
        Scene loginScene = createLoginScene(primaryStage);

        // Set initial scene to login
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Clothing Store App");
        primaryStage.show();
    }

    private Scene createLoginScene(Stage primaryStage) {
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new javafx.geometry.Insets(20));

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Admin Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (usernameField.getText().equals("admin") && passwordField.getText().equals("admin")) {
                // Correct login, show admin panel
                Scene adminScene = createAdminScene(primaryStage);
                primaryStage.setScene(adminScene);
            } else {
                // Incorrect login
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password.");
                alert.showAndWait();
            }
        });

        loginLayout.getChildren().addAll(new Label("Admin Login"), usernameField, passwordField, loginButton);
        return new Scene(loginLayout, 300, 200);
    }

    private Scene createAdminScene(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Create the product table
        productTable = new TableView<>();
        productList = FXCollections.observableArrayList(Database.getAllProducts());
        productTable.setItems(productList);

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));

        TableColumn<Product, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));

        TableColumn<Product, String> sizeColumn = new TableColumn<>("Size");
        sizeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSize()));

        TableColumn<Product, String> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getColor()));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrice()).asObject());

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Stock");
        quantityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantityInStock()).asObject());

        productTable.getColumns().addAll(nameColumn, descriptionColumn, sizeColumn, colorColumn, priceColumn, quantityColumn);

        // Add buttons for CRUD operations
        Button addButton = new Button("Add Product");
        addButton.setOnAction(e -> addProduct());

        Button updateButton = new Button("Update Product");
        updateButton.setOnAction(e -> updateProduct());

        Button deleteButton = new Button("Delete Product");
        deleteButton.setOnAction(e -> deleteProduct());

        HBox buttonBar = new HBox(10, addButton, updateButton, deleteButton);

        VBox centerLayout = new VBox(10, productTable, buttonBar);
        root.setCenter(centerLayout);

        return new Scene(root, 800, 600);
    }

    // Add product to database and update table
    private void addProduct() {
        // Sample product, in real case you would use a form to get the input
        Product newProduct = new Product("New Product", "Description", "M", "Red", 29.99, 100);
        Database.addProduct(newProduct);
        productList.setAll(Database.getAllProducts());
    }

    // Update a product (for simplicity, we just update the name in this example)
    private void updateProduct() {
        if (productTable.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            Product updatedProduct = new Product(selectedProduct.getName() + " (Updated)", selectedProduct.getDescription(),
                    selectedProduct.getSize(), selectedProduct.getColor(), selectedProduct.getPrice(), selectedProduct.getQuantityInStock());
            Database.updateProduct(selectedProduct.getName(), updatedProduct);
            productList.setAll(Database.getAllProducts());
        }
    }

    // Delete a selected product
    private void deleteProduct() {
        if (productTable.getSelectionModel().getSelectedItem() != null) {
            Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
            Database.deleteProduct(selectedProduct.getName());
            productList.setAll(Database.getAllProducts());
        }
    }
}

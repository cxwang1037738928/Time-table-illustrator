package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.Parent;
import java.io.IOException;

public class Main extends Application {

   public static Stage window; // sets stage and respective scenes
   public static Scene scene1, scene2, scene3;
   public static TableView<Product> TimeTable;
   TextField ActivityInput, Time_Started, Time_Finished;


   public static void main(String[] args) {
       launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws IOException {

       window = primaryStage;

       window.setTitle("Final Project");

       //Sets column for Activities
       TableColumn<Product, String> ActivityColumn = new TableColumn<>("Activity");
       ActivityColumn.setMinWidth(200);
       ActivityColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));

       //Sets column for time started
       TableColumn<Product, Double> Time_Started_Column = new TableColumn<>("Time Started");
       Time_Started_Column.setMinWidth(100);
       Time_Started_Column.setCellValueFactory(new PropertyValueFactory<>("Time_Started"));

       //Sets column for time ended
       TableColumn<Product, String> Time_Finished_Column = new TableColumn<>("Time Finished");
       Time_Finished_Column.setMinWidth(100);
       Time_Finished_Column.setCellValueFactory(new PropertyValueFactory<>("Time_Finished"));

       //Allows for input for activity
       ActivityInput = new TextField();
       ActivityInput.setPromptText("Activity");
       ActivityInput.setMinWidth(100);

       //Allows for input for time started
       Time_Started = new TextField();
       Time_Started.setPromptText("Time started");

       //Allows for input for time ended
       Time_Finished = new TextField();
       Time_Finished.setPromptText("Time ended");

       //Button for adding and deleting events
       Button Inputbutton = new Button("Add");
       Inputbutton.setOnAction(e -> addButtonClicked());
       Button Removebutton = new Button("Delete");
       Removebutton.setOnAction(e -> deleteButtonClicked());

       //new row for the buttons and to input new events
       HBox hb = new HBox();
       hb.setPadding(new Insets(10,10,10,10));
       hb.setSpacing(10);
       hb.getChildren().addAll(ActivityInput, Time_Started, Time_Finished, Inputbutton, Removebutton);

       TimeTable = new TableView<>();
       TimeTable.setItems(getActivities());
       TimeTable.getColumns().addAll(ActivityColumn, Time_Started_Column, Time_Finished_Column);


       //Button 1
       Label label1 = new Label("Menu");
       Button button1 = new Button("Go to pie chart");
       button1.setOnAction(e -> window.setScene(scene2));
       Button button2 = new Button("Go to bar chart");
       button2.setOnAction(e -> window.setScene(scene3));

       //Layout 1 - children laid out in vertical column
       VBox layout1 = new VBox(); /* VBox layout1 = new VBox(20); */
       layout1.getChildren().addAll(label1, button1, button2, TimeTable, hb);
       scene1 = new Scene(layout1, 600, 600);

       Parent root2 = FXMLLoader.load(getClass().getResource("piechart.fxml"));
       scene2 = new Scene(root2, 600, 400);

       Parent root = FXMLLoader.load(getClass().getResource("barchart.fxml"));
       scene3 = new Scene(root, 600, 400);

       //Display scene 1 at first
       window.setScene(scene1);
       window.setTitle("Time organizer");
       window.show();
   }

   //Add button clicked
   public void addButtonClicked(){
       Product product = new Product();
       product.setActivity(ActivityInput.getText());
       product.setTime_Started(Time_Started.getText());
       product.setTime_Finished(Time_Finished.getText());
       TimeTable.getItems().add(product);
       ActivityInput.clear();
       Time_Started.clear();
       Time_Finished.clear();
   }

   //Delete button clicked
   public void deleteButtonClicked(){
       ObservableList<Product> productSelected, allProducts;
       allProducts = TimeTable.getItems();
       productSelected = TimeTable.getSelectionModel().getSelectedItems(); // deletes the entered values on spreadsheet
       productSelected.forEach(allProducts::remove);
   }

   //Get all of the products
   public ObservableList<Product> getActivities(){
       ObservableList<Product> products = FXCollections.observableArrayList();
       products.add(new Product("Morning routine", "8:00", "9:50"));
       products.add(new Product("Math homework", "10:00", "12:00"));
       products.add(new Product("Lunch", "12:00", "12:30"));
       products.add(new Product("Nap", "12:30", "13:00"));
       products.add(new Product("Chemistry homework", "13:00", "14:30"));
       return products;
   }
}

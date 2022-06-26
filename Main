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

package sample;

public class Product {

   private String activity;
   private String Time_Started;
   private String Time_Finished;

   public Product(){ // sets basic value of activity, time started, and time ended to black string
       this.activity = "";
       this.Time_Started = "";
       this.Time_Finished = "";
   }

   public Product(String activity, String StartT, String FinishT){
       this.activity = activity;
       this.Time_Started = StartT;
       this.Time_Finished = FinishT;
   }

   // returns values needed for bar and pie chart
   public String getActivity() {
       return activity;
   }

   public void setActivity(String activity) {
       this.activity = activity;
   }

   public String getTime_Started() {
       return Time_Started;
   }

   public void setTime_Started(String startT) {
       this.Time_Started = startT;
   }

   public String getTime_Finished() {
       return Time_Finished;
   }

   public void setTime_Finished(String FinishT) {
       this.Time_Finished = FinishT;
   }

}

package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
   @FXML
   private NumberAxis naMins;
   @FXML
   private CategoryAxis caTimes;
   @FXML
   private BarChart<String, Number> barChart;
   @FXML
   private PieChart pieChart;

   public Button gotoBar;
   public Button ReturnToMenu;

   public Button gotoPie;
   public Button ReturnToMenu2;

   public void handleBarButtonClick(){
       XYChart.Series<String, Number> series = new XYChart.Series<>();
       barChart.getData().clear();
       series.setName("Activity Time");

       ObservableList<Product> allProducts;
       allProducts = Main.TimeTable.getItems();

       for (Product pr:allProducts) {
           String begin[] = pr.getTime_Started().split(":");
           String end[] = pr.getTime_Finished().split(":");
           int b = Integer.parseInt(begin[0])*60 + Integer.parseInt(begin[1]);
           int e = Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]);

           int df = e - b;
           String name = pr.getActivity();
           series.getData().add(new XYChart.Data<>(name, df));
           System.out.print("{" + pr.getActivity() + " " + pr.getTime_Started() + " " + pr.getTime_Finished() + " df= "+ df +"} ");
           System.out.print("\r\n");
       }
       barChart.getData().add(series);
   }
   public void handleGoBackButton2Click(){
       Main.window.setScene(Main.scene1);
   }

   public void handlePieButtonClick(){
       ObservableList<Product> allProducts;
       allProducts = Main.TimeTable.getItems();
       ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
       for (Product pr:allProducts) {
           String begin[] = pr.getTime_Started().split(":");
           String end[] = pr.getTime_Finished().split(":");
           int b = Integer.parseInt(begin[0])*60 + Integer.parseInt(begin[1]);
           int e = Integer.parseInt(end[0])*60 + Integer.parseInt(end[1]);
           int df = e - b;
           String name = pr.getActivity();
           list.add(new PieChart.Data(name, df));
           System.out.print("{" + pr.getActivity() + " " + pr.getTime_Started() + " " + pr.getTime_Finished() + " df= "+ df +"} ");
           System.out.print("\r\n");
       }

       pieChart.setData(list);
   }
   public void handleGoBackButton3Click(){
       Main.window.setScene(Main.scene1);
   }

   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
   }
}

JavaFx part of the Code:

<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.chart.PieChart?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.VBox?>

<AnchorPane prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="sample.Controller">
  <children>
     <VBox layoutY="-1.0" prefHeight="403.0" prefWidth="639.0">
        <children>
           <Label text="Pie Chart" />
           <Button fx:id="gotoPie" mnemonicParsing="false" onAction="#handlePieButtonClick" text="Pie Display" />
           <PieChart fx:id="pieChart" prefHeight="332.0" prefWidth="639.0" />
           <Button fx:id="ReturnToMenu2" mnemonicParsing="false" onAction="#handleGoBackButton3Click" text="Go Back" />
        </children>
     </VBox>
  </children>
</AnchorPane>


<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.scene.chart.BarChart?>
<?import javafx.scene.chart.CategoryAxis?>
<?import javafx.scene.chart.NumberAxis?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.VBox?>

<VBox prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="sample.Controller">

  <Button fx:id="gotoBar" layoutX="10.0" layoutY="27.0" onAction="#handleBarButtonClick" text="BarDisplay" />
  <BarChart fx:id="barChart" title="Time Satistics">
    <xAxis>
      <CategoryAxis fx:id="caTimes" label="Times" side="BOTTOM" />
    </xAxis>
    <yAxis>
      <NumberAxis fx:id="naMins" label="Mins" side="LEFT" />
    </yAxis>
  </BarChart>
   <Button fx:id="ReturnToMenu" onAction="#handleGoBackButton2Click" text="Go Back Button 2" />

</VBox>

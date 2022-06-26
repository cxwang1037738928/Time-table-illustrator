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

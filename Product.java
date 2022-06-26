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

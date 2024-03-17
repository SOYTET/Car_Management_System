<<<<<<< HEAD
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class Car_read {
//  public static void main(String[] args) {
//    try {
//      JSONArray jsonArray = readJsonArrayFromFile("Car_DataSet.json");
//      JSONObject obj=new JSONObject();
//      ArrayList<ArrayList<String>> arraylist=new ArrayList<ArrayList<String>>();
//      for (int i =0;i<jsonArray.size();i++){
//        arraylist.add()
//
//      }
//    } catch (IOException | ParseException e) {
//      e.printStackTrace();
//    }
//  }
//
//  public static JSONArray readJsonArrayFromFile(String fileName) throws IOException, ParseException {
//    JSONParser parser = new JSONParser();
//    Object obj = parser.parse(new FileReader(fileName));
//    JSONObject jsonObj = (JSONObject) obj;
//
//    JSONArray jsonArray = new JSONArray();
//    for (Object key : jsonObj.keySet()) {
//      String diagramKey = (String) key;
//      jsonArray.add(jsonObj.get(diagramKey));
//    }
//    return jsonArray;
//  }
//}
=======
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.JSONArray;  
import org.json.JSONObject;  

public class Car_read {
  public static void main(String[] args) {
    try {
      JSONArray jsonArray = readJsonArrayFromFile("Car_DataSet.json");

      System.out.printf("%-5s %-10s %-10s %-10s %-10s %-20s %-10s\n", "ID", "Make", "Model", "Year", "Mileage",
          "maintenace_stat", "Price");

      for (int i = 0; i <= jsonArray.size(); i++) {
        System.out.println(jsonArray.get(i));
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  public static JSONArray readJsonArrayFromFile(String fileName) throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader(fileName));
    JSONObject jsonObj = (JSONObject) obj;

    JSONArray jsonArray = new JSONArray();
    for (Object key : jsonObj.keySet()) {
      String diagramKey = (String) key;
      jsonArray.add(jsonObj.get(diagramKey));
    }
    return jsonArray;
  }
}
>>>>>>> 26e7f5cafa65e1b790677508615e8b303a1b61b5
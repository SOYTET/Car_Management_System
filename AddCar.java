import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AddCar {
    static final String _filepath = "Car_DataStorage.json";
    private int id;
    private String Manufacture;
    private String model;
    private int year;
    private int units;
    private String Class;
    private int price;

    public AddCar(int id, String manufacture, String model, int year, int units, String aClass, int price) {
        this.id = id;
        Manufacture = manufacture;
        this.model = model;
        this.year = year;
        this.units = units;
        Class = aClass;
        this.price = price;
    }

    @SuppressWarnings("unchecked")
    public void addCar() {
        // create a list to hold our car object
        ArrayList<AddCar> CarList = new ArrayList<AddCar>();
        // Create AddCar objects with car details
        AddCar car1 = new AddCar(1, "Toyota", "Camry", 2020, 25000, "Good", 25000);
        AddCar car2 = new AddCar(2, "Honda", "Civic", 2018, 30000, "Fair", 20000);
        AddCar car3 = new AddCar(3, "Ford", "Escape", 2022, 15000, "Excellent", 30000);
        AddCar car4 = new AddCar(4, "Tesla", "Y", 2023, 330, "Good", 54130);
        AddCar car5 = new AddCar(5, "Toyota", "Prius", 2024, 56000, "Good", 93800);
        AddCar car6 = new AddCar(6, "Byd", "Seal U", 2024, 500, "Good", 45000);
        AddCar car7 = new AddCar(7, "Lamborghini", "Aventador", 2022, 16000, "Excellent", 507353);
        AddCar car8 = new AddCar(8, "BMW", "X1 SUV", 2024, 20000, "Excellent", 40500);
        AddCar car9 = new AddCar(9, "Tesla", "Cyber Truck", 2023, 550000, "Excellent", 60990);
        AddCar car10 = new AddCar(10, "Lexus", "LX 570", 2022, 10000, "Good", 88245);

        // Add car objects to the list
        CarList.add(car1);
        CarList.add(car2);
        CarList.add(car3);
        CarList.add(car4);
        CarList.add(car5);
        CarList.add(car6);
        CarList.add(car7);
        CarList.add(car8);
        CarList.add(car9);
        CarList.add(car10);
        // Create a JSON array to each car obj
        JSONArray jsonArray = new JSONArray();
        for (AddCar car : CarList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ID", car.id);
            jsonObject.put("Manufacture", car.Manufacture);
            jsonObject.put("Model", car.model);
            jsonObject.put("Year", car.year);
            jsonObject.put("Units", car.units);
            jsonObject.put("Class", car.Class);
            jsonObject.put("Price", car.price);
            jsonArray.add(jsonObject);
        }

        // create json object to store jsonArray
        JSONObject carsObject = new JSONObject();
        carsObject.put("Cars", jsonArray);

        // write json object to a json file
        try (FileWriter file = new FileWriter(_filepath)) {
            file.write(carsObject.toJSONString());
            System.out.println("Successfully wrote JSON object to file.");
        } catch (IOException e) {
            System.out.println(e.getMessage().toString());
        }

    }

    @SuppressWarnings("unchecked")
    public void CreateNewCar() {
        Scanner scanner = new Scanner(System.in);
        // this lets user input
        System.out.println("Enter Car ID:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the manufacturer of the car:");
        String manufacture = scanner.nextLine();

        System.out.println("Enter the model of the car:");
        String model = scanner.nextLine();

        System.out.println("Enter the year of the car:");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the number of units of the car:");
        int units = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter the class of the car:");
        String carClass = scanner.nextLine();

        System.out.println("Enter the price of the car:");
        int price = scanner.nextInt();

        AddCar newCar = new AddCar(id, manufacture, model, year, units, carClass, price);

        try {
            // Read existing JSON data
            JSONParser jsonParser = new JSONParser();
            JSONArray carsArray;

            try (FileReader reader = new FileReader(_filepath)) {
                // Parse the JSON file
                Object obj = jsonParser.parse(reader);

                if (obj instanceof JSONArray) {
                    // If the root object is a JSONArray
                    carsArray = (JSONArray) obj;
                } else {
                    // If the root object is a JSONObject
                    JSONObject jsonObject = (JSONObject) obj;
                    carsArray = (JSONArray) jsonObject.get("Cars");
                }
            } catch (IOException | ParseException e) {
                // If the file doesn't exist or is empty, create a new JSON array
                carsArray = new JSONArray();
            }
            // Create JSON object for new car
            JSONObject newCarObject = new JSONObject();
            newCarObject.put("ID", newCar.id);
            newCarObject.put("Manufacture", newCar.Manufacture);
            newCarObject.put("Model", newCar.model);
            newCarObject.put("Year", newCar.year);
            newCarObject.put("Units", newCar.units);
            newCarObject.put("Class", newCar.Class);
            newCarObject.put("Price", newCar.price);

            // Add new car to the existing JSON array
            carsArray.add(newCarObject);
            JSONObject updatedJsonObject = new JSONObject();
            updatedJsonObject.put("Cars", carsArray);
            // // Update the JSON object with the new car array
            // carsObject.put("Cars", carArray);

            // Write updated JSON object back to file
            try (FileWriter fileWriter = new FileWriter(_filepath)) {
                fileWriter.write(updatedJsonObject.toJSONString());
                System.out.println("Successfully added a new car to the JSON file.");
                fileWriter.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage().toString());
        }
    }

}

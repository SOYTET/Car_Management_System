import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CarUpdate {
    @SuppressWarnings("unchecked")
    public static void modifyCar(String filePath, Scanner scanner) {
        try {
            // Read the JSON file
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));

            // Handle both single object and array scenarios
            JSONObject jsonObject;
            JSONArray carsArray;
            if (obj instanceof JSONObject) {
                jsonObject = (JSONObject) obj;
                carsArray = (JSONArray) jsonObject.get("Cars"); // Assuming "Cars" is the key for the car data array
            } else {
                carsArray = (JSONArray) obj; // Treat the entire object as an array
            }

            // Display available car IDs
            System.out.println("Available Car IDs:");
            for (Object carObject : carsArray) {
                JSONObject car = (JSONObject) carObject;
                System.out.println("ID: " + car.get("ID"));
            }

            System.out.print("Enter the ID of the car to modify: ");
            int carId = scanner.nextInt(); // Consume the newline character
            scanner.nextLine();

            // Find the car to modify
            JSONObject carToModify = null;
            for (Object carObject : carsArray) {
                JSONObject car = (JSONObject) carObject;
                if ((long) car.get("ID") == carId) {
                    carToModify = car;
                    break;
                }
            }

            // Modify the car if found
            if (carToModify != null) {
                System.out.println("Car found with ID: " + carId);
                System.out.print(
                        "Enter the field to modify (e.g., Model, Model_Year, Price, Manufacturer, Class, Units): ");
                String fieldToModify = scanner.nextLine();
                System.out.print("Enter the new value for the field: ");
                Object newValue;
                if (fieldToModify.equals("ID")) {
                    newValue = scanner.nextInt();
                    scanner.nextLine();
                } else if (fieldToModify.equals("Model_Year") || fieldToModify.equals("Units")) {
                    newValue = scanner.nextInt();
                    scanner.nextLine();
                } else if (fieldToModify.equals("Price")) {
                    newValue = scanner.nextDouble();
                    scanner.nextLine();
                } else {
                    newValue = scanner.nextLine();
                }

                // Update the specified field with the new value
                carToModify.put(fieldToModify, newValue);

                // Write the updated JSON data back to the file
                try (FileWriter fileWriter = new FileWriter(filePath)) {
                    if (carsArray.isEmpty()) {
                        // Handle empty array case (if the last car was deleted)
                        fileWriter.write("{}"); // Write an empty object
                    } else {
                        fileWriter.write(carsArray.toJSONString());
                    }
                    System.out.println("Car with ID " + carId + " modified successfully.");
                }
            } else {
                System.out.println("Car with ID " + carId + " not found.");
            }

        } catch (IOException | ParseException e) {
            System.err.println("Error modifying car: " + e.getMessage());
        }
    }
}

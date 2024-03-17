import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Scanner;

public class CarManagementSystem {

    public static void main(String[] args) {
        CarManagementSystem system = new CarManagementSystem();
        system.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Car Management System");
            System.out.println("1. Search cars by make");
            System.out.println("2. Read cars from JSON file");
            System.out.println("3. Add a car to JSON file");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchByMake();
                    break;
                case 2:
                    readJsonFile();
                    break;
                case 3:
                    addCar();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private void searchByMake() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter make to search: ");
        String make = scanner.nextLine();

        try {
            JSONArray jsonArray = readJsonArrayFromFile("Car_DataSet.json");
            JSONArray carsByMake = CarSearch.searchByMake(jsonArray, make);
            if (carsByMake.size() > 0) {
                System.out.println("Cars found:");
                System.out.printf("%-5s %-10s %-10s\n", "ID", "Make", "Price");
                for (Object obj : carsByMake) {
                    JSONObject car = (JSONObject) obj;
                    System.out.printf("%-5s %-10s %-10s\n", car.get("id"), car.get("make"), car.get("price"));
                }
            } else {
                System.out.println("No cars found for the specified make.");
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static JSONArray readJsonArrayFromFile(String fileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(fileName));
        JSONObject jsonObj = (JSONObject) obj;

        return (JSONArray) jsonObj.get("Cars");
    }

    private void readJsonFile() {
        // Car_read.readJsonArrayFromFile("Car_DataSet.json");
    }

    private void addCar() {
        AddCar.main(null);
    }
}
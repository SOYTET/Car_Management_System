import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class UpdateCar {
    private String code;
    private String name;

    public UpdateCar(String code, String name) {
        this.code = code;
        this.name = name;
        
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class CarManagementSystem {
    private ArrayList<Car> cars;

    public CarManagementSystem() {
        this.cars = new ArrayList<>();
    }

    public void updateCar(String code, String newName) {
        for (Car car : cars) {
            if (car.getCode().equals(code)) {
                car.setName(newName);
                System.out.println("Car updated successfully.");
                return;
            }
        }
        System.out.println("Car not found with code: " + code);
    }
}
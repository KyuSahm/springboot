import com.fasterxml.jackson.databind.ObjectMapper;
import dto.Car;
import dto.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        List<Car> carList = new ArrayList<>();

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");
        carList.add(car1);

        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNumber("22가 2222");
        car2.setType("suv");
        carList.add(car2);

        user.setCarList(carList);

        System.out.println(user);
    }
}

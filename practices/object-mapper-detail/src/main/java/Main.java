import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        System.out.println("=========================================================================================");
        System.out.println(user);

        String json = null;
        try {
            //json = objectMapper.writeValueAsString(user);
            json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
            System.out.println("=====================================================================================");
            System.out.printf("json string: %s\n", json);

            // JSON 문자열에서 JSON Node에 Access하기
            JsonNode rootNode = objectMapper.readTree(json);
            String name = rootNode.get("name").asText();
            int age = rootNode.get("age").asInt();
            System.out.println("=====================================================================================");
            System.out.printf("name: %s, age: %d\n", name, age);

            // JSON의 배열은 ArrayNode를 통해서 접근
            JsonNode carListNode = rootNode.get("car_list");
            ArrayNode arrayNode = (ArrayNode)carListNode;

            // TypeReference를 통해 원하는 Type으로 변경
            Car[] carArray = objectMapper.convertValue(arrayNode, new TypeReference<Car[]>(){});
            System.out.println("=====================================================================================");
            System.out.print("car array: [");
            for (Car car: carArray) {
                System.out.printf("%s ", car);
            }
            System.out.println("]");

            // TypeReference를 통해 원하는 Type으로 변경
            List<Car> carList1 = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>(){});
            System.out.println("=====================================================================================");
            System.out.printf("car list: %s\n", carList1);

            // TypeReference를 통해 원하는 Type으로 변경
            Set<Car> carSet = objectMapper.convertValue(arrayNode, new TypeReference<Set<Car>>(){});
            System.out.println("=====================================================================================");
            System.out.printf("car set: %s\n", carSet);

            // JsonNode는 값을 변경하는 메소드를 제공하지 않지만, ObjectNode는 메소드를 제공
            // ObjectNode가 JsonNode의 하위 클래스
            // AOP 또는 filter, intercepter에서 특정 데이터 값을 수정 가능
            ObjectNode objectNode = (ObjectNode) rootNode;
            objectNode.put("name", "steve");
            objectNode.put("age", 20);
            System.out.println("=====================================================================================");
            System.out.println(objectNode.toPrettyString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

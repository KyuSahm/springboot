package com.example.objectmapper;

import com.example.objectmapper.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

	@Test
	void contextLoads() throws JsonProcessingException {
		// Text 형태의 JSON -> Java Object
        // Java Object -> Text 형태의 JSON
		var objectMapper = new ObjectMapper();

		// object -> json text
		var user = new User("KyuSham", 45, "010-2222-3333");
		var text = objectMapper.writeValueAsString(user);
		System.out.println(text);

		// json text -> object
		var objectUser = objectMapper.readValue(text, User.class);
		System.out.println(objectUser);
		// json text -> object
	}

}

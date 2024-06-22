package com.example.jwt;

import com.example.jwt.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@SpringBootTest
class JwtApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
	}

	@Test
	void tokenCreate(){
		var claims = new HashMap<String, Object>();
		claims.put("user_id", 999);

		var expired_at = LocalDateTime.now().plusSeconds(20);

		var jwtToken = jwtService.create(claims, expired_at);

		System.out.println(jwtToken);
	}

	@Test
	void tokenValidation(){

		var token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo5OTksImV4cCI6MTcxOTA2MjM5N30.j-zE_KJx8_38UuEZZ0a7DrcuAPaW7y_s3J1_qZjzTls";

		jwtService.validation(token);
	}

}

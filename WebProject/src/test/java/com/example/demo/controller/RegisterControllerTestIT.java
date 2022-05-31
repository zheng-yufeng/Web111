package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTestIT {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void TestAccessRegister() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders//
				.get("/register")//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("Register_zheng"));
	}

	@Test
	public void TestRegist_Success() throws Exception {

		String username = "admintest1";
		String password = "6666";
		String passwordAgain = "6666";


		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username", username)//
				.param("password", password)//
				.param("password_again", passwordAgain)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
				.andExpect(view().name("Login_zheng"))//
				.andExpect(model().attribute("username", username));
		;
	}

	@Test
	public void TestRegist_PassWordIncorrect_Fail() throws Exception {
		String username = "admintest2";
		String password = "6666";
		String passwordAgain = "9999";

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username", username)//
				.param("password", password)//
				.param("password_again", passwordAgain)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("Register_fail"));
	}
}
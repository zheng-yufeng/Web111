package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
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
public class LoginControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void TestAccessLogin() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/login").accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("Login_zheng"));
	}

	@Test
	public void TestLogin_Success() throws Exception {
		String username = "admin";
		String password = "123";

		RequestBuilder request = MockMvcRequestBuilders.post("/login").param("username", username)
				.param("password", password).accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("Blog_zheng"))
				.andExpect(model().attribute("blogs", Matchers.hasItem(//
						Matchers.allOf(//
								Matchers.hasProperty("blogTitle", Matchers.is("title")), //
								Matchers.hasProperty("blogIntroduction", Matchers.is("introduction")), //
								Matchers.hasProperty("blogContents", Matchers.is("contents"))//
						))))//
				.andExpect(model().attribute("username", username));

	}

	@Test
	public void TestLogin_PasswordIncorrect_Fail() throws Exception {
		String username = "admin";
		String password = "9999";

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username", username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);//

		mockMvc.perform(request)//
				.andExpect(view().name("Login_fail"));
	}

	@Test
	public void TestLogin_Inexistence_Fail() throws Exception {
		String username = "admin";
		String password = "9999";

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username", username)//
				.param("password", password)//
				.accept(MediaType.APPLICATION_JSON);//

		mockMvc.perform(request)//
				.andExpect(view().name("Login_fail"));//
	}
}

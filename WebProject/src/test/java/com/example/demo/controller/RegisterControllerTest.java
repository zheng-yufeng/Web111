package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.UserInfoRepository.UserInfoRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserInfoRepository userInfoRepository;

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
		// 自己先设定一个桩，模拟数据库设定具体的值。
		String username = "admin";
		String password = "6666";
		String passwordAgain = "6666";

		// 会带着3个参数去访问/register，3个参数是自己赋予的。
		RequestBuilder request = MockMvcRequestBuilders//
				.post("/register")//
				.param("username", username)//
				.param("password", password)//
				.param("password_again", passwordAgain)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)// 当模拟http的request请求被抛出时，期望返回一个Login_zheng的结果。
				.andExpect(view().name("Login_zheng"))//
				.andExpect(model().attribute("username", username));
		;
	}

	@Test
	public void TestRegist_PassWordIncorrect_Fail() throws Exception {
		String username = "admin";
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

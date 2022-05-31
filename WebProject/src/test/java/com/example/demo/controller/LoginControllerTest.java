package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.UserInfoRepository.BlogInfoRepository;
import com.example.demo.UserInfoRepository.UserInfoRepository;
import com.example.demo.model.BlogInfo;
import com.example.demo.model.UserInfo;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserInfoRepository userInfoRepository;
	@MockBean
	private BlogInfoRepository blogInfoRepository;

	@Test
	public void TestAccessLogin() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders// 定义模拟的http请求
				.get("/login")// get方法的login
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)// 抛出一个模拟http的请求
				.andExpect(view().name("Login_zheng"));// 期望得到一个Login_zheng的结果
	}

	@Test
	public void TestLogin_Success() throws Exception {
		// 自己创建一个桩，设置用户名与密码（代替数据库）
		String username = "admin";
		String password = "admin";
		// 创建一个userInfo
		UserInfo userInfo = UserInfo.builder()//
				.userName(username)//
				.password(password)//
				.build();

		BlogInfo blogInfo = BlogInfo.builder()//
				.blogTitle("title")//
				.blogIntroduction("introduction")//
				.blogContents("contents")//
				.userName(username)//
				.userId(1l)//
				.blogId(1l)//
				.build();
		// 当findByUserName这个方法被(棕色的username参数)调用时，返回一个UserInfo类型的userInfo
		when(userInfoRepository.findByUserName(username)).thenReturn(userInfo);
		when(blogInfoRepository.findAll()).thenReturn(List.of(blogInfo));

		RequestBuilder request = MockMvcRequestBuilders// 定义requset请求
				.post("/login")// post方法
				.param("username", username)// 传参username
				.param("password", password)// 传参password
				.accept(MediaType.APPLICATION_JSON);// 固定

		mockMvc.perform(request)// 抛出一个模拟http的请求
				.andExpect(view().name("Blog_zheng"))// 看是否会返回一个期待的Blog_zheng的结果
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
		UserInfo userInfo = UserInfo.builder()//
				.userName(username)//
				.password(password)//
				.build();

		when(userInfoRepository.findByUserName(username)).thenReturn(userInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username", username)//
				.param("password", "6666")//
				.accept(MediaType.APPLICATION_JSON);//

		mockMvc.perform(request)//
				.andExpect(view().name("Login_fail"));
	}

	@Test
	public void TestLogin_Inexistence_Fail() throws Exception {
		String username = "admin";
		String password = "9999";
		UserInfo userInfo = UserInfo.builder()//
				.userName(username)//
				.password(password)//
				.build();

		when(userInfoRepository.findByUserName(username)).thenReturn(userInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/login")//
				.param("username", "zheng")//
				.param("password", "1234")//
				.accept(MediaType.APPLICATION_JSON);//

		mockMvc.perform(request)//
				.andExpect(view().name("Login_fail"));//
	}
}

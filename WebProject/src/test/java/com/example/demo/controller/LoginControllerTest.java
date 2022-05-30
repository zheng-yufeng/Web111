package com.example.demo.controller;

import static org.mockito.Mockito.when;
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
import com.example.demo.model.UserInfo;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserInfoRepository userInfoRepository;

	@Test
	public void testLogin_GetView_Success() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders// 定义模拟的http请求
				.get("/login")// get方法的login
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)// 抛出一个模拟http的请求
				.andExpect(view().name("Login_zheng"));// 期望得到一个Login_zheng的结果
	}

	@Test
	public void testLogin_Success() throws Exception {
		// step 1:自己创建一个桩，设置用户名与密码（代替数据库）
		String username = "admin";
		String password = "admin";
		// step 3：创建一个userInfo
		UserInfo userInfo = UserInfo.builder()//
				.userName(username)//
				.password(password)//
				.build();
		// step 2:当findByUserName这个方法被(棕色的username参数)调用时，返回一个UserInfo类型的userInfo
		when(userInfoRepository.findByUserName(username)).thenReturn(userInfo);

		RequestBuilder request = MockMvcRequestBuilders//定义requset请求
				.post("/login")//post方法
				.param("username", username)//传参username
				.param("password", password)//传参password
				.accept(MediaType.APPLICATION_JSON);//固定
 
		mockMvc.perform(request)// 抛出一个模拟http的请求
				.andExpect(view().name("Blog_zheng"));// 看是否会返回一个期待的Blog_zheng的结果

	}
}

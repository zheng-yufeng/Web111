package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

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

@SpringBootTest
@AutoConfigureMockMvc
public class BlogControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserInfoRepository userInfoRepository;
	@MockBean
	private BlogInfoRepository blogInfoRepository;

	@Test
	public void TestAccessBlog_Success() throws Exception {
		String username = "admin";

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/blog")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("Blog_zheng"));
	}

	@Test
	public void TestAccessBlogEditor_Success() throws Exception {
		String username = "admin";

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/editor")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("BlogEditor_zheng"));
	}

	@Test
	public void TestBlogEditor_Success() throws Exception {
		String username = "admin";
		String title = "title";
		String introduction = "introduction";
		String contents = "contents";
		BlogInfo blogInfo = BlogInfo.builder()//
				.blogTitle(title)//
				.blogIntroduction(introduction)//
				.blogContents(contents)//
				.userName(username)//
				.build();

		when(blogInfoRepository.save(blogInfo)).thenReturn(blogInfo);

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/editor")//
				.param("title", title)//
				.param("introduction", introduction)//
				.param("contents", contents)//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("Blog_zheng"));

	}

	@Test
	public void TestBlogDelete_Success() throws Exception {
		String username = "admin";
		Long blogId = (long) 999;

		BlogInfo blogInfo = BlogInfo.builder()//
				.blogId(blogId)//
				.userName(username).build();

		when(blogInfoRepository.findById(blogId)).thenReturn(Optional.of(blogInfo));

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/delete")//
				.param("blogId", "" + blogId)//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("Blog_zheng"));
	}

	@Test
	public void TestAccessBlogUpdate_Success() throws Exception {
		String username = "admin";
		Long blogId = (long) 999;

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/update")//
				.param("blogId", "" + blogId)//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("BlogUpdate_zheng"));
	}

	@Test
	public void TestBlogUpdate_Success() throws Exception {
		String username = "admin";
		Long blogId = (long) 999;
		String title = "title";
		String introduction = "introduction";
		String contents = "contents";

		BlogInfo blogInfo = BlogInfo.builder()//
				.blogTitle(title)//
				.blogIntroduction(introduction)//
				.blogContents(contents)//
				.userName(username)//
				.blogId(blogId)//
				.build();
 
		when(blogInfoRepository.findById(blogId)).thenReturn(Optional.of(blogInfo));

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", title)//
				.param("introduction", introduction)//
				.param("contents", contents)//
				.param("username", username)//
				.param("blogId", "" + blogId)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request).andExpect(view().name("Blog_zheng"));
	}

}

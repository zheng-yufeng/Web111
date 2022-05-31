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
public class BlogUpdateTestIT {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void TestAccessBlogUpdate() throws Exception {
		String username = "admin";
		Long blogId = (long) 999;

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/update")//
				.param("blogId", "" + blogId)//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("BlogUpdate_zheng"))//
				.andExpect(model().attribute("username", username))//
				.andExpect(model().attribute("blogId", (long) 999));
	}

	@Test
	public void TestBlogUpdate() throws Exception {
		String username = "admin";
		Long blogId = (long) 76;
		String title = "titles";
		String introduction = "introductions";
		String contents = "contents";

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/update")//
				.param("title", title)//
				.param("introduction", introduction)//
				.param("contents", contents)//
				.param("username", username)//
				.param("blogId", "" + blogId)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("Blog_zheng"))//
				.andExpect(model().attribute("blogs", Matchers.hasItem(//
						Matchers.allOf(//
								Matchers.hasProperty("blogTitle", Matchers.is("titles")), //
								Matchers.hasProperty("blogIntroduction", Matchers.is("introductions")), //
								Matchers.hasProperty("blogContents", Matchers.is("contents"))//
						))))//
				.andExpect(model().attribute("username", username));
	}

}
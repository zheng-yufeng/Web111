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
public class BlogControllerTestIT {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void TestAccessBlog() throws Exception {
		String username = "admin";

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/blog")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("Blog_zheng"))//
				.andExpect(model().attribute("blogs", Matchers.hasItem(//
						Matchers.allOf(//
								Matchers.hasProperty("blogTitle", Matchers.is("title")), //
								Matchers.hasProperty("blogIntroduction", Matchers.is("introduction")), //
								Matchers.hasProperty("blogContents", Matchers.is("contents"))//
						))))//
				.andExpect(model().attribute("username", username));
	}

	@Test
	public void TestAccessBlogEditor() throws Exception {
		String username = "admin";

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/editor")//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("BlogEditor_zheng"))//
				.andExpect(model().attribute("blogs", Matchers.hasItem(//
						Matchers.allOf(//
								Matchers.hasProperty("blogTitle", Matchers.is("title")), //
								Matchers.hasProperty("blogIntroduction", Matchers.is("introduction")), //
								Matchers.hasProperty("blogContents", Matchers.is("contents"))//
						))))//
				.andExpect(model().attribute("username", username));
	}

	@Test
	public void TestBlogEdit() throws Exception {
		String username = "admin";
		String title = "title";
		String introduction = "introduction";
		String contents = "contents";

		RequestBuilder request = MockMvcRequestBuilders//
				.post("/editor")//
				.param("title", title)//
				.param("introduction", introduction)//
				.param("contents", contents)//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("Blog_zheng"))//
				.andExpect(model().attribute("blogs", Matchers.hasItem(//
						Matchers.allOf(//
								Matchers.hasProperty("blogTitle", Matchers.is("title")), //
								Matchers.hasProperty("blogIntroduction", Matchers.is("introduction")), //
								Matchers.hasProperty("blogContents", Matchers.is("contents"))//
						))))//
				.andExpect(model().attribute("username", username));
	}
}
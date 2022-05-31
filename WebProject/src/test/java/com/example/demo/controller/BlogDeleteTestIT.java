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
public class BlogDeleteTestIT {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void TestBlogDelete() throws Exception {
		String username = "admin";
		Long blogId = (long) 77;

		RequestBuilder request = MockMvcRequestBuilders//
				.get("/delete")//
				.param("blogId", "" + blogId)//
				.param("username", username)//
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)//
				.andExpect(view().name("Blog_zheng"))//
				.andExpect(model().attribute("blogs", Matchers.not(//
						Matchers.hasItem(//
								Matchers.allOf(//
										Matchers.hasProperty("blogTitle", Matchers.is("title")), //
										Matchers.hasProperty("blogIntroduction", Matchers.is("introduction")), //
										Matchers.hasProperty("blogContents", Matchers.is("contents"))//
								)))))//
				.andExpect(model().attribute("username", username));
	}
}
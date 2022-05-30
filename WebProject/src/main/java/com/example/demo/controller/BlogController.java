package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.UserInfoRepository.BlogInfoRepository;
import com.example.demo.model.BlogInfo;

@Controller
public class BlogController {

	@Autowired
	private BlogInfoRepository blogInfoRepository;

	@GetMapping("/blog")
	// @RequestParam("写在html中的name") String 自己建的变量名
	public ModelAndView getBlogView(//
			@RequestParam("username") String username, //
			ModelAndView mv) {

		List<BlogInfo> blogs = blogInfoRepository.findAll();

		mv.addObject("blogs", blogs);
		mv.addObject("username", username);

		mv.setViewName("Blog_zheng");
		return mv;
	}

	@GetMapping("/editor")
	public ModelAndView getEditorView(//
			@RequestParam("username") String username, //
			ModelAndView mv) {

		List<BlogInfo> blogs = blogInfoRepository.findAll();

		mv.addObject("blogs", blogs);
		mv.addObject("username", username);

		mv.setViewName("BlogEditor_zheng");

		return mv;
	}

	@PostMapping("/editor")
	public ModelAndView editor(//
			@RequestParam("title") String title, //
			@RequestParam("introduction") String introduction, //
			@RequestParam("contents") String contents, //
			@RequestParam("username") String username, //
			ModelAndView mv) {

		BlogInfo blogInfo = BlogInfo.builder().blogTitle(title)//
				.blogIntroduction(introduction)//
				.blogContents(contents)//
				.build();
 
		blogInfoRepository.save(blogInfo);

		mv.addObject("blogs", blogInfoRepository.findAll());
		mv.addObject("username", username);

		mv.setViewName("Blog_zheng");

		return mv;
	}

	@GetMapping("/delete")
	public ModelAndView delete(//
			@RequestParam("username") String username, //
			@RequestParam("blogId") long blogId, //
			ModelAndView mv) {

		BlogInfo blogInfo = blogInfoRepository.findById(blogId).get();
		blogInfoRepository.delete(blogInfo);

		mv.addObject("blogs", blogInfoRepository.findAll());
		mv.addObject("username", username);

		mv.setViewName("Blog_zheng");

		return mv;
	}

	@GetMapping("/update")
	public ModelAndView update(//
			@RequestParam("username") String username, //
			@RequestParam("blogId") Long blogId, //
			ModelAndView mv) {

		mv.addObject("blogId", blogId);
		mv.addObject("username", username);

		mv.setViewName("BlogUpdate_zheng");

		return mv;
	}

	@PostMapping("/update")
	public ModelAndView update(//
			@RequestParam("title") String title, //
			@RequestParam("introduction") String introduction, //
			@RequestParam("contents") String contents, //
			@RequestParam("username") String username, //
			@RequestParam("blogId") Long blogId, //
			ModelAndView mv) {
 
		BlogInfo blogInfo = blogInfoRepository.findById(blogId).get();

		blogInfo.setBlogTitle(title);
		blogInfo.setBlogIntroduction(introduction);
		blogInfo.setBlogContents(contents);

		blogInfoRepository.save(blogInfo);

		mv.addObject("blogs", blogInfoRepository.findAll());
		mv.addObject("username", username);
 
		mv.setViewName("Blog_zheng");

		return mv;
	}

}
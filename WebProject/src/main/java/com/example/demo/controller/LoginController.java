package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.UserInfoRepository.BlogInfoRepository;
import com.example.demo.UserInfoRepository.UserInfoRepository;
import com.example.demo.model.BlogInfo;
import com.example.demo.model.UserInfo;

@Controller
@Slf4j
public class LoginController {

	@Autowired // UserInfoRepository是个接口，需要创建一个类之后再new，实例化。很麻烦，这个注释是让spring直接帮我们实例化
	private UserInfoRepository userInfoRepository;
	@Autowired
	private BlogInfoRepository blogInfoRepository;
	
	@GetMapping("/login")
	public String getLoginView() {
		return "Login_zheng";
	}

	@PostMapping("/login")
	public ModelAndView login(//
			// html中的name 自己创建的变量名
			@RequestParam("username") String username, //
			@RequestParam("password") String password, //
			ModelAndView mv) {

		mv.addObject("username", username);

		UserInfo userInfo = userInfoRepository.findByUserName(username);
		List<BlogInfo> blogs = blogInfoRepository.findAll();

		if (userInfo != null && password.equals(userInfo.getPassword())) {
			mv.addObject("blogs", blogs);
			mv.setViewName("Blog_zheng");
		} else {
			
			mv.setViewName("Login_fail");
		}
		return mv;
	}
}
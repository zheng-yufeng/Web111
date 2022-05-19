package net.zheng.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.zheng.hello.UserInfoRepository.UserInfoRepository;
import net.zheng.hello.model.UserInfo;

@Controller
@Slf4j
public class LoginController {
	// 和框架链接起来

	@Autowired
	private UserInfoRepository userInfoRepository;

	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}

	@PostMapping("/login")
	// html属性 自己创建的变量名
	public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password,
			ModelAndView mv) {
//		log.info("username:{},password:{}", username, password);

		// 对象名 ， 对象本人
		mv.addObject("username", username);

		// 这行代码很重要 右边的赋值，可能有，可能没有。所以左边的userInfo可能null空值
		UserInfo userInfo = userInfoRepository.findByName(username);

		if (userInfo != null
				&& /* username.equals(userInfo.getName()) && 可以没有 */ password.equals(userInfo.getPassword())) {
			// return "success";
			mv.setViewName("success");
		} else {
			// return "fail";
			mv.setViewName("fail");
		}
		return mv;
	}

	@GetMapping("/success")
	public String getSuccessView() {
		return "success";
	}

}
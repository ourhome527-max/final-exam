package com.framework.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController{
	@GetMapping("/home")
	@ResponseBody
	public String getHome() {
		log.info("메인 메서드 실행...!");
		return "Hello, SW Framework";
	}
}
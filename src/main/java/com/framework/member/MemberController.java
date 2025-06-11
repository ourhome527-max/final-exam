package com.framework.member;


import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;
	
	// 회원 가입 화면 이동
	@GetMapping("/join")
	public ModelAndView getJoinForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/member-join");
		return mav;
	}
	
	// 로그인 화면 이동
	@GetMapping("/login")
	public ModelAndView getLoginForm() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/member-login");
		return mav;
	}
	
	// 회원 가입
	@PostMapping("/join")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> insertMember(@RequestBody Map<String, Object> params) {
		log.info("insertMember 메서드 실행...!");
		Map<String, Object> result = null;
		return ResponseEntity.ok(result);
	}
	
	// 회원 정보 조회
	@GetMapping("/detail/{userId}")
	public ModelAndView getMemberDetail(@PathVariable("userId") String userId) {
		log.info("getMemberDetail 메서드 실행...!");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = null;
		mav.addObject("result", result);
		mav.setViewName("member/member-detail");
		return mav;
	}
	
	// 회원 수정
	@PostMapping("/modify")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> modifyMember(@RequestBody Map<String, Object> params) {
		log.info("modifyMember 메서드 실행...!");
		Map<String, Object> result = null;
		return ResponseEntity.ok(result);
	}
	
	// 회원 삭제
	@PostMapping("/delete")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> deleteMember(@RequestBody Map<String, Object> params) {
		log.info("deleteMember 메서드 실행...!");
		Map<String, Object> result = null;
		return ResponseEntity.ok(result);
	}
	
	// 로그인
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> loginMember(@RequestBody Map<String, Object> params,
			HttpSession session){
		log.info("loginMember 메서드 실행...!");
		Map<String, Object> result = null;
		if ("0000".equals(result.get("REPL_CD"))) {
			session.setAttribute("loginUser", params.get("userId"));
		}
		return ResponseEntity.ok(result);
	}
	
	// 로그아웃
}

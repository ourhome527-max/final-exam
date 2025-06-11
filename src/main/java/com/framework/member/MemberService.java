package com.framework.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
	private final MemberMapper memberMapper;
	
	@Transactional(readOnly = false)
	public Map<String, Object> insertMember(Map<String, Object> params){
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("REPL_CD", "0000");
			resultMap.put("REPL_MSG", "SUCCESS");
			resultMap.put("REPL_PAGE_MSG", "정상처리 되었습니다.");

		}catch(Exception e) {
			e.printStackTrace();
			log.error("회원 가입 오류 발생", e);
			resultMap.put("REPL_CD", "0001");
			resultMap.put("REPL_MSG", "FAILED");
			resultMap.put("REPL_PAGE_MSG", "회원 가입 오류 발생");
		}
		return resultMap;
	}
}

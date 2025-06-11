package com.framework.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
	int insertMember(Map<String, Object> params);
	int updateMember(Map<String, Object> params);
	int deleteMember(Map<String, Object> params);
	int checkDuplCnt(String userId);
	Map<String, Object> memberDetail(Map<String, Object> params);
}

package com.framework.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
	int selectProductTotalCount(Map<String, Object> params);
	List<Map<String, Object>> selectProductList(Map<String, Object> params);
	Map<String, Object> selectProduct(Map<String, Object> params);
	
}

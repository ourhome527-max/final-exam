package com.framework.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
	private final ProductMapper productMapper;

	// 상품 리스트 조회
	@Transactional(readOnly = true)
	public Map<String, Object> getProductList(SearchForm searchForm) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			resultMap.put("REPL_CD", "0000");
			resultMap.put("REPL_MSG", "SUCCESS");
			resultMap.put("REPL_PAGE_MSG", "정상처리 되었습니다.");

			Integer pageNum = searchForm.getPageNum() != null && searchForm.getPageNum() > 0 ? searchForm.getPageNum()
					: 1;
			String searchProductName = searchForm.getSearchProductName() != null
					? searchForm.getSearchProductName().trim()
					: "";
			String searchProductType = searchForm.getSearchProductType() != null
					? searchForm.getSearchProductType().trim()
					: "";
			int pageSize = 5;
			int offset = (pageNum - 1) * pageSize;
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("pageNum", pageNum);
			paramsMap.put("searchProductName", searchProductName);
			paramsMap.put("searchProductType", searchProductType);
			paramsMap.put("pageSize", pageSize);
			paramsMap.put("pageOffset", offset);

			int totalCount = productMapper.selectProductTotalCount(paramsMap);
			log.info("totalCount: {}", totalCount);
			resultMap.put("TOTAL_COUNT", totalCount);

			int firstPageNum = 1;
			int pageBlockSize = 5;

			List<Map<String, Object>> product_list = productMapper.selectProductList(paramsMap);
			log.info("상품 목록 조회 성공 : {}", product_list);
			resultMap.put("PRODUCT_LIST", product_list);

			int lastPageNum = (int) Math.ceil((double) totalCount / pageSize);

			int startBlockPage = ((pageNum - 1) / pageBlockSize) * pageBlockSize + 1;
			int endBlockPage = Math.min(startBlockPage + pageBlockSize - 1, lastPageNum);

			List<Integer> pageBlockList = new ArrayList<>();
			for (int i = startBlockPage; i <= endBlockPage; i++) {
				pageBlockList.add(i);
			}

			Map<String, Object> pagingMap = new HashMap<>();
			pagingMap.put("PAGE_BLOCK_LIST", pageBlockList);
			pagingMap.put("FIRST_PAGE_NUM", firstPageNum);
			pagingMap.put("LAST_PAGE_NUM", lastPageNum);
			pagingMap.put("PAGE_BLOCK_LIST", pageBlockList);
			pagingMap.put("PAGE_NUM", pageNum);
			pagingMap.put("PAGE_SIZE", pageSize);
			pagingMap.put("PAGE_OFFSET", offset);

			resultMap.put("pagingMap", pagingMap);

			resultMap.put("paramsMap", paramsMap);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("REPL_CD", "0001");
			resultMap.put("REPL_MSG", "PRODUCT_LIST_ERROR");
			resultMap.put("REPL_PAGE_MSG", "상품 목록 조회 중 오류가 발생하였습니다.");
		}
		return resultMap;
	}

	// 상품 상세 조회
	@Transactional(readOnly = true)
	public Map<String, Object> getProductDetail(int productSeq) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap.put("REPL_CD", "0000");
			resultMap.put("REPL_MSG", "SUCCESS");
			resultMap.put("REPL_PAGE_MSG", "정상처리 되었습니다.");

			productSeq = productSeq != 0 && productSeq > 0 ? productSeq : 1;
			log.info("productSeq: {}", productSeq);
			Map<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("productSeq", productSeq);
			Map<String, Object> product_detail = productMapper.selectProduct(paramsMap);
			log.info("상품 상세 정보: {}", product_detail);
			resultMap.put("PRODUCT_DETAIL", product_detail);

		} catch (Exception e) {
			log.error("SearchFormList 조회 오류", e);
			resultMap.put("REPL_CD", "0002");
			resultMap.put("REPL_MSG", "BOOK_DETAIL_ERROR");
			resultMap.put("REPL_PAGE_MSG", "도서 상세 조회 중 오류가 발생하였습니다.");
		}

		return resultMap;
	}
}

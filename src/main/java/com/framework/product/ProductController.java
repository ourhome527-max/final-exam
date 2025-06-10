package com.framework.product;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	// 상품 리스트 조회
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getProductList(@ModelAttribute SearchForm form) {
		log.info("getProductList 메서드 실행...!");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = productService.getProductList(form);
		mav.addObject("result", result);
		mav.setViewName("product/product-list");
		return mav;
	}
	
	// 상품 상세 조회
	@GetMapping("/detail/{productSeq}")
	public ModelAndView getProductDetail(@PathVariable("productSeq") int productSeq) {
		log.info("getProductDetail 메서드 실행...!");
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = productService.getProductDetail(productSeq);
		mav.addObject("result", result);
		mav.setViewName("product/product-list");
		return mav;
	}
}

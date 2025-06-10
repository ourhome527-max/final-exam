package com.framework.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchForm {
	private Integer pageNum;
	private String searchProductName;
	private String searchProductType;
}

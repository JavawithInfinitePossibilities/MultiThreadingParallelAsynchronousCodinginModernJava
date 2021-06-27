package com.sid.tutorials.multithreading.Java8.services;

import java.util.List;

import com.sid.tutorials.multithreading.Java8.domain.ProductInfo;
import com.sid.tutorials.multithreading.Java8.domain.ProductOption;
import com.sid.tutorials.multithreading.Java8.util.CommonUtil;

public class ProductInfoService {

	public ProductInfo retrieveProductInfo(String productId) {
		CommonUtil.delay(1000);
		List<ProductOption> productOptions = List.of(new ProductOption(1, "64GB", "Black", 699.99), new ProductOption(2,
				"128GB", "Black", 749.99));
		return ProductInfo.builder().productId(productId).productOptions(productOptions).build();
	}
}

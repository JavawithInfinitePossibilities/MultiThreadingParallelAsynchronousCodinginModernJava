package com.sid.tutorials.multithreading.Java8.services;

import java.util.concurrent.CompletableFuture;

import com.sid.tutorials.multithreading.Java8.domain.Product;
import com.sid.tutorials.multithreading.Java8.domain.ProductInfo;
import com.sid.tutorials.multithreading.Java8.domain.Review;
import com.sid.tutorials.multithreading.Java8.util.CommonUtil;
import com.sid.tutorials.multithreading.Java8.util.LoggerUtil;

public class ProductService {
	private ProductInfoService productInfoService;
	private ReviewService reviewService;

	public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
	}

	public Product retrieveProductDetails(String productId) {
		CommonUtil.stopWatch.start();

		CompletableFuture<ProductInfo> cfProductinfo = CompletableFuture.supplyAsync(() -> {
			return productInfoService.retrieveProductInfo(productId);
		});
		CompletableFuture<Review> cfReview = CompletableFuture.supplyAsync(() -> {
			return reviewService.retrieveReviews(productId);
		});

		Product product = cfProductinfo.thenCombine(cfReview, (productInfo, review) -> {
			return new Product(productId, productInfo, review);
		}).join();

		CommonUtil.stopWatch.stop();
		LoggerUtil.log("Total Time Taken : " + CommonUtil.stopWatch.getTime());
		return product;
	}
	
	public CompletableFuture<Product> retrieveProductDetailsApproach2(String productId) {
		CommonUtil.stopWatch.start();

		CompletableFuture<ProductInfo> cfProductinfo = CompletableFuture.supplyAsync(() -> {
			return productInfoService.retrieveProductInfo(productId);
		});
		CompletableFuture<Review> cfReview = CompletableFuture.supplyAsync(() -> {
			return reviewService.retrieveReviews(productId);
		});

		CompletableFuture<Product> product = cfProductinfo.thenCombine(cfReview, (productInfo, review) -> {
			return new Product(productId, productInfo, review);
		});

		CommonUtil.stopWatch.stop();
		LoggerUtil.log("Total Time Taken : " + CommonUtil.stopWatch.getTime());
		return product;
	}

}

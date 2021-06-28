package com.sid.tutorials.multithreading.Java8.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import com.sid.tutorials.multithreading.Java8.domain.Inventory;
import com.sid.tutorials.multithreading.Java8.domain.Product;
import com.sid.tutorials.multithreading.Java8.domain.ProductInfo;
import com.sid.tutorials.multithreading.Java8.domain.ProductOption;
import com.sid.tutorials.multithreading.Java8.domain.Review;
import com.sid.tutorials.multithreading.Java8.util.CommonUtil;
import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;

public class ProductService {
	private ProductInfoService productInfoService;
	private ReviewService reviewService;
	private InventoryService inventoryervice;

	public ProductService(ProductInfoService productInfoService, ReviewService reviewService) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
	}

	public ProductService(ProductInfoService productInfoService, ReviewService reviewService,
			InventoryService inventoryervice) {
		this.productInfoService = productInfoService;
		this.reviewService = reviewService;
		this.inventoryervice = inventoryervice;
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
		log("Total Time Taken : " + CommonUtil.stopWatch.getTime());
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
		log("Total Time Taken : " + CommonUtil.stopWatch.getTime());
		return product;
	}

	public Product retrieveProductDetailsWithInventory(String productId) {
		CommonUtil.stopWatch.start();
		CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture
				.supplyAsync(() -> productInfoService.retrieveProductInfo(productId)).thenApply(productInfo -> {
					productInfo.setProductOptions(updateInventory(productInfo));
					return productInfo;
				});
		CompletableFuture<Review> cfReview = CompletableFuture
				.supplyAsync(() -> reviewService.retrieveReviews(productId));
		Product product = cfProductInfo
				.thenCombine(cfReview, (productInfo, review) -> new Product(productId, productInfo, review))
				.whenComplete((result, exception) -> {
					log("Exception Is : " + exception.getMessage());
				}).join(); // block
		CommonUtil.stopWatch.stop();
		log("Total Time Taken : " + CommonUtil.stopWatch.getTime());
		return product;
	}

	private List<ProductOption> updateInventory(ProductInfo productInfo) {

		List<ProductOption> productOptionList = productInfo.getProductOptions().stream().map(productOption -> {
			Inventory inventory = inventoryervice.retrieveInventory(productOption);
			productOption.setInventory(inventory);
			return productOption;
		}).collect(Collectors.toList());
		return productOptionList;

	}

	public Product retrieveProductDetailsWithInventoryApproach2(String productId) {
		CommonUtil.stopWatch.start();
		CompletableFuture<ProductInfo> cfProductInfo = CompletableFuture
				.supplyAsync(() -> productInfoService.retrieveProductInfo(productId)).thenApply(productInfo -> {
					productInfo.setProductOptions(updateInventoryApproach2(productInfo));
					return productInfo;
				});
		CompletableFuture<Review> cfReview = CompletableFuture
				.supplyAsync(() -> reviewService.retrieveReviews(productId)).exceptionally((e) -> {
					log("Handled the Exception in reviewService : " + e.getMessage());
					return Review.builder().noOfReviews(0).overallRating(0.0).build();
				});
		Product product = cfProductInfo
				.thenCombine(cfReview, (productInfo, review) -> new Product(productId, productInfo, review)).join();
		CommonUtil.stopWatch.stop();
		log("Total Time Taken : " + CommonUtil.stopWatch.getTime());
		return product;
	}

	private List<ProductOption> updateInventoryApproach2(ProductInfo productInfo) {
		List<CompletableFuture<ProductOption>> productOptionList = productInfo.getProductOptions().stream()
				.map(productOption -> {
					return CompletableFuture.supplyAsync(() -> inventoryervice.retrieveInventory(productOption))
							.thenApply(inventory -> {
								productOption.setInventory(inventory);
								return productOption;
							});
				}).collect(Collectors.toList());
		return productOptionList.stream().map(CompletableFuture::join).collect(Collectors.toList());
	}
}

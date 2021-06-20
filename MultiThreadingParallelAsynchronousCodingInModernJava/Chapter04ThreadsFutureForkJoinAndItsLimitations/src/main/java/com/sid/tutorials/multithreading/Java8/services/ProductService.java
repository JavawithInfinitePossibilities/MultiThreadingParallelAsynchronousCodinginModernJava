package com.sid.tutorials.multithreading.Java8.services;

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

        ProductInfo productInfo = productInfoService.retrieveProductInfo(productId); // blocking call
        Review review = reviewService.retrieveReviews(productId); // blocking call

        CommonUtil.stopWatch.stop();
        LoggerUtil.log("Total Time Taken : "+ CommonUtil.stopWatch.getTime());
        return new Product(productId, productInfo, review);
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductService productService = new ProductService(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        LoggerUtil.log("Product is " + product);

    }
}

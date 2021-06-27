package com.sid.tutorials.multithreading.Java8.services;

import static com.sid.tutorials.multithreading.Java8.util.CommonUtil.*;
import static com.sid.tutorials.multithreading.Java8.util.LoggerUtil.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sid.tutorials.multithreading.Java8.domain.Cart;
import com.sid.tutorials.multithreading.Java8.domain.CartItem;
import com.sid.tutorials.multithreading.Java8.domain.CheckoutResponse;
import com.sid.tutorials.multithreading.Java8.domain.CheckoutStatus;

public class CheckoutService {

	private PriceValidatorService priceValidatorService;

	public CheckoutService(PriceValidatorService priceValidatorService) {
		this.priceValidatorService = priceValidatorService;
	}

	public CheckoutResponse checkout(Cart cart, boolean isParallel) {
		startTime(true);
		Stream<CartItem> cartStream = cart.getCartItemList().stream();
		if (isParallel) {
			cartStream = cartStream.parallel();
		}
		List<CartItem> priceValidationList = cartStream.map(cartItem -> {
			boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
			cartItem.setExpired(isPriceInvalid);
			return cartItem;
		}).filter(CartItem::isExpired).collect(Collectors.toList());

		timeTaken();
		if (priceValidationList.size() > 0) {
			return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
		}
		double finalPrice = 0.0;
		// finalPrice = calculateFinalPrice(cart);
		finalPrice = calculateFinalPrice_reduce(cart);
		log(" Checkout Complete and the final price is " + finalPrice);
		return new CheckoutResponse(CheckoutStatus.SUCCESS, finalPrice);
	}

	private double calculateFinalPrice(Cart cart) {
		return cart.getCartItemList().parallelStream().map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
				.mapToDouble(Double::doubleValue).sum();
	}

	private double calculateFinalPrice_reduce(Cart cart) {
		return cart.getCartItemList().parallelStream().map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
				.reduce(0.0, Double::sum);
	}
}

package com.sid.tutorials.multithreading.Java8.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CheckoutResponse {

	private CheckoutStatus checkoutStatus;
	private List<CartItem> errorList = new ArrayList<>();
	double finalRate;

	public CheckoutResponse(CheckoutStatus checkoutStatus) {
		this.checkoutStatus = checkoutStatus;
	}

	public CheckoutResponse(CheckoutStatus checkoutStatus, List<CartItem> errorList) {
		this.checkoutStatus = checkoutStatus;
		this.errorList = errorList;
	}

	public CheckoutResponse(CheckoutStatus checkoutStatus, double finalRate) {
		this.checkoutStatus = checkoutStatus;
		this.finalRate = finalRate;
	}
}

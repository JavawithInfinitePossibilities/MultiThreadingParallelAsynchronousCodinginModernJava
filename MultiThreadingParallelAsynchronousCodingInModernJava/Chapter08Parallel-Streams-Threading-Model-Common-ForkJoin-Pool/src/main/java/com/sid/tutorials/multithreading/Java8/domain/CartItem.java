package com.sid.tutorials.multithreading.Java8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {

    private Integer itemId;
    private String itemName;
    private double rate;
    private Integer quantity;
    private boolean isExpired;
}

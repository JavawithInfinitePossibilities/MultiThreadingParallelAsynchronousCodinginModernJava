package com.sid.tutorials.multithreading.Java8.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Cart {

    private Integer cardId;
    private List<CartItem> cartItemList;

}

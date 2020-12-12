package com.retail.discount.controller;

import com.retail.discount.dto.User;
import com.retail.discount.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class DiscountController {

    private DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/discount")
    public BigDecimal applyDiscount(@RequestBody User user){
        return discountService.applyDiscount(user);
    }
}

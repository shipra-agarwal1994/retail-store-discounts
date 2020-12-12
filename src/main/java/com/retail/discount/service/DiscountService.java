package com.retail.discount.service;

import com.retail.discount.dto.User;

import java.math.BigDecimal;

public interface DiscountService {

    BigDecimal applyDiscount(User user);
}

package com.retail.discount.service;

import com.retail.discount.dto.Item;
import com.retail.discount.dto.User;
import com.retail.discount.enums.ItemType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static com.retail.discount.constants.CommonConstants.*;
@Service
public class DiscountServiceImpl implements DiscountService{

    @Override
    public BigDecimal applyDiscount(User user) {
        BigDecimal discount ;
        BigDecimal netAmount;
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal billDiscount ;
        BigDecimal discountPer = getDiscountPercentage(user);
            for(Item item : user.getItems()){
                if(item.getItemType()== ItemType.OTHER){
                    discount = item.getPrice().multiply(discountPer);
                    netAmount = item.getPrice().subtract(discount);
                    totalAmount = totalAmount.add(netAmount);
                }else
                    totalAmount = totalAmount.add(item.getPrice());

            }
            int hundred = (totalAmount.divide(HUNDRED)).intValue();
        billDiscount = totalAmount.subtract(BigDecimal.valueOf(hundred).multiply(BILL_DISCOUNT));
        return billDiscount;

    }

    private BigDecimal getDiscountPercentage(User user) {
        BigDecimal discountPercentage = BigDecimal.ZERO;
        switch (user.getUserType()) {
            case CUSTOMER:
                if(Optional.ofNullable(user.getJoiningDate()).isPresent() &&
                        ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDateTime.now()) >= TWO_YEARS){
                        discountPercentage = CUSTOMER_DISCOUNT;
                }
                break;
            case EMPLOYEE:
                discountPercentage = EMPLOYEE_DISCOUNT;
                break;
            case AFFILIATE:
                discountPercentage = AFFILIATE_DISCOUNT;
                break;
            default:
                break;
        }
        return discountPercentage;
    }
}

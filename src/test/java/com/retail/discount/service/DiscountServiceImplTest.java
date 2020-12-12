package com.retail.discount.service;

import com.retail.discount.dto.Item;
import com.retail.discount.dto.User;
import com.retail.discount.enums.ItemType;
import com.retail.discount.enums.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class DiscountServiceImplTest {

    @InjectMocks
    private DiscountServiceImpl discountService;

    @BeforeEach
    public void setup() {
    }

    @Test
    void testCustomerDiscount() {
        User user = User.builder()
                .items(Arrays.asList(Item.builder()
                .name("Curtain")
                .itemType(ItemType.OTHER)
                .price(BigDecimal.valueOf(500))
                .build()))
                .userType(UserType.CUSTOMER)
                .name("Sam")
                .joiningDate(LocalDateTime.of(2017,01,29,00,00))
                .build();
      BigDecimal actual = discountService.applyDiscount(user);
      Assertions.assertEquals(455.00,actual.doubleValue());
    }

    @Test
    void testEmployeeDiscount() {
        User user = User.builder()
                .items(Arrays.asList(Item.builder()
                        .name("Chair")
                        .itemType(ItemType.OTHER)
                        .price(BigDecimal.valueOf(500))
                        .build()))
                .userType(UserType.EMPLOYEE)
                .name("Ravi")
                .build();
        BigDecimal actual = discountService.applyDiscount(user);
        Assertions.assertEquals(335.00,actual.doubleValue());
    }

    @Test
    void testAffiliateDiscount() {
        User user = User.builder()
                .items(Arrays.asList(Item.builder()
                        .name("Book")
                        .itemType(ItemType.OTHER)
                        .price(BigDecimal.valueOf(300))
                        .build()))
                .userType(UserType.AFFILIATE)
                .name("Raj")
                .build();
        BigDecimal actual = discountService.applyDiscount(user);
        Assertions.assertEquals(260.00,actual.doubleValue());
    }

    @Test
    void testDiscountOnGrocery() {
        User user = User.builder()
                .items(Arrays.asList(Item.builder()
                        .name("Banana")
                        .itemType(ItemType.GROCERY)
                        .price(BigDecimal.valueOf(100))
                        .build()))
                .userType(UserType.AFFILIATE)
                .name("Raj")
                .build();
        BigDecimal actual = discountService.applyDiscount(user);
        Assertions.assertEquals(95.00,actual.doubleValue());
    }
}

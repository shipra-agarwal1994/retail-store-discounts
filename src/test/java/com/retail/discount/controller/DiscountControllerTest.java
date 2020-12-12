package com.retail.discount.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.retail.discount.dto.Item;
import com.retail.discount.dto.User;
import com.retail.discount.enums.ItemType;
import com.retail.discount.enums.UserType;
import com.retail.discount.service.DiscountServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DiscountController.class)
class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountServiceImpl discountService;

    @MockBean DiscountController controller;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void testApplyDiscount() throws Exception {
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
        given(discountService.applyDiscount(user)).willReturn(BigDecimal.valueOf(455.0));

        this.mockMvc.perform(post("/discount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

}

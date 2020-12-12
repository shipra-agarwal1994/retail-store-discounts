package com.retail.discount.dto;

import com.retail.discount.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Item {
    private String name;
    private ItemType itemType;
    private BigDecimal price;
}

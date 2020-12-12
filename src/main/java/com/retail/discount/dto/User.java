package com.retail.discount.dto;

import com.retail.discount.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class User {
    private UserType userType;
    private String name;
    private List<Item> items;
    private final LocalDateTime joiningDate;
}

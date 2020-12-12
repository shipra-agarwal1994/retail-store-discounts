package com.retail.discount.repository;

import com.retail.discount.domain.DiscountPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountPercentage, Long> {


}

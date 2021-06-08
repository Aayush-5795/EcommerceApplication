package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.entity.OrderDetails;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetails, Long> {

}

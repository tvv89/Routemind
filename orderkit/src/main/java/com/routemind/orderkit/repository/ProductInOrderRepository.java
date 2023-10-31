package com.routemind.orderkit.repository;

import com.routemind.orderkit.model.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {
}

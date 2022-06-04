package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
//	@Query(value = "Select o FROM orders o join orders o on e.eid=o.eid", nativeQuery = true)
//	@Query(value = "Select o FROM orders o left join order_detail od on o.seq=od.oid WHERE o.seq=:oid")
	List<Orders> findOrderByOid(@Param("oid") Integer oid);
}

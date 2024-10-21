package com.example.tempbookshop.repo;

import com.example.tempbookshop.dto.OrderDetailsDTO;
import com.example.tempbookshop.entity.Items;
import com.example.tempbookshop.entity.OrderDetails;
import com.example.tempbookshop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Integer> {
    @Query(value = "SELECT * FROM order_details WHERE invoice_number = ?1", nativeQuery = true)
    ArrayList<OrderDetails> findInvoiceOrderDetails(int invoice_number);
}

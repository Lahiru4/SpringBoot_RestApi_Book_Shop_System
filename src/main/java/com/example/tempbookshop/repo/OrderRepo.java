package com.example.tempbookshop.repo;

import com.example.tempbookshop.entity.Items;
import com.example.tempbookshop.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Integer> {
    @Query("SELECT MAX(o.invoice_number) FROM Orders o")
    Integer findMaxInvoiceNumber();
    @Query(value = "SELECT * FROM Orders WHERE in_date = ?1", nativeQuery = true)
    ArrayList<Orders> findInDate(String in_date);
}

package com.example.tempbookshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int invoice_number;
    private String itemId;
    private String item_name;
    private String quantity;
    private String mrp;
    private String price;
    private String amount;

}

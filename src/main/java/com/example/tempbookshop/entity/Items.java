package com.example.tempbookshop.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String item_code;
    private String item_name;
    private String perches_price;
    private String selling_price;
    private String item_discount;
    private String final_selling_price;
    private String qty;
    private String inDate;
}

package com.example.tempbookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailsDTO {
    private int id;
    private int invoice_number;
    private int itemId;
    private String item_name;
    private String quantity;
    private String mrp;
    private String price;
    private String amount;
    private String tot_cost;
    private String tot_profit;
}

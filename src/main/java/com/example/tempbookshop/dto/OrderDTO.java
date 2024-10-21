package com.example.tempbookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int invoice_number;
    private String in_date;
    private String items_count;
    private String total;
    private String discount;
    private String discount_rs;
    private String grand_total;
    private String pay_amount;
    private String balance;
    private String payAmount_type;
    private String cost;
    private String profit;
    private String cashier;
}

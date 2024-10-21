package com.example.tempbookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemsDTO {
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

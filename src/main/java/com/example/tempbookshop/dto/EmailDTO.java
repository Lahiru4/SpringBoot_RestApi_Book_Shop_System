package com.example.tempbookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data@AllArgsConstructor@NoArgsConstructor
public class EmailDTO {
    private String subject ="00";
    private String sales_Count="00";
    private String qty_Count="00";
    private String cost="00";
    private String profit="00";
}

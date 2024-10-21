package com.example.tempbookshop.controller;

import com.example.tempbookshop.dto.EmailDTO;
import com.example.tempbookshop.dto.OrderDTO;
import com.example.tempbookshop.service.OrderService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@RequestMapping("/ali/v1/email")
public class SendEmail {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private OrderService orderService;
    @GetMapping("/sendDalayReport")
    public String sendEmail(){
        try {
            EmailDTO emailDTO = new EmailDTO();
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = currentDate.format(formatter);
            ArrayList<OrderDTO> dayOrders = orderService.getDayOrders(formattedDate);
            for (OrderDTO dayOrder : dayOrders) {
                emailDTO.setCost(Double.parseDouble(emailDTO.getCost())+Double.parseDouble(dayOrder.getCost())+"");
                emailDTO.setProfit(Double.parseDouble(emailDTO.getProfit())+Double.parseDouble(dayOrder.getProfit())+"");
                emailDTO.setQty_Count(Integer.parseInt(emailDTO.getQty_Count())+Integer.parseInt(dayOrder.getItems_count())+"");
            }
            emailDTO.setSales_Count(dayOrders.size()+"");
            System.out.println(emailDTO.getCost());
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo("wniputharika96@gmail.com");
            mimeMessageHelper.setFrom("companylinex7@gmail.com");
            mimeMessage.setSubject("Datales");
            mimeMessageHelper.setText("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <style>\n" +
                    "        div{\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "section{\n" +
                            "            text-align: left;\n" +
                            "        }"+

                    "    </style>\n" +
                    "\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div>\n" +

                    "    <h1 style=\"color:#3a86dd;\">BooK Shop System</h1>\n" +
                    "    <h2>Date : "+LocalDate.now().toString()+"</h2>\n" +
                    "</div>\n" +
                     "<section>" +
                    "<h3>Total Sales Count :"+emailDTO.getSales_Count()+"</h3>\n" +
                    "<h3>Total Qty Count :"+emailDTO.getQty_Count()+"</h3>\n" +
                    "<h3>Total Cost :"+emailDTO.getCost()+"</h3>\n" +
                    "<h3>Total profit :"+emailDTO.getProfit()+"</h3>\n" +"</section>"+
                    "</body>\n" +
                    "</html>\n",true);
            javaMailSender.send(mimeMessage);

            return "okay";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}

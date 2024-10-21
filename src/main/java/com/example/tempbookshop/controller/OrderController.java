package com.example.tempbookshop.controller;

import com.example.tempbookshop.dto.OrderDetailsDTO;
import com.example.tempbookshop.dto.OrderDTO;
import com.example.tempbookshop.dto.ResponseDTO;
import com.example.tempbookshop.entity.Items;
import com.example.tempbookshop.service.OrderService;
import com.example.tempbookshop.util.RespList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/ali/v1/order/")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    private ResponseDTO responseDTO;
    @GetMapping("/getInvoiceNumber")
    public int getInvoiceNumber(){
        int maxId = orderService.newInvoiceNumber();
        System.out.println(maxId);
        maxId++;
        return maxId;
    }
    @PostMapping("/saveOrderDetails")
    public ResponseEntity saveOrderDetails(@RequestBody ArrayList<OrderDetailsDTO> orderDetailsDTOS){
        try {
            for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
                System.out.println(orderDetailsDTO);
            }
            orderService.saverOrderDetails(orderDetailsDTOS);
            responseDTO.setCode("00");

        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setCode(RespList.RSP_ERROR);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getAllOrderDetails/{invoiceNumber}")
    public  ResponseEntity getOrderDetails(@PathVariable("invoiceNumber") int invoiceNumber ){
        try {
            ArrayList<OrderDetailsDTO> allOrderDetails = orderService.getAllOrderDetails(invoiceNumber);
            responseDTO.setContent(allOrderDetails);
            responseDTO.setCode("00");
            return new ResponseEntity(responseDTO,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setContent(null);
            responseDTO.setCode("05");
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/saveOrder")
    public ResponseEntity saveOrder(@RequestBody OrderDTO orderDTO){
        System.out.println(orderDTO);
        LocalDate currentDate = LocalDate.now();

        // Define the desired date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

        // Format the current date using the formatter
        String formattedDate = currentDate.format(formatter);
        orderDTO.setIn_date(currentDate.toString());
        OrderDTO saveOrder = orderService.saveOrder(orderDTO);
        responseDTO.setCode("00");
        responseDTO.setContent(saveOrder);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getDalyOrders/{date}")
    public ResponseEntity getDayOrder(@PathVariable("date") String date){
        try {

            ArrayList<OrderDTO> dayOrders = orderService.getDayOrders(date);
            responseDTO.setCode("00");
            responseDTO.setContent(dayOrders);
            System.out.println(dayOrders.toString());
            return new ResponseEntity(responseDTO, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/getOrder/{invoiceNumber}")
    public ResponseEntity getOrder(@PathVariable("invoiceNumber") int invoiceNumber){
        try {
            OrderDTO order = orderService.getOrder(invoiceNumber);
            responseDTO.setContent(order);
            responseDTO.setCode("00");
            return new ResponseEntity(responseDTO,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setCode("05");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
        }

    }
}

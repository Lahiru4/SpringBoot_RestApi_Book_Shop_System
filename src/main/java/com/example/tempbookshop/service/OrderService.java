package com.example.tempbookshop.service;

import com.example.tempbookshop.dto.ItemsDTO;
import com.example.tempbookshop.dto.OrderDetailsDTO;
import com.example.tempbookshop.dto.OrderDTO;
import com.example.tempbookshop.entity.Items;
import com.example.tempbookshop.entity.OrderDetails;
import com.example.tempbookshop.entity.Orders;
import com.example.tempbookshop.repo.ItemsRepo;
import com.example.tempbookshop.repo.OrderDetailsRepo;
import com.example.tempbookshop.repo.OrderRepo;
import com.example.tempbookshop.util.RespList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ItemsService itemsService;
    @Autowired
    ItemsRepo itemsRepo;
    public OrderDTO saveOrder(OrderDTO orderDTO){
        Orders save = orderRepo.save(modelMapper.map(orderDTO, Orders.class));
        return modelMapper.map(save,OrderDTO.class);
    }

    public int newInvoiceNumber(){
        Integer maxInvoiceNumber = orderRepo.findMaxInvoiceNumber();
        if (maxInvoiceNumber!=null) {
            return maxInvoiceNumber;
        }
        return 0;
    }

    public String saverOrderDetails(ArrayList<OrderDetailsDTO> orderDetailsDTOS) {
        double cost=00;
        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOS) {
            itemsService.upDateQty(orderDetailsDTO.getItemId(),orderDetailsDTO.getQuantity());
            orderDetailsRepo.save(modelMapper.map(orderDetailsDTO, OrderDetails.class));

            Items items = itemsRepo.findById(orderDetailsDTO.getItemId()).orElse(null);
            System.out.println(items.getPerches_price());
            cost+=Double.parseDouble(items.getPerches_price())*Integer.parseInt(orderDetailsDTO.getQuantity());
        }
        Orders orders = orderRepo.findById(orderDetailsDTOS.get(0).getInvoice_number()).orElse(null);
        orders.setCost(cost+"");
        String grandTotal = orders.getGrand_total();
        orders.setProfit((Double.parseDouble(grandTotal)-cost)+"");
        orderRepo.save(orders);
        System.out.println(cost);
        return RespList.RSP_SUCCESS;
    }

    public ArrayList<OrderDTO> getDayOrders(String date) {
        ArrayList<Orders> all = orderRepo.findInDate(date);
        return modelMapper.map(all, new TypeToken<List<OrderDTO>>() {
        }.getType());
    }

    public OrderDTO getOrder(int invoiceNumber) {
        if (orderRepo.existsById(invoiceNumber)) {
            Orders orders = orderRepo.findById(invoiceNumber).orElse(null);
            return modelMapper.map(orders,OrderDTO.class);
        }
        return null;
    }

    public ArrayList<OrderDetailsDTO> getAllOrderDetails(int invoiceNumber) {
        ArrayList<OrderDetails> invoiceOrderDetails = orderDetailsRepo.findInvoiceOrderDetails(invoiceNumber);
        return modelMapper.map(invoiceOrderDetails,new TypeToken<List<OrderDetailsDTO>>() {
        }.getType());
    }
}
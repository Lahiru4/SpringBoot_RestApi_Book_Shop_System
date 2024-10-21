package com.example.tempbookshop.controller;




import com.example.tempbookshop.dto.ItemsDTO;
import com.example.tempbookshop.dto.ResponseDTO;
import com.example.tempbookshop.service.ItemsService;
import com.example.tempbookshop.util.RespList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/ali/v1/stock")
@CrossOrigin
public class ItemsController {
    @Autowired
    private ItemsService itemsService;

    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/save")
    public ResponseEntity saveItems(@RequestBody ItemsDTO itemsDTO) {
        try {
            itemsDTO.setInDate(LocalDate.now().toString());
            System.out.println(itemsDTO);
            String s = itemsService.saveItems(itemsDTO);
            if (s.equals("00")) {
                responseDTO.setCode(RespList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(itemsDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (s.equals("06")) {
                responseDTO.setCode(RespList.RSP_DUPLICATED);
                responseDTO.setMessage("Items Have");
                responseDTO.setContent(itemsDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(RespList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setCode(RespList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            List<ItemsDTO> all = itemsService.getAll();
            responseDTO.setContent(all);
            responseDTO.setMessage("Success");
            responseDTO.setCode(RespList.RSP_SUCCESS);
            return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
        }catch (Exception e){
            e.printStackTrace();
            responseDTO.setCode(RespList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteItems(@PathVariable("id") int id) {
        itemsService.delete(id);
        responseDTO.setCode("00");
        responseDTO.setMessage("");
        responseDTO.setContent(null);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

    }

    @PutMapping("/update")
    public ResponseEntity updateItem(@RequestBody ItemsDTO itemsDTO) {
        try {
            itemsDTO.setInDate(LocalDate.now().toString());
            System.out.println(itemsDTO);
            String s = itemsService.update(itemsDTO);
            if (s.equals("00")) {
                responseDTO.setCode(RespList.RSP_SUCCESS);
                responseDTO.setMessage("success");
                responseDTO.setContent(itemsDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
            } else if (s.equals("06")) {
                responseDTO.setCode(RespList.RSP_DUPLICATED);
                responseDTO.setMessage("Items Have");
                responseDTO.setContent(itemsDTO);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            } else {
                responseDTO.setCode(RespList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        } catch (Exception e) {
            responseDTO.setCode(RespList.RSP_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}

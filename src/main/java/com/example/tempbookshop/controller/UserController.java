package com.example.tempbookshop.controller;


import com.example.tempbookshop.dto.ResponseDTO;
import com.example.tempbookshop.dto.UserDTO;
import com.example.tempbookshop.service.UserService;
import com.example.tempbookshop.util.RespList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ali/v1/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ResponseDTO responseDTO;

    @PostMapping("/save")
    public ResponseEntity saveUser(@RequestBody UserDTO userDTO){
        try {
            System.out.println(userDTO);
            String resp = userService.saveUser(userDTO);
            //userDTO.setUsername(null);
            //userDTO.setPassword(null);

            if(resp.equals("00")){
                responseDTO.setCode(RespList.RSP_SUCCESS);
                responseDTO.setMessage("Success");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
            }else if(resp.equals("06")){
                responseDTO.setCode(RespList.RSP_DUPLICATED);
                responseDTO.setMessage("User Registered");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }else{
                responseDTO.setCode(RespList.RSP_FAIL);
                responseDTO.setMessage("Error");
                responseDTO.setContent(userDTO);
                return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDTO.setCode(RespList.RSP_ERROR);
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getAll")
    public ResponseEntity getAll(){
        try {
            List<UserDTO> all = userService.getAll();
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
    @GetMapping("/checkUser/{username}/{password}")
    public ResponseEntity findUserByPasswordAndUsername(@PathVariable String username, @PathVariable String password) {
        UserDTO userByPasswordAndUsername = userService.findUserByPasswordAndUsername(username, password);
        if (userByPasswordAndUsername!=null) {
            responseDTO.setCode(RespList.RSP_SUCCESS);
            responseDTO.setMessage("RSP_SUCCESS");
            responseDTO.setContent(userByPasswordAndUsername);
            return new ResponseEntity(responseDTO,HttpStatus.OK);
        }else {
            responseDTO.setCode(RespList.RSP_ERROR);
            responseDTO.setMessage("RSP_ERROR");
            responseDTO.setContent(null);
            return new ResponseEntity(responseDTO,HttpStatus.OK);

        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteItems(@PathVariable("id") int id) {
        String delete = userService.delete(id);
        responseDTO.setCode(delete);
        responseDTO.setMessage(delete);
        responseDTO.setContent(null);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);

    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity getUser(@PathVariable("id") int id){
        UserDTO user = userService.getUser(id);
        if (user!=null) {
            responseDTO.setCode("00");
            responseDTO.setContent(user);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }else {
            responseDTO.setCode("05");
            responseDTO.setContent(null);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
    }

}

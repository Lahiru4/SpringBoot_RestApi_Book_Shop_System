package com.example.tempbookshop.service;

import com.example.tempbookshop.dto.UserDTO;
import com.example.tempbookshop.entity.User;
import com.example.tempbookshop.repo.UserRepo;
import com.example.tempbookshop.util.RespList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveUser(UserDTO userDTO) {
        userRepo.save(modelMapper.map(userDTO, User.class));
        return RespList.RSP_SUCCESS;
    }

    public String updateUser(UserDTO userDTO) {
        /*if (userRepo.existsById(userDTO.getId() + "")) {
            userRepo.save(modelMapper.map(userDTO, User.class));
            return RespList.RSP_SUCCESS;
        } else {*/
            return RespList.RSP_NO_DATA_FOUND;
        //}
    }

    public String deleteUser(String id) {
        //if (userRepo.existsById(id)) {
         //   userRepo.deleteById(id);
            return RespList.RSP_SUCCESS;
       // } else {
       //     return RespList.RSP_NO_DATA_FOUND;
       // }
    }

    public UserDTO findUserById(String id) {
       // if (userRepo.existsById(id)) {
        //    User user = userRepo.findUserById(Integer.parseInt(id));
        //    return modelMapper.map(user, UserDTO.class);
       // } else {
            return null;
       // }
    }

    public UserDTO findUserByPasswordAndUsername(String username, String password) {
        User user = userRepo.findUserByPasswordAndUsername(username, password);
        System.out.println(user);
        if (user != null) {
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }


    public List<UserDTO> getAll() {
        List<User> all = userRepo.findAll();
        return modelMapper.map(all, new TypeToken<List<UserDTO>>() {
        }.getType());
    }

    public String delete(int id) {
        if (userRepo.existsById(id)) {
            System.out.println(id);
            userRepo.deleteById(id);
            return RespList.RSP_SUCCESS;
        }else {
            return RespList.RSP_NO_DATA_FOUND;
        }
    }

    public UserDTO getUser(int id) {
        if (userRepo.existsById(id)) {
            return modelMapper.map(userRepo.findUserById(id),UserDTO.class);
        }
        return null;

    }
}

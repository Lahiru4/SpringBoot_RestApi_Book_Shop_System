package com.example.tempbookshop.service;


import com.example.tempbookshop.dto.ItemsDTO;
import com.example.tempbookshop.entity.Items;
import com.example.tempbookshop.repo.ItemsRepo;
import com.example.tempbookshop.util.RespList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class ItemsService {
    @Autowired
    ItemsRepo itemsRepo;
    @Autowired
    ModelMapper modelMapper;

    public String saveItems(ItemsDTO itemsDTO) {
        itemsRepo.save(modelMapper.map(itemsDTO, Items.class));
        return RespList.RSP_SUCCESS;

    }
    public List<ItemsDTO> getAll() {
        List<Items> all = itemsRepo.findAll();
        return modelMapper.map(all, new TypeToken<List<ItemsDTO>>() {
        }.getType());
    }

    public void delete(int id) {
        if (itemsRepo.existsById(id)) {
            itemsRepo.deleteById(id);
        }
    }
    public String upDateQty(int id,String qty){
        Items referenceById = itemsRepo.getReferenceById(id);
        int c_qty = Integer.parseInt(referenceById.getQty());
        referenceById.setQty((c_qty-Integer.parseInt(qty))+"");
        Items save = itemsRepo.save(referenceById);
        return save.getQty();
    }

    public String update(ItemsDTO itemsDTO) {
        boolean opt = itemsRepo.existsById(itemsDTO.getId());
        if (opt) {
            Items save = itemsRepo.save(modelMapper.map(itemsDTO, Items.class));
            if (save!=null) {
                return RespList.RSP_SUCCESS;
            }
        }else {
            return RespList.RSP_NO_DATA_FOUND;
        }
        return RespList.RSP_FAIL;

    }
}

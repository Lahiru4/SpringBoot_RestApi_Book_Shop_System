package com.example.tempbookshop.repo;



import com.example.tempbookshop.entity.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemsRepo extends JpaRepository<Items,Integer> {
    @Query(value = "SELECT * FROM Items WHERE item_code = ?1", nativeQuery = true)
    Items findUserByItem_code(String item_code);

}

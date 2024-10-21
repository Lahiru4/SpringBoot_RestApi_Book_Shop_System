package com.example.tempbookshop.repo;


import com.example.tempbookshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM USER WHERE ID = ?1", nativeQuery = true)
    User findUserById(int id);

    @Query(value = "SELECT * FROM USER WHERE USERNAME = ?1 AND PASSWORD = ?2", nativeQuery = true)
    User findUserByPasswordAndUsername(String username, String password);
}

package com.example.demo.Database;

import com.example.demo.Classes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo  extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u where u.email LIKE (:query)")
    public User searchByEmail(@Param("query") String query);
}

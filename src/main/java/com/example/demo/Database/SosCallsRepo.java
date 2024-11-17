package com.example.demo.Database;

import com.example.demo.Classes.SosCalls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SosCallsRepo extends JpaRepository<SosCalls,Integer> {
    @Query("SELECT s from SosCalls s where s.userId = :userId")
    public SosCalls searchFromUSerId(@Param("userId") Integer userId);
}

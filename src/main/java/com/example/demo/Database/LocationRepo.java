package com.example.demo.Database;

import com.example.demo.Classes.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location,Integer> {
    @Query("select l from Location l where l.userId = :userId")
    public List<Location> findLocationByUserId(@Param("userId") Integer userId);
    @Modifying
    @Query("DELETE FROM Location l WHERE l.userId = :userId")
    public void deleteByUserId(@Param("userId") Integer userId);
}

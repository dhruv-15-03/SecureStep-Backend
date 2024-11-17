package com.example.demo.Database;

import com.example.demo.Classes.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepo extends JpaRepository<Area,Integer> {
}

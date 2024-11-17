package com.example.demo.Database;

import com.example.demo.Classes.Crime;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeRepo extends JpaRepository<Crime,Integer> {
    @Query(value = """
        SELECT COUNT(*)
        FROM Crime c
        WHERE (6371 * acos(
            cos(radians(:latitude)) * cos(radians(c.latitude)) *\s
            cos(radians(c.longitude) - radians(:longitude)) +\s
            sin(radians(:latitude)) * sin(radians(c.latitude))
        )) < 2
    """, nativeQuery = true)
    public Integer crimeWith2km(@Param("latitude") double latitude,@Param("longitude") double longitude);
}

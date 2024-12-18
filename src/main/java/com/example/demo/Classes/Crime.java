package com.example.demo.Classes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String crime;
    private Double latitude;
    private Double longitude;
}

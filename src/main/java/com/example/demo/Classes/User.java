package com.example.demo.Classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    @Column(unique = true,nullable = false)
    private String email;
    private String gender;
    private Integer age;
    private String profile;
    private boolean SecureStep;
    private String zone;
    private String password;
    @OneToOne
    private Location location;
    @OneToMany
    @JsonIgnore
    private List<Feedback> feedbacks=new ArrayList<>();
    @OneToMany
    @JsonIgnore
    private List<Review> reviews=new ArrayList<>();
    @OneToOne
    private SosCalls sos;


    public User(Integer id, String name, String email,Integer age,String password,String gender,String profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password=password;
        this.gender=gender;
        this.profile=profile;
        this.age=age;
        this.SecureStep=true;
    }
}

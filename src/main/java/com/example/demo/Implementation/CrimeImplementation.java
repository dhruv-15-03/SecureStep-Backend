package com.example.demo.Implementation;

import com.example.demo.Classes.User;
import com.example.demo.Database.CrimeRepo;
import com.example.demo.Database.UserRepo;
import com.example.demo.Methods.CrimesMethods;
import org.springframework.beans.factory.annotation.Autowired;

public class CrimeImplementation implements CrimesMethods {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CrimeRepo crimeRepo;
    @Override
    public String safeZone(Integer userId) {
        User user=userRepo.getReferenceById(userId);
        return null;
    }

    @Override
    public String Alerts(Integer userId) {
        return null;
    }
}

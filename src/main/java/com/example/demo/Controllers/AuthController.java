package com.example.demo.Controllers;

import com.example.demo.Classes.User;
import com.example.demo.Database.UserRepo;
import com.example.demo.Implementation.CustomerUser;
import com.example.demo.Response.AuthResponse;
import com.example.demo.Response.Login;
import com.example.demo.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerUser customerUser;
    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody User user) throws Exception{
        User newUser = new User();

        User check=userRepo.searchByEmail(user.getEmail());
        if(check!=null){
            throw new Exception("User exist with username :" + user.getName());
        }
        newUser.setFeedbacks(user.getFeedbacks());
        newUser.setReviews(user.getReviews());
        newUser.setAge(user.getAge());
        newUser.setProfile(user.getProfile());
        newUser.setSecureStep(false);
        newUser.setZone(user.getZone());
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());
        List<Long> temp=user.getSos().getSos();
        temp.add(181L);
        user.getSos().setSos(temp);
        newUser.setSos(user.getSos());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        Authentication authentication=new UsernamePasswordAuthenticationToken(newUser.getEmail(),newUser.getPassword());
        String token = JwtProvider.generateToken(authentication);

        userRepo.save(newUser);

        return new AuthResponse(token,"Registered Successfully");
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody Login login) throws Exception {
        Authentication authentication=authenticate(login.getEmail(),login.getPassword());
        String token = JwtProvider.generateToken(authentication);
        return new AuthResponse(token,"Login Successfully");

    }
    private Authentication authenticate(String email, String password)  {
        UserDetails userDetails=customerUser.loadUserByUsername(email);
        if(userDetails==null){
            throw new BadCredentialsException("Invalid Email");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Password is incorrect");

        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}

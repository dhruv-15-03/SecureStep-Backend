package com.example.demo.Implementation;

import com.example.demo.Classes.*;
import com.example.demo.Controllers.UserController;
import com.example.demo.Database.FeedbackRepo;
import com.example.demo.Database.ReviewRepo;
import com.example.demo.Database.SosCallsRepo;
import com.example.demo.Database.UserRepo;
import com.example.demo.Methods.UserMethods;
import com.example.demo.config.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImplementation implements UserMethods {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    FeedbackRepo feedbackRepo;
    @Autowired
    SosCallsRepo sosCallsRepo;
    public User getFromJwt(String jwt){
        String email= JwtProvider.getEmailFromJwt(jwt);
        return userRepo.searchByEmail(email);
    }
    @Override
    public User update(User user,User user2) throws Exception {
        User old=userRepo.getReferenceById(user.getId());
        if(user2.getName()!=null){
            old.setName(user2.getName());
        }
        if(user2.getEmail()!=null){
            old.setEmail(user2.getEmail());
        }
        if(user2.getEmail()!=null){
            if(userRepo.searchByEmail(user2.getEmail())!=null&&!user2.getEmail().equals(old.getName())){
                throw new Exception("User already exist with this Username"+user2.getEmail());
            }
            old.setEmail(user2.getEmail());
        }
        if(user2.getGender()!=null)
            old.setGender(user2.getGender());
        if(user2.getProfile()!=null)
            old.setProfile(user2.getProfile());
        if(user2.getAge()!=null)
            old.setAge(user2.getAge());
        if(user2.getPassword()!=null)
            old.setPassword(user2.getPassword());
        if(user2.getSos()!=null)
            old.setSos(user2.getSos());
        userRepo.save(old);
        return old;
    }

    @Override
    public String delete(Integer id,User user) throws Exception {
        if(user.getId()!=id){
            throw new Exception("Not your id cant delete the user");
        }
        userRepo.deleteById(id);
        return "User Deleted Successfully";
    }

    @Override
    public User getFromId(Integer id) {
        return userRepo.getReferenceById(id);
    }

    @Override
    public Review newReview(User user, Review review) {
        Review review1=new Review();
        review1.setReview(review.getReview());
        review1.setUser(user);
        review1.setVideo(review.getVideo());
        review1.setImage(review.getImage());
        review1.setUpvote(0);
        review1.setId(review.getId());
        review1.setDownvote(0);
        reviewRepo.save(review1);
        return review1;
    }

    @Override
    public Feedback newFeedback(User user, Feedback feedback) {
        Feedback feedback1=new Feedback();
        feedback1.setUser(user);
        feedback1.setVideo(feedback.getVideo());
        feedback1.setImage(feedback.getImage());
        feedback1.setFeedback(feedback.getFeedback());
        feedback1.setId(feedback.getId());
        feedbackRepo.save(feedback1);
        return feedback1;
    }

    @Override
    public List<Review> getReviews(Integer id) {
        User user=userRepo.getReferenceById(id);
        return user.getReviews();
    }

    @Override
    public List<Feedback> getFeedback(Integer id) {
        User user=userRepo.getReferenceById(id);
        return user.getFeedbacks();
    }

    @Override
    public Location getLocation(Integer id) {
        User user=userRepo.getReferenceById(id);
        return user.getLocation();
    }

    @Override
    public String Alert(Integer id) {
        User user=userRepo.getReferenceById(id);
        SosCalls sosCalls=sosCallsRepo.searchFromUSerId(id);
        if(user.getZone().equalsIgnoreCase("RED")){
            if(sosCalls.getAlertCounts()==2)
                callSos(id);
            else {
                sosCalls.setAlertCounts(sosCalls.getAlertCounts()+1);
                return "Alert no " + sosCalls.getAlertCounts() + 1 + "Please beware,You are Currently in high risk zone!!!";
            }
        } else if (user.getZone().equalsIgnoreCase("Yellow")) {
            if(sosCalls.getAlertCounts()>3)
                callSos(id);
            else {
                sosCalls.setAlertCounts(sosCalls.getAlertCounts() + 1);
                return "Alert no " + sosCalls.getAlertCounts() + 1 + "Please take care,You are Currently in risk zone!!!";
            }

        }
        return null;
    }

    @Override
    public List<Long> callSos(Integer id) {
        User user=userRepo.getReferenceById(id);
        return user.getSos().getSos();
    }
}

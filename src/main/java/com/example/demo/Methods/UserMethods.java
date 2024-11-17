package com.example.demo.Methods;

import com.example.demo.Classes.Feedback;
import com.example.demo.Classes.Location;
import com.example.demo.Classes.Review;
import com.example.demo.Classes.User;

import java.util.List;

public interface UserMethods {

    public User update(User user,User user2) throws Exception;
    public String delete(Integer id,User user) throws Exception;
    public User getFromId(Integer id);
    public Review newReview(User user,Review review);
    public Feedback newFeedback(User user,Feedback feedback);
    public List<Review> getReviews(Integer id);
    public List<Feedback> getFeedback(Integer id);
    public Location getLocation(Integer id);
    public  String Alert(Integer id);
    public List<Long> callSos(Integer id);
}

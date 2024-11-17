package com.example.demo.Controllers;

import com.example.demo.Classes.Feedback;
import com.example.demo.Classes.Review;
import com.example.demo.Classes.User;
import com.example.demo.Classes.Location;
import com.example.demo.Database.FeedbackRepo;
import com.example.demo.Database.ReviewRepo;
import com.example.demo.Database.UserRepo;
import com.example.demo.Implementation.UserImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserImplementation userImplementation;
    @Autowired
    ReviewRepo reviewRepo;
    @Autowired
    FeedbackRepo feedbackRepo;
    @PutMapping("/update")
    public User update(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
        User main=userImplementation.getFromJwt(jwt);
        return userImplementation.update(main,user);
    }
    @DeleteMapping("/delete/{userId}")
    public String delete(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId) throws Exception {
        User user=userImplementation.getFromJwt(jwt);
        return userImplementation.delete(userId,user);
    }
    @GetMapping("/userAll")
    public List<User> getAll(){
        return userRepo.findAll();
    }
    @PostMapping("/location")
    public Location updateLocation(@RequestHeader("Authorization") String jwt,@RequestBody Location location ){
        User user=userImplementation.getFromJwt(jwt);
        user.setLocation(location);
        userRepo.save(user);
        return user.getLocation();
    }

    @PostMapping("/review")
    public Review review(@RequestHeader("Authorization") String jwt,@RequestBody Review review){
        return userImplementation.newReview(userImplementation.getFromJwt(jwt),review);
    }
    @PostMapping("/feedback")
    public Feedback feedback(@RequestHeader("Authorization") String jwt,@RequestBody Feedback feedback){
        return userImplementation.newFeedback(userImplementation.getFromJwt(jwt),feedback);
    }
    @GetMapping("/user/{userId}")
    public User get(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId){
        User user=userImplementation.getFromJwt(jwt);
        return userImplementation.getFromId(userId);
    }
    @GetMapping("/reviews")
    public List<Review> reviews(@RequestHeader("Authorization") String jwt){
        User user=userImplementation.getFromJwt(jwt);
        return userImplementation.getReviews(user.getId());
    }
    @GetMapping("/feedbacks")
    public List<Feedback> feedbacks(@RequestHeader("Authorization") String jwt){
        User user =userImplementation.getFromJwt(jwt);
        return userImplementation.getFeedback(user.getId());
    }
    @GetMapping("/alerts")
    public String alerts(@RequestHeader("Authorization") String jwt){
        User user=userImplementation.getFromJwt(jwt);
        return userImplementation.Alert(user.getId());
    }
    @GetMapping("/sosChoice")
    public List<Long> callSosChoice(@RequestHeader("Authorization") String jwt){
        User user=userImplementation.getFromJwt(jwt);
        return userImplementation.callSos(user.getId());
    }
}

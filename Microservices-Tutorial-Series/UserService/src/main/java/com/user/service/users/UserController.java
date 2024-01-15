package com.user.service.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.user.service.entity.User;
import com.user.service.serviceImpl.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
    
    int retryCount=1;
    @GetMapping("/{userId}")
   // @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
   // @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")//only user service started it will show whire label error after 3 retry attemps it will show dummy data
   // @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
    	logger.info("Retry count: {}", retryCount);
    	//retryCount++;
    	User user = userService.getUser(userId);
		return ResponseEntity.ok(user) ;
    	
    }
    
    //we need to have same return type as above method that is public ResponseEntity<User>
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
     logger.info("Fallback is executed because service is down : ", ex.getMessage());

      ex.printStackTrace();

      User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
      return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
  }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
    	List<User> allUser = userService.getAllUser();
		return ResponseEntity.ok(allUser);
    	
    }
    
    //rate limiter:This functionality allows limiting access to some service.Rate limiter makes service highly available by limiting the number of calls we would process in specific windows
    //this is mainly used for security(DOS ATTACK - SENDING A LOT OF REQUEST LIKE LAKH OF REQUEST/SECOND SO WE USE RATE LIMITER IT WILL LIMIT THE NUMBER OF CALLS PER REQUEST) and performance
    //WE CAN DO IN 3 WAYS REQUEST PER SECOND HOW MANY REQUEST PER SECOND,,, REQUEST PER MINUTE - HOW MANY REQUEST PER MINUTE,,,, |||LY REQUEST PER HOUR
}
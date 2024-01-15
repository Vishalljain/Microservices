package com.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.externalservice.RatingService;
import com.user.service.repository.UserRepository;
import com.user.service.serviceImpl.UserService;
import com.user.service.serviceImpl.UserServiceImpl;
import org.springframework.web.client.RestTemplate;
@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}
	
//	 @Autowired
//	    private RatingService ratingService;
//
//	   @Test
//	    void createRating() {
//	        Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("this is created using feign client").build();
//	        Rating newRating = ratingService.createRating(rating);
//	        System.out.println("new rating created");
//	    }
	
	
		
		@Autowired
		UserRepository uSERepository;
	

	    @Test
	    public void testGetUserInfo() {
	        // Create a mock RestTemplate
	        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

	        // Define the expected response from the rating service
	        User expectedUser = new User("47383621-b36c-4f69-b6b3-36b82fd8771f", "vishal", "vj@gmail.com", "working in bgsw", new ArrayList<>());
	        ResponseEntity<User> responseEntity = new ResponseEntity<>(expectedUser, HttpStatus.OK);

	        // Create an instance of UserService with the mock RestTemplate and rating service base URL
	        String ratingServiceBaseUrl = "http://localhost:8081/users";
	        UserService userService = new UserServiceImpl();

	        // Configure the mock RestTemplate to return the expected response
	        Mockito.when(restTemplate.getForEntity(ratingServiceBaseUrl, User.class))
	                .thenReturn(responseEntity);

	        // Perform the test
	        User user = userService.saveUser(expectedUser);

	        // Verify that the RestTemplate was called with the expected URL
	        Mockito.verify(restTemplate, Mockito.times(1)).getForEntity(ratingServiceBaseUrl, User.class);

	        // Verify that the result matches the expected user
	        assertEquals(expectedUser, user);
	    }
	}




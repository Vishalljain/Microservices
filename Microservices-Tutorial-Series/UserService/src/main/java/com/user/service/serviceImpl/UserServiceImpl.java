package com.user.service.serviceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.excemption.ResourceNotFoundException;
import com.user.service.externalservice.HotelService;
import com.user.service.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	private static final String GET_RATING = "http://RATING-SERVICE/ratings/users/";
	//private static final String GET_RATING = "http://localhost:8083/ratings/users/";


	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("here-----");
		String randomUserID = UUID.randomUUID().toString();
		user.setUserId(randomUserID);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

//	@Override
//	public User getUser(String userId) {
//		// TODO Auto-generated method stub
//		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on the server:"+userId));
//		//fetch rating of the above user using rating Micoservice
//		Rating[] ratingsOfUser = restTemplate.getForObject(GET_RATING+user.getUserId(),Rating[].class);
//		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
//		List<Rating> ratingList = ratings.stream().map(rating->{
//			ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//				Hotel hotel=forEntity.getBody();
//				rating.setHotel(hotel);
//				return rating;
//		}).collect(Collectors.toList());
//		
//		//In this example, the lambda expression is performing several actions (making an HTTP request, setting values) and needs an explicit return statement because the lambda is expected to return a Rating object. The return rating; statement is essential to return the modified Rating object as the result of the lambda expression.
//		
//		
//		user.setRatings(ratingList);
//		logger.info("{}",ratingList);
//		return  user;
//	}
	
	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on the server:"+userId));
		//fetch rating of the above user using rating Micoservice
		Rating[] ratingsOfUser = restTemplate.getForObject(GET_RATING+user.getUserId(),Rating[].class);
		List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
		System.out.println(ratings+"---");
		List<Rating> ratingList = ratings.stream().map(rating->{
			   Hotel hotel = hotelService.getHotel(rating.getHotelId());
				rating.setHotel(hotel);
				return rating;
		}).collect(Collectors.toList());
		
		//In this example, the lambda expression is performing several actions (making an HTTP request, setting values) and needs an explicit return statement because the lambda is expected to return a Rating object. The return rating; statement is essential to return the modified Rating object as the result of the lambda expression.
		
		
		user.setRatings(ratingList);
		logger.info("{}",ratingList);
		return  user;
	}

}

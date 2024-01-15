package com.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.user.service.entity.User;
import com.user.service.serviceImpl.UserService;
import com.user.service.users.UserController;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        // Create a User object to send in the request
        User user = new User("user123", "John Doe");

        // Mock the behavior of userService.saveUser to return a User
        User savedUser = new User("user123", "John Doe");
        Mockito.when(userService.saveUser(user)).thenReturn(savedUser);

        // Send a POST request to the /users endpoint
        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType("application/json")
                .content("{\"userId\":\"user123\",\"name\":\"John Doe\"}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // You can also add further assertions to check the response content, headers, etc.
    }
}




package com.example.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.rest.service.JwtService;
import com.example.rest.service.UserService;

@ExtendWith(MockitoExtension.class)
public class TokenControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private TokenController tokenController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.tokenController).build();
    }

    @Test
    void registration() throws Exception {
        this.mockMvc.perform(post("/api/v1/token/registration"))
            .andExpect(status().isCreated());
    }

    @Test
    void refresh() throws Exception {
        this.mockMvc.perform(post("/api/v1/token/refresh"))
            .andExpect(status().isCreated());
    }
}

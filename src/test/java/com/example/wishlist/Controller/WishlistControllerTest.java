package com.example.wishlist.Controller;

import com.example.wishlist.Service.WishlistService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;



@WebMvcTest(WishlistController.class)
    class WishlistControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
        private WishlistService coffeeOrderService;

        @BeforeEach
        void setUp() {
        }

        @AfterEach
        void tearDown() {
        }

        @Test
        void shouldShowLandingPage() throws Exception{
            mockMvc.perform(get("/landingPage"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("landingPage"));
        }

        @Test
        void login() {
        }

        @Test
        void testLogin() {
        }

        @Test
        void logout() {
        }

        @Test
        void getWishes() {
        }

        @Test
        void getProfile() {
        }

        @Test
        void showCreateUser() {
        }

        @Test
        void deleteWish() {
        }

        @Test
        void registerUser() {
        }

        @Test
        void addWish() {
        }

        @Test
        void addWishlist() {
        }

        @Test
        void saveWish() {
        }

        @Test
        void updateWish() {
        }
    }
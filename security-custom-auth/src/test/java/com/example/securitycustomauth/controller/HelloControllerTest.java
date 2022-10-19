package com.example.securitycustomauth.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class HelloControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void get_unauthorized_withAnonymousUser() throws Exception {
        mvc.perform(get("/api/v1/hello"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser(username = "Bob", roles = "USER")
    @Test
    void get_ok_withRoleUser() throws Exception {
        mvc.perform(get("/api/v1/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("GET Hello!"));
    }

    @Test
    void post_forbidden_withRoleUser() throws Exception {
        mvc.perform(post("/api/v1/hello")
                        .with(user("Bob").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    void postMethodTest_withRole_ADMIN1() throws Exception {
        mvc.perform(post("/api/v1/hello")
                        .with(user("Mike").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @WithUserDetails("Bob")
    @Test
    void postMethodTest_withRole_ADMIN() throws Exception {
        mvc.perform(post("/api/v1/hello"))
                .andExpect(status().isOk());
    }
}
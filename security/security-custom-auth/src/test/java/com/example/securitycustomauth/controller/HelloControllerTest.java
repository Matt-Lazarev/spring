package com.example.securitycustomauth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithAnonymousUser
    void get_unauthorized_withAnonymousUser() throws Exception {
        mvc.perform(get("/api/v1/hello"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "Bob", roles = "USER")
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
    void postMethodTest_withRoleAdmin() throws Exception {
        mvc.perform(post("/api/v1/hello")
                        .with(user("Mike").roles("ADMIN"))
                        .with(csrf()))
                .andExpect(status().isOk());
    }
}
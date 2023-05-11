package com.github.bolabolor.backend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @DirtiesContext
    @WithMockUser
    void expectSuccessfulLogin() throws Exception {
        mvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username = "testuser")
    void expectUnsuccessfullLogin_whenUserIsUnauthorized() throws Exception {
        mvc.perform(post("/api/users/login"))
                .andExpect(status().isForbidden());
    }


    @Test
    @DirtiesContext
    void expectFailedLogin() throws Exception {
        mvc.perform(post("/api/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void expectGetMe_whenLoggedIn() throws Exception {
        mvc.perform(get("/api/users/me"))
                .andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    void expectGetMe_whenNotLoggedIn() throws Exception {
        mvc.perform(get("/api/users/me").
                        contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(content().string("anonymousUser")).
                andExpect(status().isOk());
    }

    @Test
    @DirtiesContext
    @WithMockUser
    void expectLogout() throws Exception {
        mvc.perform(post("/api/users/logout").with(csrf()))
                .andExpect(status().isOk());
    }



}

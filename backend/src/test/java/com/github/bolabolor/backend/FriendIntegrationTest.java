package com.github.bolabolor.backend;
import com.cloudinary.Cloudinary;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bolabolor.backend.friend.FriendRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class FriendIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FriendRepository friendRepository;

    @MockBean
    Cloudinary cloudinary;

    @Test
    @WithMockUser
    void getAllFriends() throws Exception {
        mockMvc.perform(get("/api/friend"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                        ]
                        """));
    }

    @Test
    @WithMockUser
    void expectSuccessfulDelete() throws Exception {
        mockMvc.perform(delete("/api/friend/lukas")
                        .with(csrf()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/friend"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        []
                        """));
    }
}

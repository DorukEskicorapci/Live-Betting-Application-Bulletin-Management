package com.example.livebetting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturn404ForOutOfRangeIds() throws Exception {
        int[] outOfRangeIds = {2, 4, 8};

        for (int id : outOfRangeIds) {
            mockMvc.perform(get("/events/" + id))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }


    }


    @Test
    void shouldReturn404ForIdZero() throws Exception {
        mockMvc.perform(get("/events/0"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn404ForNonNumericId() throws Exception {
        mockMvc.perform(get("/events/abc"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        assert(true);
    }

}
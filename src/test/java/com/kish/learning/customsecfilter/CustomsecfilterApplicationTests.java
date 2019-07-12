package com.kish.learning.customsecfilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CustomsecfilterApplication.class},
        webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomsecfilterApplicationTests {


    @Autowired
    private MockMvc mockMvc;


    @Test
    public void greetingsApiPathTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void greetingsApi2PathTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api2"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }


    @Test
    public void greetingsHelloPathTest401() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void greetingsHelloPathTest() throws Exception {
        String oAuth = "Something";
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").header("OAuth",oAuth))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string("Hello Logged in user Guest_"+oAuth));
    }
}

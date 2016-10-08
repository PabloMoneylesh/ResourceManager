package com.hid.resourceManager.controllers;

import com.hid.resourceManager.ResorceManagerApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ResorceManagerApplication.class)
@WebAppConfiguration
public class AuthenticationTest {


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new Authentication())
                //.setHandlerExceptionResolvers(exceptionResolver())
                //.setValidator(validator())
                //.setViewResolvers(viewResolver())
                .build();
    }


    @Test
    public void home() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isOk());


        MockHttpServletResponse response = resultActions.andReturn().getResponse();

        resultActions.andExpect(forwardedUrl("home"));



    }

}
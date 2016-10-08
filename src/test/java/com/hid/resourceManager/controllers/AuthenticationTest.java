package com.hid.resourceManager.controllers;

import com.hid.resourceManager.ResorceManagerApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ResorceManagerApplication.class)
@WebAppConfiguration
public class AuthenticationTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }


    @Test
    public void nonAuthorizedShouldByRedirectedToLogin() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println("Resul:");
        System.out.println(response.getContentAsString());
        System.out.println(response.getRedirectedUrl());

        resultActions.andExpect(status().is(302));
        resultActions.andExpect(redirectedUrlPattern("**/login"));

    }

    @Test
    public void authorizedShouldByRedirectedToHome() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/").with(user("user"));

        ResultActions resultActions = mockMvc.perform(requestBuilder);


        resultActions.andExpect(status().is(200));
        resultActions.andExpect(redirectedUrl(null));

    }

}
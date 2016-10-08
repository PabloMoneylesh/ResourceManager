package com.hid.resourceManager.controllers;

import com.hid.resourceManager.ResorceManagerApplication;
import org.junit.Before;
import org.junit.Ignore;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ResorceManagerApplication.class)
@WebAppConfiguration
public class ResourceManagerTest {

    private MockMvc mockMvc;

    @Autowired
    ResourceManager resourceManager;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(resourceManager)
                .build();
    }

    @Test
    @Ignore
    public void downloadResourceBadRequestShoudReturn400() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0001");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is(400));


        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    @Test
    public void downloadResourceOK() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0001&class=ARTWORK");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType("application/pdf"));
        resultActions.andExpect(content().string("PN0001.PDF_11111111111111111111111111111"));


        //org.springframework.test.web.servlet.result.MockMvcResultMatchers.content().contentType()
        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

}
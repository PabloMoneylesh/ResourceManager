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
    public void downloadResourceBadRequestShoudReturn400() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0001");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is(400));


        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    @Test
    public void downloadResourceFileOK() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0001&class=ARTWORK");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType("application/pdf"));
        resultActions.andExpect(content().string("PN0001.PDF_11111111111111111111111111111"));

        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    @Test
    public void downloadResourceDBOK() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0001&class=LOGO");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentType("image/jpeg"));
        resultActions.andExpect(content().string("image file PN0001"));

        MockHttpServletResponse response = resultActions.andReturn().getResponse();
        System.out.println(response.getContentAsString());
    }

    @Test
    public void wrongRequest() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0001");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is(400));
        resultActions.andExpect(status().reason("Not Enough params"));

    }

    @Test
    public void noResourceFound() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0001&class=ABC");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is(400));
        resultActions.andExpect(status().reason("No resource found"));

    }

    @Test
    public void resourceReadingError() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getResource?key=PN0003&class=ARTWORK");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        resultActions.andExpect(status().is(500));

    }

}
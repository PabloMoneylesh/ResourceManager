package com.hid.resourceManager.services;

import com.hid.resourceManager.ResourceManagerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ResourceManagerApplication.class)
@WebAppConfiguration
public class ResourceManagerServiceTest {
    @Autowired
    ResourceManagerService resourceManagerService;

    @Test
    public void getResourceUrl() throws Exception {
        String resourceUrl = resourceManagerService.getResourceUrl("PN0001", "ARTWORK");
        assertEquals("PN0001.PDF", resourceUrl);
    }

    @Test
    public void getResourceContentType() throws Exception {
        String resourceContentType = resourceManagerService.getResourceContentType("ARTWORK");
        assertEquals("application/pdf", resourceContentType);

    }

    @Test
    public void getResourceGetterClass() throws Exception {
        String resourceGetterClass = resourceManagerService.getResourceGetterClass("PN0001", "ARTWORK");
        assertEquals("FileResource", resourceGetterClass);

    }

}
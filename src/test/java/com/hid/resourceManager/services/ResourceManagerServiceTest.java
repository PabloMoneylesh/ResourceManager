package com.hid.resourceManager.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Created by comp on 07.10.2016.
 */
public class ResourceManagerServiceTest {

    @Autowired
    ResourceManagerService resourceManagerService;

    @Test
    public void getResourceUrl() throws Exception {
        String resourceUrl = resourceManagerService.getResourceUrl("PN0001", "ARTWORK");
        assertEquals(resourceUrl, "PN0001.PDF");
    }

}
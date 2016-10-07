package com.hid.resourceManager.resourceGetters;

import com.hid.resourceManager.ResorceManagerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ResorceManagerApplication.class)
@WebAppConfiguration
public class FileResourceGetterTest {

    @Autowired
    FileResourceGetter resourceGetter;

    @Test
    public void getResource() throws Exception {
        InputStream resource = resourceGetter.getResource("PN0001.jpg");

        assert resource != null;
    }

}
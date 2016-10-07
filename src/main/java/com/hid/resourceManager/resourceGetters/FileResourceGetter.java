package com.hid.resourceManager.resourceGetters;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service("FileResource")
public class FileResourceGetter implements ResourceGetter {

    @Override
    public InputStream getResource(String url) throws IOException {


        return null;
    }
}

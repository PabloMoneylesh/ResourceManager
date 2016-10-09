package com.hid.resourceManager.resourceGetters;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * provides method for reading resource content from file
 */
@Service("FileResource")
public class FileResourceGetter implements ResourceGetter {

    @Override
    public InputStream getResource(String url) throws IOException {

        File resFile = new File(url);
        if (!resFile.exists()) {
            throw new IOException("No file resource found " + url);
        }
        FileInputStream fis;
        fis = new FileInputStream(resFile);

        return fis;
    }
}

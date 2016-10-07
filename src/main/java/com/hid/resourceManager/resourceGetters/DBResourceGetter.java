package com.hid.resourceManager.resourceGetters;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.SQLException;

@Service("DBResource")
public class DBResourceGetter implements ResourceGetter {


    @Override
    public InputStream getResource(String url) throws SQLException {


        return null;
    }
}

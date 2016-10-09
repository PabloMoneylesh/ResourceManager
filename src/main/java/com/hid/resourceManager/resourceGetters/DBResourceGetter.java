package com.hid.resourceManager.resourceGetters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * provides method for reading resource content from database
 */
@Service("DBResource")
public class DBResourceGetter implements ResourceGetter {


    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * request content for reading resource
     */
    private static final String READ_RESOURCE = "select resource_body from resource_storage where resource_url = ?";

    /**
     * send request to DB for provided resource url
     *
     * @param url
     * @return InputStream to read the resource body
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public InputStream getResource(String url) throws SQLException, IOException {
        Object[] args = new Object[1];
        args[0] = url;

        Blob resourceBody = jdbcTemplate.query(READ_RESOURCE, args, new ResultSetExtractor<Blob>() {
            @Override
            public Blob extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                if (resultSet.next()) {
                    return resultSet.getBlob(1);
                }
                return null;
            }
        });

        if (resourceBody == null) {
            new IOException("No resource found in DB " + url);
        }

        InputStream bis = resourceBody.getBinaryStream();

        return bis;
    }
}

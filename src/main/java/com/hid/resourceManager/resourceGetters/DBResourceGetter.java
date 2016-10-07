package com.hid.resourceManager.resourceGetters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service("DBResource")
public class DBResourceGetter implements ResourceGetter {


    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String READ_RESOURCE = "select resource_body from resource_storage where resource_url = ?";

    @Override
    public InputStream getResource(String url) throws SQLException {
        Object[] args = new Object[1];
        args[0] = url;

        Blob resourceBody = jdbcTemplate.query(READ_RESOURCE, args, new ResultSetExtractor<Blob>() {
            @Override
            public Blob extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getBlob(1);
            }
        });

        InputStream bis = resourceBody.getBinaryStream();

        return bis;
    }
}

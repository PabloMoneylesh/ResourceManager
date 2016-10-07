package com.hid.resourceManager.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ResourceManagerService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_RESOURCE_URL = "select resource_url\n" +
            " from resources\n" +
            " where resource_key=?\n" +
            " and resource_class=?";


    public String getResourceUrl(String resKey, String resClass) {

        Object[] args = new Object[2];
        args[0] = resKey;
        args[1] = resClass;

        String url;

        url = jdbcTemplate.query(SELECT_RESOURCE_URL, args, new ResultSetExtractor<String>() {
            @Override
            public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                resultSet.next();
                return resultSet.getString(1);
            }
        });

        return url;


    }


}

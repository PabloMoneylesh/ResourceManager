package com.hid.resourceManager.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ResourceManagerService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SELECT_RESOURCE_URL = "select resource_url\n" +
            " from resources\n" +
            " where resource_key=?\n" +
            " and resource_class=?";


    public String getResourceUrl(String resKey, String resClass) {

        return "";


    }


}

package com.hid.resourceManager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by comp on 06.10.2016.
 */
@Controller
public class ResourceManager {


    @RequestMapping(value = "/getResource")
    public void downloadResource(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String resKey = request.getParameter("key");
        String resClass = request.getParameter("class");


    }

}

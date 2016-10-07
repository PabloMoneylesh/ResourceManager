package com.hid.resourceManager.controllers;

import com.hid.resourceManager.resourceGetters.ResourceGetter;
import com.hid.resourceManager.resourceGetters.ResourceGettersFactory;
import com.hid.resourceManager.services.ResourceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
public class ResourceManager {


    @Autowired
    ResourceGettersFactory resourceGettersFactory;

    @Autowired
    ResourceManagerService resourceManagerService;


    @RequestMapping(value = "/getResource")
    public void downloadResource(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String resKey = request.getParameter("key");
        String resClass = request.getParameter("class");

        if (resKey == null || resClass == null) {
            response.sendError(400);
            return;
        }


        String resourceUrl = resourceManagerService.getResourceUrl(resKey, resClass);
        String resourceGetterClassName = resourceManagerService.getResourceGetterClass(resKey, resClass);
        ResourceGetter resourceGetter = resourceGettersFactory.getResourceGetter(resourceGetterClassName);

        String resourceContentType = resourceManagerService.getResourceContentType(resClass);
        InputStream resourceInputStream = resourceGetter.getResource(resourceUrl);

        response.setContentType(resourceContentType);
        response.setHeader("Content-disposition", "attachment; filename=" + resourceUrl);

        try {
            writeDataToResponse(resourceInputStream, response.getOutputStream());


        } catch (Exception e1) {
            e1.printStackTrace();
        }


    }

    private void writeDataToResponse(InputStream inputStream, OutputStream outputStream) {

        try {

            byte[] buffer = new byte[1024];

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



}

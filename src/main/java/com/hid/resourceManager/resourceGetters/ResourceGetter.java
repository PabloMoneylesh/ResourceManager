package com.hid.resourceManager.resourceGetters;

import java.io.InputStream;

/**
 * Created by comp on 06.10.2016.
 */
public interface ResourceGetter {

    InputStream getResource(String url) throws Exception;
}

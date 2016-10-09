package com.hid.resourceManager.resourceGetters;

import java.io.InputStream;

/**
 * defines the interface for different resource getters
 */
public interface ResourceGetter {

    InputStream getResource(String url) throws Exception;
}

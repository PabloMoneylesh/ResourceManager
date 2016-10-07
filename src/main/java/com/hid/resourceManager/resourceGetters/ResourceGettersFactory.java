package com.hid.resourceManager.resourceGetters;

/**
 * Created by comp on 07.10.2016.
 */
public interface ResourceGettersFactory {

    ResourceGetter getResourceGetter(String getterClass);
}

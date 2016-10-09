package com.hid.resourceManager.resourceGetters;

/**
 * factory that produces resources getters entities according to given resource class
 */
public interface ResourceGettersFactory {

    ResourceGetter getResourceGetter(String getterClass);
}

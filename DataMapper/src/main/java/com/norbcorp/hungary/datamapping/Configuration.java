package com.norbcorp.hungary.datamapping;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Configuration of DataMapper class.
 */
class Configuration {

    /**
     *  Parameter of eager loading of collections.
     *  Setters of collections are not invoked if it is false.
     */
    private boolean eagerLoadingAllowed=false;


    /**
     * Eager loading of collections. If it is set to true, it will invoke getters of collactions. Otherwise it will not.
     *
     * @return boolean of eagerLoadingAllowed parameter.
     */
    public boolean isEagerLoadingAllowed() {
        return eagerLoadingAllowed;
    }

    /**
     * If it is allowed, it invokes getter methods of Collections.
     *
     * @param eagerLoadingAllowed boolean value
     * @return the datamapper instance.
     */
    public Configuration setEagerLoadingAllowed(boolean eagerLoadingAllowed) {
        this.eagerLoadingAllowed=eagerLoadingAllowed;
        return this;
    }

}

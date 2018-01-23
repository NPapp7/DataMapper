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
     * Custom mappings of values.
     */
    private Map<Supplier,Consumer> mappings = new HashMap<Supplier, Consumer>();

    /**
     *  Parameter of eager loading of collections.
     *  Setters of collections are not invoked if it is false.
     */
    private boolean eagerLoadingAllowed=false;

    /**
     * Selected mapping type.
     */
    public MappingType selectedMappingType;

    /**
     * Type of the mapping.
     * <i>ONLY_CUSTOM</i>: only custom mappings.
     * <i>NORMAL</i>: only normal JavaBean method mapping.
     * <i>CUSTOM_AND_NORMAL</i>: combination of the previous two.
     */
    public enum MappingType {
        ONLY_CUSTOM, NORMAL, CUSTOM_AND_NORMAL
    }

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

   /* public void addMappings(Object from, Object to){
        Function getter;
        if(from instanceof Function)
            getter = (Function)from;
    }*/

    public <T> void addMappings(Supplier<T> supplier, Consumer<T> consumer){
        mappings.put(supplier, consumer);
    }

    public Map<Supplier, Consumer> getMappings() {
        return mappings;
    }

    public void setMappings(Map<Supplier, Consumer> mappings) {
        this.mappings = mappings;
    }
}

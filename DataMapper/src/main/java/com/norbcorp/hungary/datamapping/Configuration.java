package com.norbcorp.hungary.datamapping;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Logger;

/**
 * Configuration of DataMapper class.
 */
class Configuration {
    private static Logger logger = Logger.getLogger(Configuration.class.getName());

    /**
     * Custom mappings of values.
     */
    private static Map<Supplier,Consumer> mappings = new HashMap<>();

    /**
     *  Parameter of eager loading of collections.
     *  Setters of collections are not invoked if it is false.
     */
    private boolean eagerLoadingAllowed=false;

    /**
     * Selected mapping type.
     *
     * The default option is normal.
     */
    private MappingType selectedMappingType = MappingType.NORMAL;

    /**
     * Type of the mapping.
     * <i>ONLY_CUSTOM</i>: only custom mappings.
     * <i>NORMAL</i>: only normal JavaBean method mapping.
     * <i>CUSTOM_AND_NORMAL</i>: combination of the previous two.
     */
    public enum MappingType {
        CUSTOM, NORMAL, CUSTOM_AND_NORMAL
    }

    /**
     * Eager loading of collections. If it is set to true, it will invoke getters of collections. Otherwise it will not.
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

    public <T> void addMapping(Supplier<T> supplier, Consumer<T> consumer){
        mappings.put(supplier, consumer);
    }


    public Map<Supplier, Consumer> getMappings() {
        return mappings;
    }

    public void setMappings(Map<Supplier, Consumer> mappings) {
        this.mappings = mappings;
    }

    public MappingType getSelectedMappingType() {
        return selectedMappingType;
    }

    public void setSelectedMappingType(MappingType selectedMappingType) {
        this.selectedMappingType = selectedMappingType;
    }
}

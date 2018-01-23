package com.norbcorp.hungary.datamapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for back and forth mapping. It maps values between entities and data transfer objects.
 *
 * Created by nor on 2017.05.06..
 */
public class DataMapper{

    /**
     * Create a DataMapper instance with default configuration.
     */
    private DataMapper(){
        configuration = new Configuration();
    }

    /**
     * Static factory method for creating a new instance of DataMapper class.
     *
     * @return new DataMapper instance.
     */
    public static DataMapper newInstance(){
        return new DataMapper();
    }

    /**
     * Configuration of a DataMapper instance.
     */
    private Configuration configuration;

    /**
     * Map list of entities to another type of objects.
     *
     * @param fromList list of source object
     * @param to class of destination object.
     * @param <T> destination type
     * @param <F> source type
     * @return list of destination entities
     */
    public <T,F> List<T> mapList(List<F> fromList, Class<T> to) {
        List<T> dtos=new LinkedList<T>();
        try {
            for(F entity : fromList)
                dtos.add(map(entity,to.newInstance()));

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return dtos;
    }


    /**
     * Mapping of values of two different object.
     *
     * @param from is an object from which its values are copied.
     * @param to is an object to which the values of <i>from</i> object are copied.
     * @param <F> type of the source object
     * @param <T> type of the return object
     * @return with an object with T type containing the mapped values.
     */
    public <F,T> T map(F from,T to) {
        for(Method m : from.getClass().getDeclaredMethods()){
            if(m.getName().startsWith("get")){
                String name=m.getName().substring(3);
                for(Method methodOfEntity : to.getClass().getDeclaredMethods()){
                    if(methodOfEntity.getName().contains(name) && methodOfEntity.getName().startsWith("set")){
                        try {
                            if(configuration.isEagerLoadingAllowed() && (Collection.class.isAssignableFrom(m.getReturnType()))){
                                methodOfEntity.invoke(to, m.invoke(from));
                            } else if(!Collection.class.isAssignableFrom(m.getReturnType())) {
                                methodOfEntity.invoke(to, m.invoke(from));
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NullPointerException npe){
                            System.out.println("NullPointerException happened");
                        }
                    }
                }
            }
        }
        return to;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}

package com.norbcorp.hungary.datamapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Class for back and forth mapping. It maps values between entities and data transfer objects.
 *
 * Created by nor on 2017.05.06..
 */
public class DataMapper{

    /**
     *  Parameter of eager loading of collections.
     *  Setters of collections are not invoked if it is false.
     */
    private boolean eagerLoadingAllowed=false;

    private DataMapper(){}

    /**
     * Static factory method for creating a new instance of DataMapper class.
     *
     * @return new DataMapper instance.
     */
    public static DataMapper newInstance(){
        return new DataMapper();
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
    public DataMapper setEagerLoadingAllowed(boolean eagerLoadingAllowed) {
        this.eagerLoadingAllowed=eagerLoadingAllowed;
        return this;
    }

    /**
     * It copies values of an entity into the given dto. Getters and setters are used.
     *
     * Both dto and entity has to comply with JavaBean definition.
     *
     * @param entity which values will be copied
     * @param dto to which values of entity will be copied
     * @return the given DTO with values.
     */
    public  <DTO,ENTITY> DTO mappingEntityToDTO(ENTITY entity, DTO dto) {
        for(Method m : entity.getClass().getDeclaredMethods()){
                if(m.getName().startsWith("get")){
                    String name=m.getName().substring(3);
                    for(Method methodOfDTO : dto.getClass().getDeclaredMethods()){
                        if(methodOfDTO.getName().contains(name) && methodOfDTO.getName().startsWith("set")){
                            try {
                                if(eagerLoadingAllowed && (Collection.class.isAssignableFrom(m.getReturnType()))){
                                    methodOfDTO.invoke(dto, m.invoke(entity));
                                } else if(!Collection.class.isAssignableFrom(m.getReturnType())) {
                                    methodOfDTO.invoke(dto, m.invoke(entity));
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
        return dto;
    }


    /**
     * It copies values of a dto into the given entity. Getters and setters are used.
     *
     * Both dto and entity has to comply with JavaBean definition.
     *
     * @param entity which values will be copied
     * @return the given DTO with values.
     */
    public <DTO,ENTITY> ENTITY mappingDTOToEntity(DTO dto,ENTITY entity) {
        for(Method m : dto.getClass().getDeclaredMethods()){
            if(m.getName().startsWith("get")){
                String name=m.getName().substring(3);
                for(Method methodOfEntity : entity.getClass().getDeclaredMethods()){
                    if(methodOfEntity.getName().contains(name) && methodOfEntity.getName().startsWith("set")){
                        try {
                            if(eagerLoadingAllowed && (Collection.class.isAssignableFrom(m.getReturnType()))){
                                methodOfEntity.invoke(entity, m.invoke(dto));
                            } else if(!Collection.class.isAssignableFrom(m.getReturnType())) {
                                methodOfEntity.invoke(entity, m.invoke(dto));
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
        return entity;
    }

}
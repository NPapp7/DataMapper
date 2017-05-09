package com.norbcorp.hungary.datamapping;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by nor on 2017.05.06..
 */
public class testDataMapper {

    private SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD HH:mm");

    @Test
    public void testMappingEntityToDTO(){
        DTO dto=new DTO();
        Entity entity=new Entity();
        assertEquals(DataMapper.newInstance().mappingEntityToDTO(entity,dto).getName(),"Test");
        entity.setName("test 2");
        assertEquals(DataMapper.newInstance().mappingEntityToDTO(entity,dto).getName(),"test 2");

        entity.setAge(123);
        assertEquals(DataMapper.newInstance().mappingEntityToDTO(entity, dto).getAge().intValue(),123);

        entity.setAge(200);
        assertEquals(DataMapper.newInstance().mappingEntityToDTO(entity, dto).getAge().intValue(),200);
       try {
            entity.setDateOfRegistration(sdf.parse("2017-05-06 20:00"));
            Date date=sdf.parse("2017-05-06 20:00");
            assertEquals(DataMapper.newInstance().mappingEntityToDTO(entity,dto).getDateOfRegistration(),date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DTO dto1 = new DTO();
        assertNull(DataMapper.newInstance().setEagerLoadingAllowed(false).mappingEntityToDTO(entity,dto1).getStrings());
        DTO dto2 = new DTO();
        Assert.assertNotNull(DataMapper.newInstance().setEagerLoadingAllowed(true).mappingEntityToDTO(entity,dto2).getStrings());
    }

    @Test
    public void testMappingDTOToEntity(){
        DTO  dto = new DTO();
        Entity entity = new Entity();
        entity.setName(null);
        entity.setAge(null);
        entity.setDateOfRegistration(null);
        entity.setDescription(null);
        entity.setStrings(null);

        dto.setName("Test");
        dto.setAge(10);
        dto.setStrings(new LinkedList<String>());
        assertEquals(DataMapper.newInstance().setEagerLoadingAllowed(false).mappingDTOToEntity(dto, entity).getName(),"Test");
        assertEquals(DataMapper.newInstance().setEagerLoadingAllowed(false).mappingDTOToEntity(dto, entity).getAge().intValue(),10);
        assertNull(DataMapper.newInstance().setEagerLoadingAllowed(false).mappingDTOToEntity(dto, entity).getStrings());
        assertNotNull(DataMapper.newInstance().setEagerLoadingAllowed(true).mappingDTOToEntity(dto, entity));
    }
}


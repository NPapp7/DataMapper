package com.norbcorp.hungary.datamapping;

import com.norbcorp.hungary.datamapping.util.DTO;
import com.norbcorp.hungary.datamapping.util.Entity;
import org.junit.Test;

import java.sql.Date;
import java.time.Instant;

public class DataMapperConfigurationTest {



    @Test
    public void testConfiguration(){
        DTO dto = new DTO();
        Entity entity = new Entity();
        entity.setName("Test");
        entity.setDateOfRegistration(Date.from(Instant.now()));
        DataMapper dataMapper = DataMapper.newInstance();

        dataMapper.getConfiguration().addMappings(entity::getName, dto::setName);
        dataMapper.getConfiguration().addMappings(entity::getDateOfRegistration, dto::setDateOfRegistration);
    }
}

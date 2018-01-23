package com.norbcorp.hungary.datamapping;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import java.sql.Date;
import java.time.Instant;

import org.junit.Test;

import com.norbcorp.hungary.datamapping.util.DTO;
import com.norbcorp.hungary.datamapping.util.Entity;

public class DataMapperConfigurationTest {



    @Test
    public void testConfiguration(){
        DTO dto = new DTO();
        Entity entity = new Entity();
        entity.setName("Test");
        entity.setDateOfRegistration(Date.from(Instant.now()));
        DataMapper dataMapper = DataMapper.newInstance();
        
        assertThat(dataMapper.getConfiguration().isEagerLoadingAllowed(),is(equalTo(false)));
        dataMapper.getConfiguration().setEagerLoadingAllowed(true);
        assertThat(dataMapper.getConfiguration().isEagerLoadingAllowed(), is(equalTo(true)));
    }
}

package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.impl.DeviceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    public static final Long ID = 12345L;

    @Mock
    private DeviceRepository repository;

    @InjectMocks
    private DeviceServiceImpl service;

    private Device testEntity;

    @BeforeEach
    void setup() {
        testEntity = Device.builder().id(ID).systemName("machine")
                .type(DeviceType.builder().typeName("MAC").build())
                .build();
    }

    @Test
    void getEntityFromService() {
        when(repository.findById(ID)).thenReturn(Optional.of(testEntity));
        Optional<Device> entityOpt = service.findOne(ID);
        assertTrue(entityOpt.isPresent());
        assertEquals(testEntity.getId(), entityOpt.get().getId());
        assertEquals(testEntity.getType(), entityOpt.get().getType());
        assertEquals(testEntity.getSystemName(), entityOpt.get().getSystemName());
    }

    @Test
    void saveEntityThruService() {
        when(repository.save(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity, service.save(testEntity));
    }

    @Test
    void deleteEntityThruService() {
        doNothing().when(repository).deleteById(ID);
        service.delete(ID);
        Mockito.verify(repository, times(1)).deleteById(ID);
    }
}

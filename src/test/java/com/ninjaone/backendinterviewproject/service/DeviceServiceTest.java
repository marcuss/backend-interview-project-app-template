package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
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
        testEntity = new Device(ID, "device", "machine");
    }

    @Test
    void getEntityFromService() {
        when(repository.findById(ID)).thenReturn(Optional.of(testEntity));
        Optional<Device> entityOpt = service.getEntity(ID);
        assertTrue(entityOpt.isPresent());
        assertEquals(testEntity.getId(), entityOpt.get().getId());
        assertEquals(testEntity.getDeviceType(), entityOpt.get().getDeviceType());
        assertEquals(testEntity.getSystemName(), entityOpt.get().getSystemName());
    }

    @Test
    void saveEntityThruService() {
        when(repository.save(testEntity)).thenReturn(testEntity);
        assertEquals(testEntity, service.saveEntity(testEntity));
    }

    @Test
    void deleteEntityThruService() {
        doNothing().when(repository).deleteById(ID);
        service.deleteEntity(ID);
        Mockito.verify(repository, times(1)).deleteById(ID);
    }
}

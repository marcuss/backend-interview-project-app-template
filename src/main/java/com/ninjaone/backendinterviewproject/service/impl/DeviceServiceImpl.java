package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device saveEntity(Device sample){
        return deviceRepository.save(sample);
    }

    @Override
    public Optional<Device> getEntity(String id){
        return deviceRepository.findById(id);
    }

    @Override
    public void deleteEntity(String id) {
        deviceRepository.deleteById(id);
    }
}

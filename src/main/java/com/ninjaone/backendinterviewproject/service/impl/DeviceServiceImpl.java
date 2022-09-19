package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Device}.
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device save(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findOne(Long id) {
        return deviceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Device> findBySystemName(String systemName) {
        return deviceRepository.findBySystemName(systemName);
    }

    @Override
    public void delete(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return deviceRepository.existsById(id);
    }
}

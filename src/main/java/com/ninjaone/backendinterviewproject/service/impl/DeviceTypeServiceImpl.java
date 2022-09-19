package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.DeviceTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link DeviceType}.
 */
@Service
@Transactional
public class DeviceTypeServiceImpl implements DeviceTypeService {

    private final DeviceTypeRepository deviceTypeRepository;


    public DeviceTypeServiceImpl(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @Override
    public DeviceType save(DeviceType deviceType) {
        return deviceTypeRepository.save(deviceType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DeviceType> findAll() {
        return deviceTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceType> findOne(Long id) {
        return deviceTypeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DeviceType> findOneByName(String typeName) {
        return deviceTypeRepository.findByTypeName(typeName);
    }

    @Override
    public void delete(Long id) {
        deviceTypeRepository.deleteById(id);
    }
}

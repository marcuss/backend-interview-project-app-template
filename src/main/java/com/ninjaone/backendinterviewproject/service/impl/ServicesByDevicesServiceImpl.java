package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.ServicesByDevicesRepository;
import com.ninjaone.backendinterviewproject.model.ServicesByDevices;
import com.ninjaone.backendinterviewproject.service.ServicesByDevicesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ServicesByDevices}.
 */
@Service
@Transactional
public class ServicesByDevicesServiceImpl implements ServicesByDevicesService {
    private final ServicesByDevicesRepository servicesByDevicesRepository;

    public ServicesByDevicesServiceImpl(ServicesByDevicesRepository servicesByDevicesRepository) {
        this.servicesByDevicesRepository = servicesByDevicesRepository;
    }

    @Override
    public ServicesByDevices save(ServicesByDevices servicesByDevices) {
        return servicesByDevicesRepository.save(servicesByDevices);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServicesByDevices> findAll() {
        return servicesByDevicesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServicesByDevices> findOne(Long id) {
        return servicesByDevicesRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        servicesByDevicesRepository.deleteById(id);
    }

}

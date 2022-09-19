package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Service}.
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Service> findOne(Long id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Optional<Service> findByServiceName(String serviceName) {
        return serviceRepository.findByServiceName(serviceName);
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return serviceRepository.existsById(id);
    }
}

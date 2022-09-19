package com.ninjaone.backendinterviewproject.service.impl;

import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.database.ServiceCostRepository;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.model.ServiceCost;
import com.ninjaone.backendinterviewproject.service.ServiceCostService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link ServiceCost}.
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceCostServiceImpl implements ServiceCostService {

    private final ServiceCostRepository repository;

    private final DeviceTypeRepository deviceTypeRepository;

    private DeviceType TYPE_ANY = null;

    public ServiceCostServiceImpl(ServiceCostRepository repository, DeviceTypeRepository deviceTypeRepository) {
        this.repository = repository;
        this.deviceTypeRepository = deviceTypeRepository;
    }

    @Override
    public ServiceCost save(ServiceCost serviceCost) {
        return repository.save(serviceCost);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ServiceCost> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceCost> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BigDecimal calculateCost(Service service, DeviceType type) {
        return repository.findByServiceAndDeviceType(service, type)
                .map(ServiceCost::getCost)
                .orElseGet(() -> repository.findByServiceAndDeviceType(service, getTypeAny())
                        .map(ServiceCost::getCost)
                        .orElseGet(() -> new BigDecimal(0))
                );
    }

    private DeviceType getTypeAny() {
        if (TYPE_ANY == null) {
            synchronized (DeviceType.class) {
                if (TYPE_ANY == null) {
                    return deviceTypeRepository.findByTypeName("ANY")
                            .orElseGet(() -> deviceTypeRepository.save(DeviceType.builder().typeName("ANY").build()));
                }
            }
        }
        return TYPE_ANY;
    }
}

package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.model.ServiceCost;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ServiceCost}.
 */
public interface ServiceCostService {
    /**
     * Save a serviceCost.
     *
     * @param serviceCost the entity to save.
     * @return the persisted entity.
     */
    ServiceCost save(ServiceCost serviceCost);

    /**
     * Get all the serviceCosts.
     *
     * @return the list of entities.
     */
    List<ServiceCost> findAll();

    /**
     * Get the "id" serviceCost.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceCost> findOne(Long id);

    /**
     * Delete the "id" serviceCost.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    BigDecimal calculateCost(Service service, DeviceType type);
}

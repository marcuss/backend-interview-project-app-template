package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.ServicesByDevices;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link ServicesByDevices}.
 */
public interface ServicesByDevicesService {
    /**
     * Save a servicesByDevices.
     *
     * @param servicesByDevices the entity to save.
     * @return the persisted entity.
     */
    ServicesByDevices save(ServicesByDevices servicesByDevices);

    /**
     * Get all the servicesByDevices.
     *
     * @return the list of entities.
     */
    List<ServicesByDevices> findAll();

    /**
     * Get the "id" servicesByDevices.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServicesByDevices> findOne(Long id);

    /**
     * Delete the "id" servicesByDevices.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

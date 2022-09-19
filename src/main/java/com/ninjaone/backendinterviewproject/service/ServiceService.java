package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Service}.
 */
public interface ServiceService {
    /**
     * Save a service.
     *
     * @param service the entity to save.
     * @return the persisted entity.
     */
    Service save(Service service);

    /**
     * Get all the services.
     *
     * @return the list of entities.
     */
    List<Service> findAll();

    /**
     * Get the "id" service.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Service> findOne(Long id);

    /**
     * Delete the "id" service.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    boolean existsById(Long id);
}

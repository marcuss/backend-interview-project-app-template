package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Device;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Device}.
 */
public interface DeviceService {
    /**
     * Save a device.
     *
     * @param device the entity to save.
     * @return the persisted entity.
     */
    Device save(Device device);

    /**
     * Partially updates a device.
     *
     * @param device the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Device> partialUpdate(Device device);

    /**
     * Get all the devices.
     *
     * @return the list of entities.
     */
    List<Device> findAll();

    /**
     * Get the "id" device.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Device> findOne(Long id);

    /**
     * Delete the "id" device.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    boolean existsById(Long id);
}

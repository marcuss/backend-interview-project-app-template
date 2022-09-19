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

    /**
     * Get the device by systemName.
     *
     * @param systemName the systemName of the entity.
     * @return the entity.
     */
    Optional<Device> findBySystemName(String systemName);
}

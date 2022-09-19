package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.DeviceType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link DeviceType}.
 */
public interface DeviceTypeService {
    /**
     * Save a deviceType.
     *
     * @param deviceType the entity to save.
     * @return the persisted entity.
     */
    DeviceType save(DeviceType deviceType);

    /**
     * Get all the deviceTypes.
     *
     * @return the list of entities.
     */
    List<DeviceType> findAll();

    /**
     * Get the "id" deviceType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DeviceType> findOne(Long id);

    @Transactional(readOnly = true)
    Optional<DeviceType> findOneByName(String typeName);

    /**
     * Delete the "id" deviceType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    /**
     * Retrieves an entity by its systemName.
     *
     * @param systemName must not be {@literal null}.
     * @return the entity with the given systemName or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal systemName} is {@literal null}.
     */
    Optional<Device> findBySystemName(String systemName);
}

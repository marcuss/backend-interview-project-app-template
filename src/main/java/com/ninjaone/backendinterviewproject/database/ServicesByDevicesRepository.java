package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.ServicesByDevices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface ServicesByDevicesRepository extends JpaRepository<ServicesByDevices, Long> {
}

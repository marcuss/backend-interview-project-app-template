package com.ninjaone.backendinterviewproject.database;

import com.ninjaone.backendinterviewproject.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<Service> findByServiceName(String serviceName);
}

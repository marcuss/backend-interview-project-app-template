package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.model.Device;

import java.util.Optional;

public interface DeviceService {

    Device saveEntity(Device sample);

    Optional<Device> getEntity(String id);

    void deleteEntity(String id);
}

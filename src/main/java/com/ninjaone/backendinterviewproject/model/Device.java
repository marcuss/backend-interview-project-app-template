package com.ninjaone.backendinterviewproject.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Device {
    @Id
    private String id;
    private String systemName;
    private String deviceType;

    public Device() {
    }

    public Device(String id, String systemName, String deviceType) {
        this.id = id;
        this.systemName = systemName;
        this.deviceType = deviceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
}

package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A DeviceType.
 */
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "device_type")
public class DeviceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type_name", unique = true)
    private String typeName;

    @OneToMany(mappedBy = "type")
    @JsonIgnoreProperties(value = { "type", "customerDevices" }, allowSetters = true)
    private Set<Device> ids = new HashSet<>();

    @OneToMany(mappedBy = "deviceType")
    @JsonIgnoreProperties(value = { "service", "deviceType", "id" }, allowSetters = true)
    private Set<ServiceCost> deviceTypeCosts = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public DeviceType id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public DeviceType typeName(String typeName) {
        this.setTypeName(typeName);
        return this;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<Device> getIds() {
        return this.ids;
    }

    public void setIds(Set<Device> devices) {
        if (this.ids != null) {
            this.ids.forEach(i -> i.setType(null));
        }
        if (devices != null) {
            devices.forEach(i -> i.setType(this));
        }
        this.ids = devices;
    }

    public DeviceType ids(Set<Device> devices) {
        this.setIds(devices);
        return this;
    }

    public DeviceType addId(Device device) {
        this.ids.add(device);
        device.setType(this);
        return this;
    }

    public DeviceType removeId(Device device) {
        this.ids.remove(device);
        device.setType(null);
        return this;
    }

    public Set<ServiceCost> getDeviceTypeCosts() {
        return this.deviceTypeCosts;
    }

    public void setDeviceTypeCosts(Set<ServiceCost> serviceCosts) {
        if (this.deviceTypeCosts != null) {
            this.deviceTypeCosts.forEach(i -> i.setDeviceType(null));
        }
        if (serviceCosts != null) {
            serviceCosts.forEach(i -> i.setDeviceType(this));
        }
        this.deviceTypeCosts = serviceCosts;
    }

    public DeviceType deviceTypeCosts(Set<ServiceCost> serviceCosts) {
        this.setDeviceTypeCosts(serviceCosts);
        return this;
    }

    public DeviceType addDeviceTypeCosts(ServiceCost serviceCost) {
        this.deviceTypeCosts.add(serviceCost);
        serviceCost.setDeviceType(this);
        return this;
    }

    public DeviceType removeDeviceTypeCosts(ServiceCost serviceCost) {
        this.deviceTypeCosts.remove(serviceCost);
        serviceCost.setDeviceType(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceType)) {
            return false;
        }
        return id != null && id.equals(((DeviceType) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "DeviceType{" +
            "id=" + getId() +
            ", typeName='" + getTypeName() + "'" +
            "}";
    }
}

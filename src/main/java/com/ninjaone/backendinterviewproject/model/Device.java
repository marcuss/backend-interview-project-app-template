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
 * A Device.
 */
@Entity
@Table(name = "device")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"customerDevices"})
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "system_name")
    private String systemName;

    @Column(name = "customer")
    private String customer;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ids", "id", "deviceTypeCosts" }, allowSetters = true)
    private DeviceType type;

    @OneToMany(mappedBy = "device")
    private Set<ServicesByDevices> customerDevices = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Device id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public Device systemName(String systemName) {
        this.setSystemName(systemName);
        return this;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getCustomer() {
        return this.customer;
    }

    public Device customer(String customer) {
        this.setCustomer(customer);
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public DeviceType getType() {
        return this.type;
    }

    public void setType(DeviceType deviceType) {
        this.type = deviceType;
    }

    public Device type(DeviceType deviceType) {
        this.setType(deviceType);
        return this;
    }

    public Set<ServicesByDevices> getCustomerDevices() {
        return this.customerDevices;
    }

    public void setCustomerDevices(Set<ServicesByDevices> servicesByDevices) {
        if (this.customerDevices != null) {
            this.customerDevices.forEach(i -> i.setDevice(null));
        }
        if (servicesByDevices != null) {
            servicesByDevices.forEach(i -> i.setDevice(this));
        }
        this.customerDevices = servicesByDevices;
    }

    public Device customerDevices(Set<ServicesByDevices> servicesByDevices) {
        this.setCustomerDevices(servicesByDevices);
        return this;
    }

    public Device addCustomerDevice(ServicesByDevices servicesByDevices) {
        this.customerDevices.add(servicesByDevices);
        servicesByDevices.setDevice(this);
        return this;
    }

    public Device removeCustomerDevice(ServicesByDevices servicesByDevices) {
        this.customerDevices.remove(servicesByDevices);
        servicesByDevices.setDevice(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        return id != null && id.equals(((Device) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Device{" +
            "id=" + getId() +
            ", systemName='" + getSystemName() + "'" +
            ", customer='" + getCustomer() + "'" +
            "}";
    }
}

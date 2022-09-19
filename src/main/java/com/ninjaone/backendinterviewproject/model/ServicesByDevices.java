package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;

/**
 * A ServicesByDevices.
 */
@Entity
@Table(name = "services_by_devices")
public class ServicesByDevices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer")
    private String customer;

    @ManyToOne
    @JsonIgnoreProperties(value = { "serviceCosts", "customerServices" }, allowSetters = true)
    private Service service;

    @ManyToOne
    @JsonIgnoreProperties(value = { "type", "customerDevices" }, allowSetters = true)
    private Device device;

    public Long getId() {
        return this.id;
    }

    public ServicesByDevices id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return this.customer;
    }

    public ServicesByDevices customer(String customer) {
        this.setCustomer(customer);
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServicesByDevices service(Service service) {
        this.setService(service);
        return this;
    }

    public Device getDevice() {
        return this.device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public ServicesByDevices device(Device device) {
        this.setDevice(device);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServicesByDevices)) {
            return false;
        }
        return id != null && id.equals(((ServicesByDevices) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ServicesByDevices{" +
            "id=" + getId() +
            ", customer='" + getCustomer() + "'" +
            "}";
    }
}

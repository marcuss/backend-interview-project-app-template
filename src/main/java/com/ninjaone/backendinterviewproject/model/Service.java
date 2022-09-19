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
 * A Service.
 */
@Entity
@Table(name = "service")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "serviceCosts", "customerServices" })
public class Service implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "service_name", unique = true)
    private String serviceName;

    @OneToMany(mappedBy = "service")
    @JsonIgnoreProperties(value = { "service", "deviceType" }, allowSetters = true)
    private Set<ServiceCost> serviceCosts = new HashSet<>();

    @OneToMany(mappedBy = "service")
    @JsonIgnoreProperties(value = { "service", "device" }, allowSetters = true)
    private Set<ServicesByDevices> customerServices = new HashSet<>();

    public Long getId() {
        return this.id;
    }

    public Service id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public Service serviceName(String serviceName) {
        this.setServiceName(serviceName);
        return this;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<ServiceCost> getServiceCosts() {
        return this.serviceCosts;
    }

    public void setServiceCosts(Set<ServiceCost> serviceCosts) {
        if (this.serviceCosts != null) {
            this.serviceCosts.forEach(i -> i.setService(null));
        }
        if (serviceCosts != null) {
            serviceCosts.forEach(i -> i.setService(this));
        }
        this.serviceCosts = serviceCosts;
    }

    public Service serviceCosts(Set<ServiceCost> serviceCosts) {
        this.setServiceCosts(serviceCosts);
        return this;
    }

    public Service addServiceCosts(ServiceCost serviceCost) {
        this.serviceCosts.add(serviceCost);
        serviceCost.setService(this);
        return this;
    }

    public Service removeServiceCosts(ServiceCost serviceCost) {
        this.serviceCosts.remove(serviceCost);
        serviceCost.setService(null);
        return this;
    }

    public Set<ServicesByDevices> getCustomerServices() {
        return this.customerServices;
    }

    public void setCustomerServices(Set<ServicesByDevices> servicesByDevices) {
        if (this.customerServices != null) {
            this.customerServices.forEach(i -> i.setService(null));
        }
        if (servicesByDevices != null) {
            servicesByDevices.forEach(i -> i.setService(this));
        }
        this.customerServices = servicesByDevices;
    }

    public Service customerServices(Set<ServicesByDevices> servicesByDevices) {
        this.setCustomerServices(servicesByDevices);
        return this;
    }

    public Service addCustomerService(ServicesByDevices servicesByDevices) {
        this.customerServices.add(servicesByDevices);
        servicesByDevices.setService(this);
        return this;
    }

    public Service removeCustomerService(ServicesByDevices servicesByDevices) {
        this.customerServices.remove(servicesByDevices);
        servicesByDevices.setService(null);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Service)) {
            return false;
        }
        return id != null && id.equals(((Service) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Service{" +
            "id=" + getId() +
            ", serviceName='" + getServiceName() + "'" +
            "}";
    }
}

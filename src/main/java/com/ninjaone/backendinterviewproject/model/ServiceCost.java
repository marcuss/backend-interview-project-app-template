package com.ninjaone.backendinterviewproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 * A ServiceCost.
 */
@Entity
@Table(name = "service_cost")
public class ServiceCost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cost", precision = 21, scale = 2)
    private BigDecimal cost;

    @ManyToOne
    @JsonIgnoreProperties(value = { "serviceCosts", "customerServices" }, allowSetters = true)
    private Service service;

    @ManyToOne
    @JsonIgnoreProperties(value = { "ids", "deviceTypeCosts" }, allowSetters = true)
    private DeviceType deviceType;

     public Long getId() {
        return this.id;
    }

    public ServiceCost id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public ServiceCost cost(BigDecimal cost) {
        this.setCost(cost);
        return this;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceCost service(Service service) {
        this.setService(service);
        return this;
    }

    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public ServiceCost deviceType(DeviceType deviceType) {
        this.setDeviceType(deviceType);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceCost)) {
            return false;
        }
        return id != null && id.equals(((ServiceCost) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ServiceCost{" +
            "id=" + getId() +
            ", cost=" + getCost() +
            "}";
    }
}

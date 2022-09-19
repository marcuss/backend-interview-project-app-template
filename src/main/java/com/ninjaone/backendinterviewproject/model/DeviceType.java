package com.ninjaone.backendinterviewproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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

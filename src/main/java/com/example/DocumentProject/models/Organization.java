package com.example.DocumentProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @Column(name = "id_organization")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty(message = "Необходимо ввести название организации")
    @Column(name = "name_organization")
    private String name;

    @NotEmpty(message = "Необходимо ввести физический адрес организации")
    @Column(name = "physical_address_organization")
    private String physical_address;

    @NotEmpty(message = "Необходимо ввести юридический адрес организации")
    @Column(name = "legal_address_organization")
    private String legal_address;

    @NotEmpty(message = "Необходимо ввести руководителя организации")
    @Column(name = "supervisor_organization")
    private String supervisor;

    public Organization() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id_organization) {
        this.id = id_organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_organization) {
        this.name = name_organization;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public void setPhysical_address(String physical_address_organization) {
        this.physical_address = physical_address_organization;
    }

    public String getLegal_address() {
        return legal_address;
    }

    public void setLegal_address(String legal_address_organization) {
        this.legal_address = legal_address_organization;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor_organization) {
        this.supervisor = supervisor_organization;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id_organization=" + id +
                ", name_organization='" + name + '\'' +
                ", physical_address_organization='" + physical_address + '\'' +
                ", legal_address_organization='" + legal_address + '\'' +
                ", supervisor_organization='" + supervisor + '\'' +
                '}';
    }
    private Organization(Organization.Builder builder){
        setId(builder.id);
        setName(builder.name);
        setPhysical_address(builder.physical_address);
        setLegal_address(builder.legal_address);
        setSupervisor(builder.supervisor);
    }
    public static final class Builder{
        private UUID id;
        private String name;
        private String physical_address;
        private String legal_address;
        private String supervisor;

        public Builder buildId(UUID val){
            id = val;
            return this;
        }
        public Builder buildName(String val){
            name = val;
            return this;
        }
        public Builder buildPhysical_address(String val){
            physical_address = val;
            return this;
        }
        public Builder buildLegal_address(String val){
            legal_address = val;
            return this;
        }
        public Builder buildSupervisor(String val){
            supervisor = val;
            return this;
        }
        public Organization build(){
            return new Organization(this);
        }
    }
}

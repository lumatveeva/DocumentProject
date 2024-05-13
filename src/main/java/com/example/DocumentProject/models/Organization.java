package com.example.DocumentProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @Column(name = "id_organization")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_organization;

    @NotEmpty(message = "Необходимо ввести название организации")
    @Column(name = "name_organization")
    private String name_organization;

    @NotEmpty(message = "Необходимо ввести физический адрес организации")
    @Column(name = "physical_address_organization")
    private String physical_address_organization;

    @NotEmpty(message = "Необходимо ввести юридический адрес организации")
    @Column(name = "legal_address_organization")
    private String legal_address_organization;

    @NotEmpty(message = "Необходимо ввести руководителя организации")
    @Column(name = "supervisor_organization")
    private String supervisor_organization;

    public Organization() {
    }

    public int getId_organization() {
        return id_organization;
    }

    public void setId_organization(int id_organization) {
        this.id_organization = id_organization;
    }

    public String getName_organization() {
        return name_organization;
    }

    public void setName_organization(String name_organization) {
        this.name_organization = name_organization;
    }

    public String getPhysical_address_organization() {
        return physical_address_organization;
    }

    public void setPhysical_address_organization(String physical_address_organization) {
        this.physical_address_organization = physical_address_organization;
    }

    public String getLegal_address_organization() {
        return legal_address_organization;
    }

    public void setLegal_address_organization(String legal_address_organization) {
        this.legal_address_organization = legal_address_organization;
    }

    public String getSupervisor_organization() {
        return supervisor_organization;
    }

    public void setSupervisor_organization(String supervisor_organization) {
        this.supervisor_organization = supervisor_organization;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id_organization=" + id_organization +
                ", name_organization='" + name_organization + '\'' +
                ", physical_address_organization='" + physical_address_organization + '\'' +
                ", legal_address_organization='" + legal_address_organization + '\'' +
                ", supervisor_organization='" + supervisor_organization + '\'' +
                '}';
    }
}

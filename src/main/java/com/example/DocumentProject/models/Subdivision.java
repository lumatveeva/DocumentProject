package com.example.DocumentProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "Subdivision")
public class Subdivision {
    @Id
    @Column(name = "id_subdivision")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_subdivision;

    @Column(name = "name_subdivision")
    @NotEmpty(message = "Необходимо ввести название подразделения")
    private String name_subdivision;

    @Column(name = "сontact_details_subdivision")
    @NotEmpty(message = "Необходимо ввести контактные данные подразделения")
    private String сontact_details_subdivision;

    @Column(name = "supervisor_subdivision")
    @NotEmpty(message = "Необходимо ввести руководителя подразделения")
    private String supervisor_subdivision;

    @ManyToOne()
    @JoinColumn(name = "organization_id", referencedColumnName = "id_organization")
    private Organization organization_id;

    @OneToMany(mappedBy = "subdivision_id")
    private List<Employee> employees;

    public Subdivision() {
    }

    public int getId_subdivision() {
        return id_subdivision;
    }

    public void setId_subdivision(int id_subdivision) {
        this.id_subdivision = id_subdivision;
    }

    public String getName_subdivision() {
        return name_subdivision;
    }

    public void setName_subdivision(String name_subdivision) {
        this.name_subdivision = name_subdivision;
    }

    public String getСontact_details_subdivision() {
        return сontact_details_subdivision;
    }

    public void setСontact_details_subdivision(String сontact_details_subdivision) {
        this.сontact_details_subdivision = сontact_details_subdivision;
    }

    public String getSupervisor_subdivision() {
        return supervisor_subdivision;
    }

    public void setSupervisor_subdivision(String supervisor_subdivision) {
        this.supervisor_subdivision = supervisor_subdivision;
    }

    public Organization getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Organization organization_id) {
        this.organization_id = organization_id;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}

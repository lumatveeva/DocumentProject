package com.example.DocumentProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table(name = "Subdivision")
public class Subdivision {
    @Id
    @Column(name = "id_subdivision")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name_subdivision")
    @NotEmpty(message = "Необходимо ввести название подразделения")
    private String name;

    @Column(name = "info_subdivision")
    @NotEmpty(message = "Необходимо ввести контактные данные подразделения")
    private String info;

    @Column(name = "supervisor_subdivision")
    @NotEmpty(message = "Необходимо ввести руководителя подразделения")
    private String supervisor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "organization_id", referencedColumnName = "id_organization")
    private Organization organization_id;
    public Subdivision() {
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id_subdivision) {
        this.id = id_subdivision;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_subdivision) {
        this.name = name_subdivision;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor_subdivision) {
        this.supervisor = supervisor_subdivision;
    }

    public Organization getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Organization organization_id) {
        this.organization_id = organization_id;
    }


    @Override
    public String toString() {
        return "Subdivision{" +
                "id_subdivision=" + id +
                ", name_subdivision='" + name + '\'' +
                ", info='" + info + '\'' +
                ", supervisor_subdivision='" + supervisor + '\'' +
                ", organization_id=" + organization_id +
                '}';
    }
    private Subdivision(Subdivision.Builder builder){
        setId(builder.id);
        setName(builder.name);
        setInfo(builder.info);
        setSupervisor(builder.supervisor);
        setOrganization_id(builder.organization_id);
    }
    public static final class Builder{
        private UUID id;
        private String name;
        private String info;
        private String supervisor;
        private Organization organization_id;
        public Builder(){

        }
        public Builder buildId(UUID val){
            id = val;
            return this;
        }
        public Builder buildName(String val){
            name = val;
            return this;
        }
        public Builder buildInfo(String val){
            info = val;
            return this;
        }
        public Builder buildSupervisor(String val){
            supervisor = val;
            return this;
        }
        public Builder buildOrganization_id(Organization val){
            organization_id = val;
            return this;
        }
        public Subdivision build(){
            return new Subdivision(this);
        }
    }
}

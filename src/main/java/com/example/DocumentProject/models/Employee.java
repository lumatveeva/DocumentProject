package com.example.DocumentProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "surname_employee")
    @NotEmpty(message = "Необходимо ввести фамилию сотрудника")
    private String surname;

    @Column(name = "name_employee")
    @NotEmpty(message = "Необходимо ввести имя сотрудника")
    private String name;

    @Column(name = "patronymic_employee")
    @NotEmpty(message = "Необходимо ввести отчество сотрудника")
    private String patronymic;

    @Column(name = "position_employee")
    @NotEmpty(message = "Необходимо ввести должность сотрудника")
    private String position;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subdivision_id", referencedColumnName = "id_subdivision" )
    private Subdivision subdivision_id;

    public Employee() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id_employee) {
        this.id = id_employee;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname_employee) {
        this.surname = surname_employee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_employee) {
        this.name = name_employee;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic_employee) {
        this.patronymic = patronymic_employee;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position_employee) {
        this.position = position_employee;
    }

    public Subdivision getSubdivision_id() {
        return subdivision_id;
    }

    public void setSubdivision_id(Subdivision subdivision_id) {
        this.subdivision_id = subdivision_id;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id_employee=" + id +
                ", surname_employee='" + surname + '\'' +
                ", name_employee='" + name + '\'' +
                ", patronymic_employee='" + patronymic + '\'' +
                ", position_employee='" + position + '\'' +
                ", subdivision_id=" + subdivision_id +
                '}';
    }
    private Employee(Employee.Builder builder){
        setId(builder.id_employee);
        setSurname(builder.surname);
        setName(builder.name);
        setPatronymic(builder.patronymic);
        setPosition(builder.position);
        setSubdivision_id(builder.subdivision_id);
    }
    public static final class Builder{
        private UUID id_employee;
        private String surname;
        private String name;
        private String patronymic;
        private String position;
        private Subdivision subdivision_id;

        public Builder buildId(UUID val){
            id_employee = val;
            return this;
        }
        public Builder buildSurname(String val){
            surname = val;
            return this;
        }
        public Builder buildName(String val){
            name = val;
            return this;
        }
        public Builder buildPatronymic(String val){
            patronymic = val;
            return this;
        }
        public Builder buildPosition(String val){
            position = val;
            return this;
        }
        public Builder buildSubdivision_id(Subdivision val){
            subdivision_id = val;
            return this;
        }
        public Employee build(){
            return new Employee(this);
        }
    }
}

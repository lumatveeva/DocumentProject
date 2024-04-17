package com.example.DocumentProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column(name = "id_employee")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_employee;

    @Column(name = "surname_employee")
    @NotEmpty(message = "Необходимо ввести фамилию сотрудника")
    private String surname_employee;

    @Column(name = "name_employee")
    @NotEmpty(message = "Необходимо ввести имя сотрудника")
    private String name_employee;

    @Column(name = "patronymic_employee")
    @NotEmpty(message = "Необходимо ввести отчество сотрудника")
    private String patronymic_employee;

    @Column(name = "position_employee")
    @NotEmpty(message = "Необходимо ввести должность сотрудника")
    private String position_employee;

    @ManyToOne
    @JoinColumn(name = "subdivision_id", referencedColumnName = "id_subdivision" )
    private Subdivision subdivision_id;

    @OneToMany(mappedBy = "authorDocument")
    private List<Document> author_documents;

    @ManyToMany
    @JoinTable(name ="employee_executor_document",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name ="document_id"))
    private List<Document> executor_employee;

    public Employee() {
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getSurname_employee() {
        return surname_employee;
    }

    public void setSurname_employee(String surname_employee) {
        this.surname_employee = surname_employee;
    }

    public String getName_employee() {
        return name_employee;
    }

    public void setName_employee(String name_employee) {
        this.name_employee = name_employee;
    }

    public String getPatronymic_employee() {
        return patronymic_employee;
    }

    public void setPatronymic_employee(String patronymic_employee) {
        this.patronymic_employee = patronymic_employee;
    }

    public String getPosition_employee() {
        return position_employee;
    }

    public void setPosition_employee(String position_employee) {
        this.position_employee = position_employee;
    }

    public Subdivision getSubdivision_id() {
        return subdivision_id;
    }

    public void setSubdivision_id(Subdivision subdivision_id) {
        this.subdivision_id = subdivision_id;
    }

    public List<Document> getAuthor_documents() {
        return author_documents;
    }

    public void setAuthor_documents(List<Document> author_documents) {
        this.author_documents = author_documents;
    }

    public List<Document> getExecutor_employee() {
        return executor_employee;
    }

    public void setExecutor_employee(List<Document> executor_documents) {
        this.executor_employee = executor_documents;
    }

    @Override
    public String toString() {
        return  surname_employee + ' ' + name_employee + ' ' + patronymic_employee;
    }
}

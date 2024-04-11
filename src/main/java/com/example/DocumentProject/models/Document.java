package com.example.DocumentProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @Column (name = "id_document")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_document;

    @Column (name = "subject_document")
    @NotEmpty(message = "Необходимо ввести предмет поручения")
    private String subject_document;

    @ManyToOne
    @JoinColumn(name = "author_document", referencedColumnName = "id_employee")
    private Employee author_document;

    @ManyToMany(mappedBy = "executor_employee")
    private List<Employee> executors_document;

    @Column(name = "period_of_execution")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    //@NotEmpty(message = "Необходимо ввести срок исполнения поручения")
    private Date period_of_execution;

    @Column(name = "text_document")
    @NotEmpty(message = "Необходимо ввести текст поручения")
    private String text_document;
//    @Enumerated(EnumType.STRING)
    @Column(name ="document_status")
    private String documentStatus;

    @Transient
    private boolean isDone = false;

    public Document() {
    }

    public int getId_document() {
        return id_document;
    }

    public void setId_document(int id_document) {
        this.id_document = id_document;
    }

    public String getSubject_document() {
        return subject_document;
    }

    public void setSubject_document(String subject_document) {
        this.subject_document = subject_document;
    }

    public Employee getAuthor_document() {
        return author_document;
    }

    public void setAuthor_document(Employee author_document) {
        this.author_document = author_document;
    }

    public List<Employee> getExecutors_document() {
        return executors_document;
    }

    public void setExecutors_document(List<Employee> executors_document) {
        this.executors_document = executors_document;
    }

    public Date getPeriod_of_execution() {
        return period_of_execution;
    }

    public void setPeriod_of_execution(Date period_of_execution) {
        this.period_of_execution = period_of_execution;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getText_document() {
        return text_document;
    }

    public void setText_document(String text_document) {
        this.text_document = text_document;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}

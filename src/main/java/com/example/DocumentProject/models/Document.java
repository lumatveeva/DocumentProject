package com.example.DocumentProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @Column (name = "id_document")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDocument;

    @Column (name = "subject_document")
    @NotEmpty(message = "Необходимо ввести предмет поручения")
    private String subjectDocument;

    @ManyToOne
    @JoinColumn(name = "author_document", referencedColumnName = "id_employee")
    private Employee authorDocument;

//    @ManyToOne
//    @JoinColumn(name = "employee_id",referencedColumnName = "id_employee")
    @Transient
    private Employee executorsDocument;

    @Column(name = "period_of_execution")
    @NotEmpty(message = "Необходимо ввести срок исполнения поручения")
    private String periodOfExecution;

    @Column(name = "text_document")
    @NotEmpty(message = "Необходимо ввести текст поручения")
    private String textDocument;

    @Enumerated(EnumType.STRING)
    @Column(name ="document_status")
    private DocumentStatus documentStatus;

    @Column(name ="document_done")
    private boolean isDone = false;

    public Document() {
    }

    public int getIdDocument() {
        return idDocument;
    }

    public void setIdDocument(int id_document) {
        this.idDocument = id_document;
    }

    public String getSubjectDocument() {
        return subjectDocument;
    }

    public void setSubjectDocument(String subject_document) {
        this.subjectDocument = subject_document;
    }

    public Employee getAuthorDocument() {
        return authorDocument;
    }

    public void setAuthorDocument(Employee author_document) {
        this.authorDocument = author_document;
    }

    public Employee getExecutorsDocument() {
        return executorsDocument;
    }

    public void setExecutorsDocument(Employee executors_document) {
        this.executorsDocument = executors_document;
    }

    public String getPeriodOfExecution() {
        return periodOfExecution;
    }

    public void setPeriodOfExecution(String period_of_execution) {
        this.periodOfExecution = period_of_execution;
    }

    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getTextDocument() {
        return textDocument;
    }

    public void setTextDocument(String text_document) {
        this.textDocument = text_document;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public String toString() {
        return "Document{" +
                "idDocument=" + idDocument +
                ", subjectDocument='" + subjectDocument + '\'' +
                ", authorDocument=" + authorDocument +
                ", executorsDocument=" + executorsDocument +
                ", periodOfExecution='" + periodOfExecution + '\'' +
                ", textDocument='" + textDocument + '\'' +
                ", documentStatus=" + documentStatus +
                ", isDone=" + isDone +
                '}';
    }
}

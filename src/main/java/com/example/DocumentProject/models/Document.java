package com.example.DocumentProject.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @Column (name = "id_document")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "subject_document")
    @NotEmpty(message = "Необходимо ввести предмет поручения")
    private String subject;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_document", referencedColumnName = "id_employee")
    private Employee author_id;

//    @ManyToOne
//    @JoinColumn(name = "employee_id",referencedColumnName = "id_employee")
    @Transient
    private Employee executors;

    @Column(name = "period_of_execution")
//    @NotEmpty(message = "Необходимо ввести срок исполнения поручения")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date periodOfExecution;

    @Column(name = "text_document")
    @NotEmpty(message = "Необходимо ввести текст поручения")
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(name ="document_status")
    private DocumentStatus documentStatus;

    @Column(name ="document_done")
    private boolean isDone = false;

    public Document() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id_document) {
        this.id = id_document;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject_document) {
        this.subject = subject_document;
    }

    public Employee getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Employee author_document) {
        this.author_id = author_document;
    }

    public Employee getExecutors() {
        return executors;
    }

    public void setExecutors(Employee executors_document) {
        this.executors = executors_document;
    }

    public Date getPeriodOfExecution() {
        return periodOfExecution;
    }

    public void setPeriodOfExecution(Date period_of_execution) {
        this.periodOfExecution = period_of_execution;
    }

    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getText() {
        return text;
    }

    public void setText(String text_document) {
        this.text = text_document;
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
                "idDocument=" + id +
                ", subjectDocument='" + subject + '\'' +
                ", authorDocument=" + author_id +
                ", executorsDocument=" + executors +
                ", periodOfExecution='" + periodOfExecution + '\'' +
                ", textDocument='" + text + '\'' +
                ", documentStatus=" + documentStatus +
                ", isDone=" + isDone +
                '}';
    }
  private Document (Document.DocumentBuilder builder){
        setId(builder.id);
        setSubject(builder.subject);
        setAuthor_id(builder.author);
        setExecutors(builder.executors);
        setPeriodOfExecution(builder.periodOfExecution);
        setText(builder.text);
        setDocumentStatus(builder.documentStatus);
        setDone(builder.isDone);
  }
    public static final class DocumentBuilder{
        private UUID id;
        private String subject;
        private Employee author;
        private Employee executors;
        private Date periodOfExecution;
        private String text;
        private DocumentStatus documentStatus;
        private boolean isDone = false;
        public DocumentBuilder() {
        }

        public Document.DocumentBuilder buildId(UUID val){
            id = val;
            return this;
        }
        public DocumentBuilder buildSubject(String val){
            subject = val;
            return this;
        }
        public DocumentBuilder buildAuthorId(Employee val){
            author = val;
            return this;
        }
        public DocumentBuilder buildExecutor(Employee val){
            executors = val;
            return this;
        }
        public DocumentBuilder buildPeriodOfExecution(Date val){
            periodOfExecution = val;
            return this;
        }
        public DocumentBuilder buildText(String val){
            text = val;
            return this;
        }
        public DocumentBuilder buildDocumentStatus(DocumentStatus val){
            documentStatus = val;
            return this;
        }
        public DocumentBuilder buildIsDone(boolean val){
            isDone = val;
            return this;
        }
        public Document build(){
            return new Document(this) ;
        }
    }
}

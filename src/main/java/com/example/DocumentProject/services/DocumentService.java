package com.example.DocumentProject.services;

import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.models.DocumentStatus;
import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Document findById(int id){
        return documentRepository.findById(id).orElse(null);
    }
    public List<Document> findAll(){
        return documentRepository.findAll();
    }

    @Transactional
    public void save(Document document){
        documentRepository.save(document);
    }

    @Transactional
    public void update(Document updateDocument, int id){
        updateDocument.setId_document(id);
        documentRepository.save(updateDocument);
    }

    @Transactional
    public void delete(int id){
        documentRepository.deleteById(id);
    }

    @Transactional //назначить автора поручения
    public void assignAuthor(int documentId, Employee employee){
        documentRepository.findById(documentId).ifPresent(
                doc -> {
                    doc.setAuthor_document(employee);
                    doc.setDocumentStatus("NEW");
                });
    }
    @Transactional //назначить исполнителя получения
    public void assignExecutor(int documentId, List<Employee> executors){
        documentRepository.findById(documentId).ifPresent(
                doc -> {
                    doc.setExecutors_document(executors);
                });
    }

    public String changeStatusDocument(int id){
        Document document = documentRepository.findById(id).orElse(null);
        String status = document.getDocumentStatus();
        return "EXECUTION";

    }


    @Transactional//отправить документ на исполнение
    public void startExecution(int id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus("EXECUTION")
        );
    }
    @Transactional//отправить документ на контроль
    public void startControl(int id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus("CONTROL")
        );
    }
    @Transactional//отправить документ на доработку
    public void startRevision(int id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus("REVISION")
        );
    }
    @Transactional//отправить документ на прием
    public void startAcceptance(int id){
        documentRepository.findById(id).ifPresent(
                doc -> {
                    doc.setDocumentStatus("ACCEPTANCE");
                    doc.setDone(true);
                });
    }

}

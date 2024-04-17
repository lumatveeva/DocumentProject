package com.example.DocumentProject.services;

import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.models.DocumentStatus;
import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Document> findAllPagable(int page, int itemPerPage){
        return documentRepository.findAll(PageRequest.of(page, itemPerPage)).getContent();
    }
    public List<Document> findAllSort(String sortBy){
        return documentRepository.findAll(Sort.by(sortBy));
    }

    @Transactional
    public void save(Document document){
        documentRepository.save(document);
    }

    @Transactional
    public void update(Document updateDocument, int id){
        updateDocument.setIdDocument(id);
        documentRepository.save(updateDocument);
    }

    @Transactional
    public void delete(int id){
        documentRepository.deleteById(id);
    }

    @Transactional //назначить автора поручения
    public void assignStatus(int documentId){
        documentRepository.findById(documentId).ifPresent(
                doc -> doc.setDocumentStatus(DocumentStatus.NEW));
    }
    @Transactional //назначить исполнителя получения
    public void assignExecutor(int documentId, List<Employee> executors){
        documentRepository.findById(documentId).ifPresent(
                doc -> {
                    doc.setExecutorsDocument(executors);
                });
    }

    @Transactional//отправить документ на исполнение
    public void startExecution(int id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus(DocumentStatus.EXECUTION)
        );
    }
    @Transactional//отправить документ на контроль
    public void startControl(int id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus(DocumentStatus.CONTROL)
        );
    }
    @Transactional//отправить документ на доработку
    public void startRevision(int id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus(DocumentStatus.REVISION));
    }
    @Transactional//отправить документ на прием
    public void startAcceptance(int id){
        documentRepository.findById(id).ifPresent(
                doc -> {
                    doc.setDocumentStatus(DocumentStatus.ACCEPTANCE);
                    doc.setDone(true);
                });
    }
//    public void doneDocument(int id){ // отправка документа на выполнение описана в методе startAcceptance
//        documentRepository.findById(id).ifPresent(
//                doc -> doc.setDone(true)
//        );
//    }

}

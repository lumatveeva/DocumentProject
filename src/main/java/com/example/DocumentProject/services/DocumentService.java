package com.example.DocumentProject.services;

import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.models.DocumentStatus;
import com.example.DocumentProject.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Document findById(UUID id){
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
    public void update(Document updateDocument, UUID id){
        updateDocument.setId(id);
        documentRepository.save(updateDocument);
    }

    @Transactional
    public void delete(UUID id){
        documentRepository.deleteById(id);
    }


    @Transactional//отправить документ на исполнение
    public void startExecution(UUID id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus(DocumentStatus.EXECUTION)
        );
    }
    @Transactional//отправить документ на контроль
    public void startControl(UUID id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus(DocumentStatus.CONTROL)
        );
    }
    @Transactional//отправить документ на доработку
    public void startRevision(UUID id){
        documentRepository.findById(id).ifPresent(
                doc -> doc.setDocumentStatus(DocumentStatus.REVISION));
    }
    @Transactional//отправить документ на прием
    public void startAcceptance(UUID id){
        documentRepository.findById(id).ifPresent(
                doc -> {
                    doc.setDocumentStatus(DocumentStatus.ACCEPTANCE);
                    doc.setDone(true);
                });
    }

}

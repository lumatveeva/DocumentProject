package com.example.DocumentProject.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class DocumentAspects {
    private LocalDateTime currentDate = LocalDateTime.now();
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.findAll(*))")
    public void beforeFindAllDocumentAdvice(){
        System.out.println(currentDate + " BEFORE: попытка вывести список всех документов");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.findById(..))")
    public void beforeFindByIdDocumentAdvice(){
        System.out.println(currentDate + " BEFORE: попытка вывести документ по id");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.create(*))")
    public void beforeCreateDocumentAdvice(){
        System.out.println(currentDate + " BEFORE: попытка перенаправления на страницу создания нового документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.save(..))")
    public void beforeSaveDocumentAdvice(){
        System.out.println(currentDate + " BEFORE: попытка сохранения нового документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.editDocument(..))")
    public void beforeEditDocumentAdvice(){
        System.out.println(currentDate + " BEFORE: попытка перенаправления на страницу редактирования существующего документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.updateDocument(..))")
    public void beforeUpdateDocumentAdvice(){
        System.out.println(currentDate + " BEFORE: попытка редактирования существующего документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.deleteDocument(*))")
    public void beforeDeleteDocumentAdvice(){
        System.out.println(currentDate + " BEFORE: попытка удаления существующего документа");
    }

    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.start*(*))")
    public void beforeStartDocumentAdvice(){
        System.out.println(LocalDateTime.now() + " BEFORE: попытка изменения статуса документа");
    }
}

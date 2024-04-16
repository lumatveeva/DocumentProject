package com.example.DocumentProject.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DocumentAspects {
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.findAll(*))")
    public void beforeFindAllDocumentAdvice(){
        System.out.println("before: попытка вывести список всех документов");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.findById(..))")
    public void beforeFindByIdDocumentAdvice(){
        System.out.println("before: попытка вывести документ по id");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.create(*))")
    public void beforeCreateDocumentAdvice(){
        System.out.println("before: попытка перенаправления на страницу создания нового документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.save(..))")
    public void beforeSaveDocumentAdvice(){
        System.out.println("before: попытка сохранения нового документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.editDocument(..))")
    public void beforeEditDocumentAdvice(){
        System.out.println("before: попытка перенаправления на страницу редактирования существующего документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.updateDocument(..))")
    public void beforeUpdateDocumentAdvice(){
        System.out.println("before: попытка редактирования существующего документа");
    }
    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.deleteDocument(*))")
    public void beforeDeleteDocumentAdvice(){
        System.out.println("before: попытка удаления существующего документа");
    }

    @Before("execution(public String com.example.DocumentProject.controllers.DocumentController.start*(*))")
    public void beforeStartDocumentAdvice(){
        System.out.println("before: попытка изменения статуса документа");
    }
}

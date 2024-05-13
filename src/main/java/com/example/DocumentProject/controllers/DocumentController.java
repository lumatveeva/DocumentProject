package com.example.DocumentProject.controllers;

import com.example.DocumentProject.annotations.LoggingAspect;
import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.services.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/documents")

public class DocumentController {
    @Autowired
    private DocumentService documentService;

    /**
     *
     * @param page Указывается номер странице при пагинации
     * @param itemPerPage Указывается количество элементов на странице при пагинации
     * @param sortBy Указывается признак по которому необходимо осуществить сотрировку
     * @return Список документов
     */
    @Operation(summary = "Получение информации о всех документах")
    @GetMapping()
    @LoggingAspect
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации о книге",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    public List<Document> findAll(
                          @RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "itemPerPage", required = false) Integer itemPerPage,
                          @RequestParam(value = "sortBy", required = false) String sortBy){
        List<Document> documents = new ArrayList<>();
        if((page == null || itemPerPage == null) && sortBy == null){
            documents.addAll(documentService.findAll());
        }
        if(page != null && itemPerPage != null && sortBy == null){
            documents.addAll(documentService.findAllPagable(page, itemPerPage));
        }
        if ((page == null || itemPerPage == null) && sortBy != null){
            documents.addAll(documentService.findAllSort(sortBy));
        }
        return documents;
    }

    @GetMapping("/{id}")
    @LoggingAspect
    @Operation(summary = "Получение информации о документе по его Id")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации о книге",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    public Document findById(@PathVariable("id") int id){
        return documentService.findById(id);
    }

    /**
     *Метод для сохранения нового документа
     * @param document
     * @param bindingResult
     * @return Документ который был создан
     */
    @PostMapping("/add")
    @LoggingAspect
    @Operation(summary = "Создание нового документа")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации о книге",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    public Document save(@RequestBody Document document, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Ошибка при создании нового документа");
        }
        documentService.save(document);
        return document;
    }

    @PatchMapping("{id}")
    @LoggingAspect
    @Operation(summary = "Обновление документа")
    @ApiResponse(
            responseCode = "200",
            description = "Успешное получение информации о книге",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE)
    )
    public Document updateDocument(@RequestBody Document document,
                               @PathVariable("id") int id,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RuntimeException("Ошибка обновления существующего документа");
        }

        documentService.update(document,id);
        return document;
    }

    @DeleteMapping("{id}/delete")
    @LoggingAspect
    @Operation(summary = "Удаление документа")
    public void deleteDocument(@PathVariable("id") int id){
        documentService.delete(id);
    }

    @GetMapping("/{id}/execution")
    @LoggingAspect
    @Operation(summary = "Принятие документа в работу")

    public void startExecution(@PathVariable("id") int id){
        documentService.startExecution(id);
    }
    @GetMapping("/{id}/control")
    @LoggingAspect
    @Operation(summary = "Отправка документа на контроль")
    public void startControl(@PathVariable("id") int id){
        documentService.startControl(id);
    }
    @GetMapping("/{id}/revision")
    @LoggingAspect
    @Operation(summary = "Отправка документа на проверку")
    public void startRevision(@PathVariable("id") int id){
        documentService.startRevision(id);
    }
    @GetMapping("/{id}/accept")
    @LoggingAspect
    @Operation(summary = "Отправка документа на согласование")
    public void startAcceptance(@PathVariable("id") int id){
        documentService.startAcceptance(id);
    }
}

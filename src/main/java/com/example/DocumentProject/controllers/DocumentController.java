package com.example.DocumentProject.controllers;

import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.models.DocumentStatus;
import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.services.DocumentService;
import com.example.DocumentProject.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/documents")
@Slf4j
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String findAll(Model model,
                          @RequestParam(value = "page", required = false) Integer page,
                          @RequestParam(value = "itemPerPage", required = false) Integer itemPerPage,
                          @RequestParam(value = "sortBy", required = false) String sortBy){
        if((page == null || itemPerPage == null) && sortBy == null){
            model.addAttribute("docs", documentService.findAll());
        }
        if(page != null && itemPerPage != null && sortBy == null){
            model.addAttribute("docs", documentService.findAllPagable(page, itemPerPage));
        }
        if ((page == null || itemPerPage == null) && sortBy != null){
            model.addAttribute("docs", documentService.findAllSort(sortBy));
        }
        log.info("Получение списка документов: страница {}, количество элементов на странице {}, сортировка по {}", page, itemPerPage, sortBy);
        return"/documents/documentsAll";
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable("id") int id, Model model){
        model.addAttribute("doc", documentService.findById(id));
        model.addAttribute("employees", employeeService.findAll());
        log.info("Поис документа по id {}", id);

//        return "/documents/documentById";
    }

    @GetMapping("/new")
    public void create(Model model){
        model.addAttribute("document", new Document());
        model.addAttribute("employees", employeeService.findAll());
        log.info("создание нового документа");

//        return "/documents/new";
    }

    /**
     *
     * @param requestData(поля класса Document b List Employments)
     * @return главная страница
     */
    @PostMapping()
    public String save(@RequestBody Map<String, Object> requestData, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.error("Ошибка при создании нового документа");
            throw new RuntimeException("Ошибка при создании нового документа");
        }
        // Преобразование данных из JSON в объекты Java
        String subjectDocument = (String) requestData.get("subjectDocument");
        String periodOfExecution = (String) requestData.get("periodOfExecution");
        String textDocument = (String) requestData.get("textDocument");
        Integer authorDocumentId = (Integer) requestData.get("authorDocument");
        List<Map<String, Integer>> executorsData = (List<Map<String, Integer>>) requestData.get("executors");

        // Преобразование списка исполнителей из JSON в список объектов Employee
        List<Employee> executors = new ArrayList<>();
        for (Map<String, Integer> executorData : executorsData) {
            Integer id_employee = executorData.get("id_employee");
            Employee executor = employeeService.findById(id_employee); // Здесь предполагается, что у вас есть сервис для получения сотрудника по ID
            executors.add(executor);
        }

        // Создание объекта документа
        Document document = new Document();
        document.setSubjectDocument(subjectDocument);
        document.setPeriodOfExecution(periodOfExecution);
        document.setTextDocument(textDocument);

        // Получение автора документа по ID
        Employee authorDocument = employeeService.findById(authorDocumentId);
        document.setAuthorDocument(authorDocument);

        // Установка списка исполнителей
        document.setExecutorsDocument(executors);
        //Назначение статуса документа
        document.setDocumentStatus(DocumentStatus.NEW);

        // Сохранение документа
        documentService.save(document);
        log.info("Сохранение нового документа: название документа {}", requestData.get("subjectDocument"));
        return "redirect:/documents";
    }

    @GetMapping("{id}/edit")
    public void editDocument(@PathVariable("id") int id, Model model){
        model.addAttribute("updatedDocument", documentService.findById(id));
        model.addAttribute("employees", employeeService.findAll());
        log.info("Изменение документа по id {}", id);
//        return "documents/updateDocument";
    }
    @PatchMapping("{id}")
    public void updateDocument(@RequestBody Map<String, Object> requestData,
                               @PathVariable("id") int id,
                               BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("Ошибка при обновлении существующего документа");
            throw new RuntimeException("Ошибка обновления существующего документа");
        }
        Document updatedDocument = documentService.findById(id);

        if(requestData.get("subjectDocument") != null ){
            updatedDocument.setSubjectDocument((String) requestData.get("subjectDocument"));
        }
        if(requestData.get("periodOfExecution") != null)
        {
            updatedDocument.setPeriodOfExecution((String)requestData.get("periodOfExecution"));
        }
        if(requestData.get("authorDocument") != null){
            String authorDocumentId = (String) requestData.get("authorDocument");
            Employee authorDocument = employeeService.findById(Integer.parseInt(authorDocumentId));
            updatedDocument.setAuthorDocument(authorDocument);
        }
        if(requestData.get("textDocument") != null){
            updatedDocument.setTextDocument((String) requestData.get("textDocument"));
        }
        if((requestData.get("executors")) != null){
            List<Map<String, Object>> executors = (List<Map<String, Object>>) requestData.get("executors");
            List<Employee> executorsEmployee = new ArrayList<>();
            for(Map<String, Object> employee : executors){
                Integer employeeId = (Integer) employee.get("id_employee");
                executorsEmployee.add(employeeService.findById(employeeId));
            }
            updatedDocument.setExecutorsDocument(executorsEmployee);
        }
        documentService.update(updatedDocument,id);
        log.info("Сохранение измененного документа по id {}", id);

    }

    @DeleteMapping("{id}/delete")
    public void deleteDocument(@PathVariable("id") int id){
        documentService.delete(id);
        log.info("Удаление документа id {}", id);
//        return"redirect:http://localhost:8080/documents";
    }

    @GetMapping("/{id}/execution")
    public void startExecution(@PathVariable("id") int id){
        documentService.startExecution(id);
//        return "redirect:/documents";
    }
    @GetMapping("/{id}/control")
    public void startControl(@PathVariable("id") int id){
        documentService.startControl(id);
//        return "redirect:/documents";
    }
    @GetMapping("/{id}/revision")
    public void startRevision(@PathVariable("id") int id){
        documentService.startRevision(id);
//        return "redirect:/documents";
    }
    @GetMapping("/{id}/accept")
    public void startAcceptance(@PathVariable("id") int id){
        documentService.startAcceptance(id);
//        return "redirect:/documents";
    }

}

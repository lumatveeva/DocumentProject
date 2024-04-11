package com.example.DocumentProject.controllers;

import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.models.Employee;
import com.example.DocumentProject.services.DocumentService;
import com.example.DocumentProject.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public String findAll(Model model){
        model.addAttribute("docs", documentService.findAll());
        return"/documents/documentsAll";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model){
        model.addAttribute("doc", documentService.findById(id));
        model.addAttribute("employees", employeeService.findAll());

        return "/documents/documentById";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("document", new Document());
        model.addAttribute("employees", employeeService.findAll());
//        model.addAttribute("executorsSize", employeeService.findAll().size());

        return "/documents/new";
    }
    @PostMapping()
    public String save(@ModelAttribute("document") Document document,
                       @ModelAttribute("author") Employee author,
                       @ModelAttribute("date") Date date){
//                       @RequestParam("executors") List<Employee> executors) {
        assignAuthor(document.getId_document(), author);
        document.setPeriod_of_execution(date);
//        assignExecutor(document.getId_document(), executors);
        documentService.save(document);
        return "redirect:/documents";
    }
//    @PostMapping()
//    public String save(@ModelAttribute("document") Document document,
//                       @ModelAttribute ("employee") Employee employee){
//        assignAuthor(document.getId_document(), employee);
//        documentService.save(document);
//        return "redirect:/documents";
//    }
    @GetMapping("{id}/edit")
    public String editDocument(@PathVariable("id") int id, Model model){
        model.addAttribute("updatedDocument", documentService.findById(id));
//        model.addAttribute("employees", employeeService.findAll());
        return "documents/updateDocument";
    }
    @PatchMapping("{id}")
    public String updateDocument(@ModelAttribute("updatedDocument") @Valid Document updatedDocument,
                                 @PathVariable("id") int id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("Ошибка в обновлении документа");
            return "documents/updateDocument";
        }
        documentService.update(updatedDocument,id);
        return "redirect:/documents";
    }

    @DeleteMapping("{id}")
    public String deleteDocument(@PathVariable("id") int id){
        documentService.delete(id);
        return"redirect:documents";
    }
    private void assignAuthor(int documentId,Employee employee){
        documentService.assignAuthor(documentId, employee);
    }

    private void assignExecutor(int documentId, List<Employee> executors){
        documentService.assignExecutor(documentId, executors);
    }
    @GetMapping("/{id}/execution")
    public String startExecution(@PathVariable("id") int id){
        documentService.startExecution(id);
        return "redirect:/documents";
    }
    @GetMapping("/{id}/control")
    public String startControl(@PathVariable("id") int id){
        documentService.startControl(id);
        return "redirect:/documents";
    }
    @GetMapping("/{id}/revision")
    public String startRevision(@PathVariable("id") int id){
        documentService.startRevision(id);
        return "redirect:/documents";
    }
    @GetMapping("/{id}/accept")
    public String startAcceptance(@PathVariable("id") int id){
        documentService.startAcceptance(id);
        return "redirect:/documents";
    }

//    @GetMapping("{id}/done")
//    public String doneDocument(@PathVariable("id") int id){
//        documentService.doneDocument(id);
//        return "redirect:/documents";
//
//    }


}

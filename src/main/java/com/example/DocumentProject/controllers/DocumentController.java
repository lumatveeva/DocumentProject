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

import java.util.List;

@Controller
@RequestMapping("/documents")
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
        return"/documents/documentsAll";
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable("id") int id, Model model){
        model.addAttribute("doc", documentService.findById(id));
        model.addAttribute("employees", employeeService.findAll());

//        return "/documents/documentById";
    }

    @GetMapping("/new")
    public void create(Model model){
        model.addAttribute("document", new Document());
        model.addAttribute("employees", employeeService.findAll());
//        model.addAttribute("executorsSize", employeeService.findAll().size());

//        return "/documents/new";
    }
    @PostMapping()
    public void save(@ModelAttribute("document") @Valid Document document,
//                       @ModelAttribute("author") Employee author,
                       @RequestParam("executors") List<Employee> executors,
                       BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("Ошибка создания документа");
//            return "/documents/new";
        }
        documentService.save(document);
//        documentService.assignStatus(document.getIdDocument());
        documentService.assignExecutor(document.getIdDocument(), executors);

//        return "redirect:/documents";
    }

    @GetMapping("{id}/edit")
    public void editDocument(@PathVariable("id") int id, Model model){
        model.addAttribute("updatedDocument", documentService.findById(id));
        model.addAttribute("employees", employeeService.findAll());
//        return "documents/updateDocument";
    }
    @PatchMapping("{id}")
    public void updateDocument(@ModelAttribute("updatedDocument") @Valid Document updatedDocument,
                                 @PathVariable("id") int id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("Ошибка в обновлении документа");
//            return "documents/updateDocument";
        }
        documentService.update(updatedDocument,id);
//        return "redirect:/documents";
    }

    @DeleteMapping("{id}/delete")
    public void deleteDocument(@PathVariable("id") int id){
        documentService.delete(id);
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

//    @GetMapping("{id}/done")
//    public String doneDocument(@PathVariable("id") int id){
//        documentService.doneDocument(id);
//        return "redirect:/documents";
//
//    }


}

package com.example.DocumentProject.generators;

import com.example.DocumentProject.models.Document;
import com.example.DocumentProject.models.DocumentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DocumentGenerator {

    @Autowired
    private EmployeeGenerator employeeGenerator;
    private final List<String> SUBJECT = List.of("Докладная", "Накладная", "Раскладная", "Подкладная");
    private final List<String> TEXT = List.of("Накладная о расходах и доходах за 01/22", "Накладная о расходах и доходах за 02/22", "Накладная о расходах и доходах за 06/22", "Накладная о расходах и доходах за 10/22");

    public Document generateDocument(){
    return new Document.DocumentBuilder()
            .buildSubject(getRandomSubject())
            .buildAuthorId(employeeGenerator.generateEmployee())
            .buildPeriodOfExecution(new Date())
            .buildDocumentStatus(DocumentStatus.NEW)
            .buildText(getRandomText())
            .buildIsDone(false)
            .build();
}
    private int getRandomId(){
    Random random = new Random();
    return random.nextInt(15000);
}
    private String getRandomSubject(){
        Random random = new Random();
        int randomIndex = random.nextInt(SUBJECT.size());
        return SUBJECT.get(randomIndex);
    }
    private String getRandomText(){
        Random random = new Random();
        int randomIndex = random.nextInt(TEXT.size());
        return TEXT.get(randomIndex);
    }

}

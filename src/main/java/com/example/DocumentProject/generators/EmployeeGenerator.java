package com.example.DocumentProject.generators;

import com.example.DocumentProject.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class EmployeeGenerator {

    @Autowired
    private SubdivisionGenerator subdivisionGenerator;
    private final List<String> SURNAME = List.of("Matveeva", "Borisov", "Akhmatova", "Korostylev");
    private final List<String> NAME = List.of("Kate", "Oleg", "Anna", "Vladimir");
    private final List<String> PATRONYMIC = List.of("Sergeevna", "Olegovich", "Petrovna", "Konstantinovich");
    private final List<String> POSITION = List.of("cooking", "driver", "admin", "staff");
    public Employee generateEmployee(){
        return new Employee.Builder()
                .buildSurname(getRandomSurname())
                .buildName(getRandomName())
                .buildPatronymic(getRandomPatronymic())
                .buildPosition(getRandomPosition())
                .buildSubdivision_id(subdivisionGenerator.generateSubdivision())
                .build();
    }
    private int getRandomId(){
        Random random = new Random();
        return random.nextInt(15000);
    }
    private String getRandomSurname(){
        Random random = new Random();
        int randomIndex = random.nextInt(SURNAME.size());
        return SURNAME.get(randomIndex);
    }
    private String getRandomName(){
        Random random = new Random();
        int randomIndex = random.nextInt(NAME.size());
        return NAME.get(randomIndex);
    }
    private String getRandomPatronymic(){
        Random random = new Random();
        int randomIndex = random.nextInt(PATRONYMIC.size());
        return PATRONYMIC.get(randomIndex);
    }
    private String getRandomPosition(){
        Random random = new Random();
        int randomIndex = random.nextInt(POSITION.size());
        return POSITION.get(randomIndex);
    }

}

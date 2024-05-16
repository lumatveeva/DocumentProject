package com.example.DocumentProject.generators;

import com.example.DocumentProject.models.Subdivision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class SubdivisionGenerator {

    @Autowired
    private OrganizationGenerator organizationGenerator;
    private final List<String> NAMES = List.of("Food", "HR", "Sales", "Security");
    private final List<String> INFO = List.of("8-937-628-10-73","8-937-628-10-74","8-937-628-40-73","8-937-648-10-73");
    private final List<String> SUPERVISOR = List.of("Kate Matveeva", "Oleg Borisov", "Anna Akhmatova", "Vladimir Korostylev");

    public Subdivision generateSubdivision(){
       return new Subdivision.Builder()
               .buildName(getRandomName())
               .buildInfo(getRandomInfo())
               .buildSupervisor(getRandomSupervisor())
               .buildOrganization_id(organizationGenerator.generateOrganization())
               .build();
   }

    private int getRandomId(){
        Random random = new Random();
        return random.nextInt(15000);
    }
    private String getRandomName(){
        Random random = new Random();
        int randomIndex = random.nextInt(NAMES.size());
        return NAMES.get(randomIndex);
    }
    private String getRandomInfo(){
        Random random = new Random();
        int randomIndex = random.nextInt(INFO.size());
        return INFO.get(randomIndex);
    }
    private String getRandomSupervisor(){
        Random random = new Random();
        int randomIndex = random.nextInt(SUPERVISOR.size());
        return SUPERVISOR.get(randomIndex);
    }
}

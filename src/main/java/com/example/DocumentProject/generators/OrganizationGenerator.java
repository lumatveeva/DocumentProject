package com.example.DocumentProject.generators;

import com.example.DocumentProject.models.Organization;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class OrganizationGenerator {

    private final List<String> NAMES = List.of("Dodo", "Pizza Nick", "Papa jones", "Pizza hat");
    private final List<String> PHYSICAL_ADDRESS = List.of("ул. Революционная, д. 52", "ул. Мушникова, д. 13", "ул. Магистральная, д. 2А", "ул. Сходненская, д. 25");
    private final List<String> LEGAL_ADDRESS = List.of("г. Москва", "г. Казань", "г. Уфа", "г. Чебоксары");
    private final List<String> SUPERVISOR = List.of("Pert Petrov", "Luiza Suleimanova", "Liubov Matveeva", "Ilnur Kharipov");

    public Organization generateOrganization(){
        return new Organization.Builder()
                .buildName(getRandomName())
                .buildPhysical_address(getRandomPAddress())
                .buildLegal_address(getRandomLAddress())
                .buildSupervisor(getRandomSupervisor())
                .build();
    }

    public String getRandomName(){
        Random random = new Random();
        int randomIndex = random.nextInt(NAMES.size());
        return NAMES.get(randomIndex);
    }
    public String getRandomPAddress(){
        Random random = new Random();
        int randomIndex = random.nextInt(PHYSICAL_ADDRESS.size());
        return PHYSICAL_ADDRESS.get(randomIndex);
    }
    public String getRandomLAddress(){
        Random random = new Random();
        int randomIndex = random.nextInt(LEGAL_ADDRESS.size());
        return LEGAL_ADDRESS.get(randomIndex);
    }
    public String getRandomSupervisor(){
        Random random = new Random();
        int randomIndex = random.nextInt(SUPERVISOR.size());
        return SUPERVISOR.get(randomIndex);
    }

}

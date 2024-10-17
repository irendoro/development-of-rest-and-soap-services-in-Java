package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newId = new AtomicInteger(1); //класс, отвечающий за уникальные значения

    @PostMapping(value = "/createPet", consumes = "application/json", produces = "application/json")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet, newId.getAndIncrement());
        return "{\"message\":\"Поздравляем! Новый питомец успешно создан!\"}";
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id){
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value="/deletePet",consumes = "application/json")
    public String deletePet(@RequestBody Map<String, Integer> id) {
        petModel.delete(id.get("id"));
        return "{\"message\":\"Поздравляем! Питомец успешно удален!\"}";
    }

    @PutMapping(value="/updatePet", consumes = "application/json")
    public String updatePet(@RequestBody Map<String, Object> petData) {
        int id = (Integer) petData.get("id");
        Pet existingPet = petModel.getFromList(id);

        existingPet.setName((String) petData.get("name"));
        existingPet.setType((String) petData.get("type"));
        existingPet.setAge((Integer) petData.get("age"));
        return "{\"message\":\"Питомец успешно обновлен!\"}";
    }
}

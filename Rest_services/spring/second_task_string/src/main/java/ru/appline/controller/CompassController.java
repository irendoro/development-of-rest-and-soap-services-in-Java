package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.CompassModel;
import ru.appline.logic.Range;

import java.util.Map;

@RestController
public class CompassController {
    private static final CompassModel compassModel = CompassModel.getInstance();

    @PostMapping(value = "/setDirections", consumes = "application/json", produces = "application/json")
    public String setDirections(@RequestBody Map<String, String> directions) {
        for (Map.Entry<String, String> entry : directions.entrySet()) {
            String side = entry.getKey();
            String[] range = entry.getValue().split("-");
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            compassModel.setDirection(side, new Range(start, end));
        }

        return "{\"message\":\"Диапазоны для сторон света успешно заданы!\"}";
    }

    @GetMapping(value = "/getDirection", consumes = "application/json", produces = "application/json")
    public String getDirection(@RequestBody Map<String, Integer> request) {
        int degree = request.get("Degree");
        String direction = compassModel.getDirection(degree);
        return String.format("{\"Side\":\"%s\"}", direction);
    }

}

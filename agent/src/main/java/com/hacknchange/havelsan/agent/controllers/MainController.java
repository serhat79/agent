package com.hacknchange.havelsan.agent.controllers;

import com.hacknchange.havelsan.agent.requests.QuestionRequest;
import com.hacknchange.havelsan.agent.requests.TrainingDataRequest;
import com.hacknchange.havelsan.agent.services.IMainService;
import com.hacknchange.havelsan.agent.services.IWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MainController {

    private final IMainService mainService;
    private final IWriteService writeService;

    @Autowired
    public MainController(IMainService mainService, IWriteService writeService) {
        this.mainService = mainService;
        this.writeService = writeService;
    }


    @PostMapping("/train")
    @CrossOrigin(origins = "http://localhost:8081")
    public boolean train(@RequestBody TrainingDataRequest[] trainingDataRequest) {
        try {
            writeService.updateTrainData(trainingDataRequest);
        }catch (Exception e){
            throw new RuntimeException("Train Error");
        }
        return true;
    }

    @PostMapping("/predict")
    @CrossOrigin(origins = "http://localhost:8081")
    public String predict(@RequestBody QuestionRequest question) {
        try {
           return mainService.predictData(question.getQuestion());
        }catch (Exception e){
            throw new RuntimeException("Predict Error");
        }
    }


}

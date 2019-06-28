package com.hacknchange.havelsan.agent.services.impl;

import com.hacknchange.havelsan.agent.models.Topic;
import com.hacknchange.havelsan.agent.services.IMainService;
import com.hacknchange.havelsan.agent.services.IReadService;
import com.hacknchange.havelsan.agent.services.ITrainingService;
import com.hacknchange.havelsan.classification.classifiers.NaiveBayes;
import com.hacknchange.havelsan.classification.dataobjects.NaiveBayesKnowledgeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MainService implements IMainService {

    List<Topic> trainingData;

    private final IReadService readDataService;
    private final ITrainingService trainingService;
    private NaiveBayes naiveBayes;

    @Autowired
    public MainService(IReadService readDataService, ITrainingService trainingService) {
        this.readDataService = readDataService;
        this.trainingService = trainingService;
        try {
            trainData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String predictData(String question) {
        try {
            if (naiveBayes == null) {
                naiveBayes = new NaiveBayes();
            }
            NaiveBayesKnowledgeBase knowledgeBase = naiveBayes.getKnowledgeBase();
            naiveBayes = new NaiveBayes(knowledgeBase);

            String id = naiveBayes.predict(question);
            trainingData = readDataService.getTrainingData();
            if (trainingData != null && !trainingData.isEmpty()) {
                Topic topic = trainingData.stream().filter(f -> f.getId() == Integer.parseInt(id)).findAny().orElse(null);
                if (topic != null) return topic.getAnswer();
            }

            return "bulunamadı!";
        } catch (Exception e) {
            throw new RuntimeException("Prediction Error");
        }
    }

    public boolean trainData() {
        try {

            this.trainingData = readDataService.getTrainingData();
            naiveBayes = trainingService.trainData(trainingData);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Training Error");
        }
    }

}

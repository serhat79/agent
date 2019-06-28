package com.hacknchange.havelsan.agent.services.impl;

import com.hacknchange.havelsan.agent.models.Topic;
import com.hacknchange.havelsan.agent.services.ITrainingService;
import com.hacknchange.havelsan.classification.classifiers.NaiveBayes;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainingService implements ITrainingService {



    @Override
    public NaiveBayes trainData(List<Topic> topics) throws Exception {

        Map<String, String[]> trainingExamples = new HashMap<>();
        for (Topic topic : topics){
            trainingExamples.put(String.valueOf(topic.getId()), topic.getTrain());
        }

        NaiveBayes nb = new NaiveBayes();
        nb.setChisquareCriticalValue(0.01); //0.01 pvalue
        nb.train(trainingExamples);
        return nb;
    }
}
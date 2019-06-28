package com.hacknchange.havelsan.agent.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hacknchange.havelsan.agent.models.Topic;
import com.hacknchange.havelsan.agent.requests.TrainingDataRequest;
import com.hacknchange.havelsan.agent.services.IMainService;
import com.hacknchange.havelsan.agent.services.IReadService;
import com.hacknchange.havelsan.agent.services.IWriteService;
import com.hacknchange.havelsan.agent.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class WriteService implements IWriteService {
    private final IReadService readService;
    private final IMainService mainService;

    @Autowired
    public WriteService(IReadService readService, IMainService mainService) {
        this.readService = readService;
        this.mainService = mainService;
    }

    @Override
    public void updateTrainData(TrainingDataRequest[] trainingDataRequestArray) throws Exception {

        List<Topic> topicList = readService.getTrainingData();

        Integer i = topicList.stream().map(m->m.getId()).max(Comparator.comparing(c->c)).get();

        for (TrainingDataRequest trainingDataRequest : trainingDataRequestArray ) {
            Topic topic = new Topic();
            i++;
            topic.setId(i);
            topic.setAnswer(trainingDataRequest.getAnswer());
            topic.setTrain( trainingDataRequest.getQuestionList().toArray(new String[ trainingDataRequest.getQuestionList().size()]));
            topicList.add(topic);
        }

        JacksonUtils.writeObjectToFile("c:\\training-db.json", topicList);
        mainService.trainData();

    }
}

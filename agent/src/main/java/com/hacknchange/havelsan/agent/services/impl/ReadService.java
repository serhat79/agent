package com.hacknchange.havelsan.agent.services.impl;

import com.hacknchange.havelsan.agent.models.Topic;
import com.hacknchange.havelsan.agent.services.IReadService;
import com.hacknchange.havelsan.agent.utils.JacksonUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ReadService implements IReadService {


    @Override
    public List<Topic> getTrainingData() throws Exception {
        File file = new File("C://training-db.json");
        List<Topic> myTopics = JacksonUtils.readValueToList(file, Topic.class);
        return  myTopics;
    }
}

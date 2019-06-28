package com.hacknchange.havelsan.agent.services;

import com.hacknchange.havelsan.agent.models.Topic;

import java.util.List;

public interface IReadService {

    List<Topic> getTrainingData() throws Exception;
}

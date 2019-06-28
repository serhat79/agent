package com.hacknchange.havelsan.agent.services;

import com.hacknchange.havelsan.agent.requests.TrainingDataRequest;

public interface IMainService {

    String predictData(String question);

    boolean trainData();
}

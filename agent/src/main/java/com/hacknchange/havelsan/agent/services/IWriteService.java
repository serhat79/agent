package com.hacknchange.havelsan.agent.services;

import com.hacknchange.havelsan.agent.requests.TrainingDataRequest;

public interface IWriteService {
     void updateTrainData(TrainingDataRequest[] trainingDataRequest) throws Exception ;
}

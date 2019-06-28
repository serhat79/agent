package com.hacknchange.havelsan.agent.services;

import com.hacknchange.havelsan.agent.models.Topic;
import com.hacknchange.havelsan.classification.classifiers.NaiveBayes;

import java.util.List;

public interface ITrainingService {
     NaiveBayes trainData(List<Topic> topics)  throws Exception;
}

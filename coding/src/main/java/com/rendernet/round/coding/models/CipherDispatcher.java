package com.rendernet.round.coding.models;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Component
public class CipherDispatcher {

    
    // @Autowired
    // private Validator validator;

    // @Autowired
    // GenerateRandomNumbers generateRandomNumbers;

    public void dispatch(String json){

        //validate string

        AWSCredentials credentials = new BasicAWSCredentials("AKIAZMAW3JF3T6D4K2WV","RG8V8hgAFhl9tcdWpsI/Xm4YL1UT0sjlsqpBswHK");


        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .build();

        String queueUrl = "https://sqs.us-east-1.amazonaws.com/644293020023/test-queue";

        Random random = new Random();
        int randomNo = random.nextInt(10000);
        
        String updatedJsonString = "";                
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode jsonString = (ObjectNode) mapper.readTree(json);
            jsonString.put("uid", randomNo);
            updatedJsonString = "";
            updatedJsonString = mapper.writeValueAsString(jsonString);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        SendMessageRequest send_msg_request = new SendMessageRequest()
        .withQueueUrl(queueUrl)
        .withMessageBody(updatedJsonString);
        

        sqs.sendMessage(send_msg_request);

        // ListQueuesResult result = sqs.listQueues();
        // System.out.print(result.toString());

    }
}
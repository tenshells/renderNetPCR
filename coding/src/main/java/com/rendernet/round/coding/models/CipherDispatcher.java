package com.rendernet.round.coding.models;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Component
public class CipherDispatcher {

    int noOfMessages;

    @Autowired
    CipherDispatcher(){
        noOfMessages=0;
    }

    public void dispatch(String json){

        //validate string

        AWSCredentials credentials = new BasicAWSCredentials("AKIAZMAW3JF3T6D4K2WV","RG8V8hgAFhl9tcdWpsI/Xm4YL1UT0sjlsqpBswHK");


        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .build();

        String queueUrl = "https://sqs.us-east-1.amazonaws.com/644293020023/test-queue";

        Random random = new Random();
        int randomnumber = random.nextInt(10000);
        String updatedJsonString = "jesus";

        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode objectNode = (ObjectNode) mapper.readTree(json);
            objectNode.put("uid", randomnumber);
            updatedJsonString = mapper.writeValueAsString(objectNode);
        } catch(Exception e){
            System.out.print("Cannot add to string!");
        }


        SendMessageRequest send_msg_request = new SendMessageRequest()
        .withQueueUrl(queueUrl)
        .withDelaySeconds(5)
        .withMessageBody(updatedJsonString);
        
        System.out.println("Sending "+noOfMessages+"th json");

        sqs.sendMessage(send_msg_request);

        // ListQueuesResult result = sqs.listQueues();
        // System.out.print(result.toString());

    }
}
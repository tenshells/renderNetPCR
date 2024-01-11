package com.rendernet.round.coding.models;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rendernet.round.exception.RequiredFieldMissing;


@Component
public class CipherDispatcher {

    public void dispatch(String json){

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

            List<String> reqFields = List.of("name","age","height","is_student","grades","address","birth_date","is_employed","salary","skills","achievements");

            for(String field: reqFields){
                if(objectNode.get(field)==null){
                    throw new RequiredFieldMissing();
                }
            }

            objectNode.put("uid", randomnumber);
            updatedJsonString = mapper.writeValueAsString(objectNode);
        } catch(RequiredFieldMissing e){
            System.out.println("Required field is missing! ");
            return;
        } 
        catch(Exception e){
            System.out.print("String is fishy!");
            return;
        }

        SendMessageRequest send_msg_request = new SendMessageRequest()
        .withQueueUrl(queueUrl)
        .withDelaySeconds(5)
        .withMessageBody(updatedJsonString);

        sqs.sendMessage(send_msg_request);

    }
}
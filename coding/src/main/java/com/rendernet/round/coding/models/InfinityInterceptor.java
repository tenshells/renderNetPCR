package com.rendernet.round.coding.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

@Component
public class InfinityInterceptor {
    int noOfMessages;

    public void intercept(){
        
        AWSCredentials credentials = new BasicAWSCredentials("AKIAZMAW3JF3T6D4K2WV","RG8V8hgAFhl9tcdWpsI/Xm4YL1UT0sjlsqpBswHK");
        // AwsClientBuilder

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .build();

        String queueUrl = "https://sqs.us-east-1.amazonaws.com/644293020023/test-queue";

        ReceiveMessageRequest rmr = new ReceiveMessageRequest()
        .withQueueUrl(queueUrl)
        .withWaitTimeSeconds(5)
        .withMaxNumberOfMessages(1);

        while(true){
            List<Message> messages = sqs.receiveMessage(rmr).getMessages();

            for(Message message : messages){
                System.out.println("Message  recived is "+message.getBody());
                sqs.deleteMessage(queueUrl,message.getReceiptHandle());
            }

        }

    }
    
}

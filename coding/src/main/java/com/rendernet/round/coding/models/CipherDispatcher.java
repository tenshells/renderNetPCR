package com.rendernet.round.coding.models;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;


@Component
public class CipherDispatcher {
    public void dispatch(){
        AWSCredentials credentials = new BasicAWSCredentials("AKIAZMAW3JF3T6D4K2WV","RG8V8hgAFhl9tcdWpsI/Xm4YL1UT0sjlsqpBswHK");
        // AwsClientBuilder

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .build();

        String queueUrl = "https://sqs.us-east-1.amazonaws.com/644293020023/test-horsey";

        SendMessageRequest send_msg_request = new SendMessageRequest()
        .withQueueUrl(queueUrl)
        .withMessageBody("hello world")
        .withDelaySeconds(5);
sqs.sendMessage(send_msg_request);

        ListQueuesResult result = sqs.listQueues();
        System.out.print(result.toString());

    }
}
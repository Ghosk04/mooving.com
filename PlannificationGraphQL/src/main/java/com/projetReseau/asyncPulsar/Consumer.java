/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jedan.code.geminiService.asyncPulsar;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.pulsar.annotation.EnablePulsar;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

/**
 *
 * @author JD
 */
@EnablePulsar
@Service
public class Consumer {
    
    private static final String RESPONSE_TOPIC = "response_topic";
    
    @PulsarListener(subscriptionName = "response-topic-subscription",
            topics = RESPONSE_TOPIC,
            schemaType = SchemaType.JSON)
    public void responseTopicListener(Object response){
        System.out.println("Message bien reçu.");
    }
}

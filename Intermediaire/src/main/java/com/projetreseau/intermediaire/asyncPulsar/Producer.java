/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jedan.code.geminiService.asyncPulsar;

import java.net.http.HttpRequest;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.pulsar.core.DefaultSchemaResolver;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.pulsar.core.SchemaResolver;
import org.springframework.stereotype.Component;

/**
 *
 * @author JD
 */
@Component
public class Producer {
    
//    @Bean
//    public SchemaResolver.SchemaResolverCustomizer<DefaultSchemaResolver> schemaResolverCustomizer(){
//        return (schemaResolver) -> {
//            schemaResolver.addCustomSchemaMapping(HttpRequest.class, Schema.JSON(HttpRequest.class));
//        };
//    }
    @Autowired
    private PulsarTemplate<HttpRequest> template;
    
    private static final String REQUEST_TOPIC = "gemini-topic";
    
    public void sendMessageToPulsarTopic(HttpRequest request) throws PulsarClientException{
        template.send(REQUEST_TOPIC, request);
    }
}

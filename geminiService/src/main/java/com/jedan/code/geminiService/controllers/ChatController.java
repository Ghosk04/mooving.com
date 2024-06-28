/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jedan.code.geminiService.controllers;

import com.jedan.code.geminiService.models.Message;
import com.jedan.code.geminiService.services.ChatService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JD
 */
@RestController
@RequestMapping("/gemini/discussion")
public class ChatController {
    @Autowired
    private ChatService chatService;
    
    @PostMapping
    public Message getResponse(@RequestBody Message message){
        String projectId = "reference-baton-427407-f9";
        String location = "us-central1";
        String modelName = "gemini-1.5-flash-001";
        
        try{
            return new Message(chatService.reponse(projectId, location, modelName, message.getLibelle()));
        }catch(IOException e){
            return new Message(e.getMessage());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jedan.code.geminiService.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.preview.ChatSession;
import com.google.cloud.vertexai.generativeai.preview.GenerativeModel;
import com.google.cloud.vertexai.generativeai.preview.ResponseHandler;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Service;

/**
 *
 * @author JD
 */
@Service
public class ChatService {
    public String reponse(String projectId, String location, String modelName, String content)throws IOException{
        try(InputStream serviceAccount = new FileInputStream("/tmp/google_cloud_credentials.json");
            VertexAI vertexAI = new VertexAI(projectId, location, GoogleCredentials.fromStream(serviceAccount)
                    .createScoped("https://www.googleapis.com/auth/cloud-platform"));){
            
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            ChatSession chatSession = new ChatSession(model);
            return ResponseHandler.getText(chatSession.sendMessage(content));
        }
    }
}

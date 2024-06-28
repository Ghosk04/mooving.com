/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jedan.code.geminiService.models;


/**
 *
 * @author JD
 */
public class Message {
    private String libelle;
    
    public Message(){
        this.libelle = "";
    }
    
    public Message(String message){
        this.libelle = message;
    }
    
    public String getLibelle(){
        return this.libelle;
    }
    
    public void setLibelle(String libelle){
        this.libelle  = libelle;
    }
}

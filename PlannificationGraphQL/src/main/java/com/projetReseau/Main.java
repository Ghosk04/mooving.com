package com.projetReseau;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.context.annotation.ComponentScan;

@EnablePulsar
@ComponentScan(basePackages = "com.projetReseau")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(WavefrontProperties.Application.class, args);
    }
}
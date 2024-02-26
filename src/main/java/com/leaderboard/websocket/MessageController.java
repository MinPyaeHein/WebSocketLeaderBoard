package com.leaderboard.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Member;
import service.MessageService;

@Controller
public class MessageController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
   
    MessageService messageService;

    // Mapped as /app/application
    @MessageMapping("/application")
    @SendTo("/all/messages")
    public Message send(final Message message) throws Exception {
    	System.out.println("Arrive to  mesAllsage");
        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2fQ.XknT5Dw8aY7bAAUE1qBoHZQKXFUK06AMf8M_XuuVPoE"; // Replace this with your actual auth token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v1/members"; // Assuming message has a method getMemberId()
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println(responseBody);
        } else {
            System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
        }
        return message;
    }
    
    @MessageMapping("/application/distrbuteMember")
    @SendTo("/all/messages/distrbuteMember")
    public Message distrbuteMember(final Message message) throws Exception {
    	System.out.println("Arrive to distribute Member message");
        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2fQ.XknT5Dw8aY7bAAUE1qBoHZQKXFUK06AMf8M_XuuVPoE"; // Replace this with your actual auth token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",authToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "http://127.0.0.1:3000/api/v1/members"; // Assuming message has a method getMemberId()
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println(responseBody);
        } else {
            System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
        }
        System.out.println("Message=="+message.getText());
        return message;
    }
    

    
    @MessageMapping("/application/GetMemberById")
    @SendTo("/all/messages/GetMemberById")
    public Member getMemberById(final Message message) throws Exception {
        messageService=new MessageService();
        Member member = null;
        System.out.println("Arrive to GetMemberById controller");
//        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyMX0.hjKR9MOIHW3OgUamQHGaqBRKdMWsn3qkYJvxo7BPEj8";
        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2fQ.XknT5Dw8aY7bAAUE1qBoHZQKXFUK06AMf8M_XuuVPoE"; // Replace this with your actual auth token
        member =messageService.getMemberById(4, authToken);
        System.out.println("Member_Name="+member.getName());
        return member;
    }

}
package com.leaderboard.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import config.Message;
import model.Event;
import model.Member;
import service.MessageService;
import service.ScoreBoardService;

@Controller
public class ScoreBoardController {
    

    ScoreBoardService scoreBoardService;

    @MessageMapping("/scoreBoard/GetTeamInvestScores")
    @SendTo("/specific/scoreBoard/GetTeamInvestScores") 
    public JsonNode getTeamInvestScores(@Payload String payload) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(); 
        Event event = objectMapper.readValue(payload, Event.class);
        scoreBoardService = new ScoreBoardService();
        System.out.println("Arrive to GetTeamInvestScores");
        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2fQ.XknT5Dw8aY7bAAUE1qBoHZQKXFUK06AMf8M_XuuVPoE";
        JsonNode jsonNode = scoreBoardService.GetTeamInvestScores(event.getEvent_id(), authToken);
        return jsonNode;
    }
}
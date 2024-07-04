package com.leaderboard.websocket;

import com.fasterxml.jackson.databind.node.ObjectNode;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import config.Message;
import model.Event;
import model.Member;
import requestModel.EventRequest;
import service.MessageService;
import service.ScoreBoardService;

@Controller
public class ScoreBoardController {
    
	

    ScoreBoardService scoreBoardService;
    private final SimpMessagingTemplate messagingTemplate;
    @Autowired
    public ScoreBoardController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        scoreBoardService = new ScoreBoardService();
    }
    @MessageMapping("/scoreBoard/GetTeamInvestScores")
    public void getTeamInvestScores(@Payload String payload) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper(); 
        Event event = objectMapper.readValue(payload, Event.class);
        System.out.println("Arrive to GetTeamInvestScores");
        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2fQ.XknT5Dw8aY7bAAUE1qBoHZQKXFUK06AMf8M_XuuVPoE";
        JsonNode jsonNode = scoreBoardService.getTeamInvestScores(event.getEvent_id(), authToken);
        messagingTemplate.convertAndSend("/specific/scoreBoard/teamScores/"+event.getEvent_id(), jsonNode);
    }

    @GetMapping("/skillCategoryScoreBoard")
    public String showCustomPage() {
        return "skillCategoryScoreBoard.html";
    }

    @MessageMapping("/teams/event/totalScore")
    public void getTeamInvestScores(@Payload EventRequest eventRequest) throws Exception {
        System.out.println("Arrive to GetTeamSkillCategoryScores");
        String authToken = eventRequest.getToken();
        JsonNode jsonNodeScoreBoard = scoreBoardService.getTeamSkillCategoryScores(eventRequest);
        String destinationPath="/destination/teams/event/" + eventRequest.getEventId() + "/totalScore";
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode resultNode = mapper.createObjectNode();
        resultNode.set("jsonNodeScoreBoard", jsonNodeScoreBoard);
        messagingTemplate.convertAndSend(destinationPath, resultNode);
    }
}
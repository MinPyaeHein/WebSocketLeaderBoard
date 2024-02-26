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

import config.Message;
import model.Member;
import service.MessageService;
import service.ScoreBoardService;

@Controller
public class ScoreBoardController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    ScoreBoardService scoreBoardService;

    @MessageMapping("/application/GetTeamInvestScores")
    @SendTo("/all/scoreBoard/GetTeamInvestScores")
    public JsonNode GetTeamInvestScores(final Message message) throws Exception {
        scoreBoardService = new ScoreBoardService();
        System.out.println("Arrive to GetTeamInvestScores");

        // String authToken =
        // "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyMX0.hjKR9MOIHW3OgUamQHGaqBRKdMWsn3qkYJvxo7BPEj8";
        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2fQ.XknT5Dw8aY7bAAUE1qBoHZQKXFUK06AMf8M_XuuVPoE";

        JsonNode jsonNode = scoreBoardService.GetTeamInvestScores(7, authToken);

        return jsonNode;
    }

}
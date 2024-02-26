package com.leaderboard.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.TranInvestor;
import model.Member;
import service.InvestToTeamService;
import service.MessageService;
import service.ScoreBoardService;

@Controller
public class InvestToTeamController {
	
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    InvestToTeamService investToTeamService;
    
    @GetMapping("/invest")
    public String showCustomPage() {
       
        return "invest.html"; 
    }

    @MessageMapping("/application/investToTeam")
//    @SendTo("/all/application/investToTeam")
    @SendTo("/all/scoreBoard/GetTeamInvestScores")
    public JsonNode GetTeamInvestScores(@Payload TranInvestor investToTeam) throws Exception {
    	investToTeamService = new InvestToTeamService();
        System.out.println("Arrive to investToTeam");
        ScoreBoardService scoreBoardService;
        String authToken = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjo2fQ.XknT5Dw8aY7bAAUE1qBoHZQKXFUK06AMf8M_XuuVPoE"; 
        
        JsonNode jsonNode = investToTeamService.investToTeamService(investToTeam, authToken);
        
        scoreBoardService=new ScoreBoardService();
        jsonNode = scoreBoardService.GetTeamInvestScores(7, authToken);
        System.out.print("SentUptadeData");
        System.out.print(jsonNode);
        return jsonNode;

    
    }

}
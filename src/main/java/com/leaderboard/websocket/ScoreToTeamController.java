package com.leaderboard.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.TranInvestor;
import model.TranScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import requestModel.EventRequest;
import service.InvestToTeamService;
import service.ScoreBoardService;
import service.ScoreToTeamService;

@Controller
public class ScoreToTeamController {

	@Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    ScoreToTeamService scoreToTeamService;

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ScoreToTeamController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        scoreToTeamService = new ScoreToTeamService();
    }
    
    @GetMapping("/score")
    public String showCustomPage() {
       
        return "score.html";
    }
    @MessageMapping("/score")
    public void getTeamInvestScores(@Payload TranScore tranScore) throws Exception {
        ScoreBoardService scoreBoardService = new ScoreBoardService();
        JsonNode scoreStatus= scoreToTeamService.scoreToTeamService(tranScore);
        EventRequest eventRequest= new EventRequest(tranScore.getEvent_id(), tranScore.getToken());
        JsonNode jsonNodeScoreBoard = scoreBoardService.getTeamSkillCategoryScores(eventRequest);
        JsonNode teamScoreList=scoreToTeamService.getAllTeamScoreCategoryByAllJudge(tranScore);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode resultNode = mapper.createObjectNode();
        resultNode.set("scoreStatus", scoreStatus);
        resultNode.set("jsonNodeScoreBoard", jsonNodeScoreBoard);
        messagingTemplate.convertAndSend("/destination/teams/event/" + eventRequest.getEventId() + "/totalScore", resultNode);
        messagingTemplate.convertAndSend("/destination/teams/event/" + eventRequest.getEventId() + "/categoriesScore/judge", teamScoreList);
    }
    

}
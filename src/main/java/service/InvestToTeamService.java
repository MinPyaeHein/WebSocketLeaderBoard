package service;

import java.util.ArrayList;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.TranInvestor;
import model.TranInvestorRequest;
import model.Member;
import model.TeamInvestScore;

@Service
public class InvestToTeamService {

    public JsonNode investToTeamService(TranInvestor investToTeam, String authToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authToken);
        headers.set("Content-Type", "application/json");
        TranInvestorRequest investorRequest=new TranInvestorRequest();
        ObjectMapper mapper = new ObjectMapper();
        investorRequest.setTran_investor(investToTeam);
        String memberRequestBody = mapper.writeValueAsString(investorRequest);

        System.out.println(memberRequestBody);

        HttpEntity<String> requestEntity = new HttpEntity<>(memberRequestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v1/tran_investors";
        
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        JsonNode message = null;

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println(responseBody);

            JsonNode root = mapper.readTree(responseBody);
            message = root.get("message");
            System.out.println(message);
        } else {
            System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
        }

        return message;
    }
}

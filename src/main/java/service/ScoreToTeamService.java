package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Judge;
import model.TranInvestor;
import model.TranScore;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import requestModel.TranInvestorRequest;
import requestModel.TranScoreRequest;

@Service
public class ScoreToTeamService {

    public JsonNode scoreToTeamService(TranScore tranScore) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(tranScore.toString());
        headers.set("Authorization",tranScore.getToken());
        headers.set("Content-Type", "application/json");
        TranScoreRequest tranScoreRequest=new TranScoreRequest();
        ObjectMapper mapper = new ObjectMapper();
        tranScoreRequest.setTran_score(tranScore);
        String memberRequestBody = mapper.writeValueAsString(tranScoreRequest);
        HttpEntity<String> requestEntity = new HttpEntity<>(memberRequestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v2/score";
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        JsonNode root = null;
        
        try {
            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                root = mapper.readTree(responseBody);
            } else {
                System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
                return root;
            }
        } catch (HttpClientErrorException.UnprocessableEntity ex) {
          
            String responseBody = ex.getResponseBodyAsString();
       
            System.out.println("Caught UnprocessableEntity exception: " + responseBody);
        
            String responseBodyError = ex.getResponseBodyAsString();
       
            ObjectNode rootNode = mapper.createObjectNode();
            rootNode.put("success", false);
            ArrayNode errorsNode = mapper.createArrayNode();
            errorsNode.add(responseBodyError);
            rootNode.set("errors", errorsNode);
            // Assign the rootNode to root
            root = rootNode;
            // Additional error handling code can be added here
          
        } catch (Exception e) {
          
            System.out.println("An unexpected error occurred: " + e.getMessage());
           
        }

        return root;
    }

    public JsonNode getAllTeamScoreCategoryByAllJudge(TranScore tranScore) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(tranScore.toString());
        headers.set("Authorization",tranScore.getToken());
        headers.set("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v2/teams/event/"+tranScore.getEvent_id()+"/categoriesScore";
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);
        JsonNode root = null;

        try {
            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                root = mapper.readTree(responseBody);
            } else {
                System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
                return root;
            }
        } catch (HttpClientErrorException.UnprocessableEntity ex) {

            String responseBody = ex.getResponseBodyAsString();

            System.out.println("Caught UnprocessableEntity exception: " + responseBody);

            String responseBodyError = ex.getResponseBodyAsString();

            ObjectNode rootNode = mapper.createObjectNode();
            rootNode.put("success", false);
            ArrayNode errorsNode = mapper.createArrayNode();
            errorsNode.add(responseBodyError);
            rootNode.set("errors", errorsNode);
            // Assign the rootNode to root
            root = rootNode;
            // Additional error handling code can be added here

        } catch (Exception e) {

            System.out.println("An unexpected error occurred: " + e.getMessage());

        }

        return root;
    }



}

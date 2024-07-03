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

import model.Member;
import model.TeamInvestScore;
import requestModel.EventRequest;

@Service
public class ScoreBoardService {
	public JsonNode getTeamInvestScores(int event_id, String authToken) throws JsonMappingException, JsonProcessingException {
//		ArrayList<TeamInvestScore> teamInvestScores=new ArrayList<>();
		JsonNode root=null;
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", authToken);
	    headers.set("Content-Type", "application/json");
	    String memberRequestBody = "{\"event_id\":"+event_id+"}";
	    HttpEntity<String> requestEntity = new HttpEntity<>(memberRequestBody, headers);
	    RestTemplate restTemplate = new RestTemplate();
	    String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v1/tran_investors/invest_amounts_by_team"; 
	    ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
	    
	    if (response.getStatusCode().is2xxSuccessful()) {
	        String responseBody = response.getBody();
	        System.out.println(responseBody);
	        ObjectMapper mapper = new ObjectMapper();
	         root = mapper.readTree(responseBody);
	    } else {
	        System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
	    }
		return root;
		
	}

	public JsonNode getTeamSkillCategoryScores(EventRequest eventRequest) throws JsonMappingException, JsonProcessingException {
		JsonNode root=null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", eventRequest.getToken());
		headers.set("Content-Type", "application/json");
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v2/teams/event/"+eventRequest.getEventId()+"/categoriesScore";
		ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, requestEntity, String.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			String responseBody = response.getBody();
			ObjectMapper mapper = new ObjectMapper();
			root = mapper.readTree(responseBody);
		} else {
			System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
		}
		return root;

	}
	
}

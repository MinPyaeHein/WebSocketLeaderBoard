package service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import model.TranInvestor;
import requestModel.TranInvestorRequest;
import model.Judge;

@Service
public class InvestToTeamService {

    public JsonNode investToTeamService(TranInvestor investToTeam, String authToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        System.out.println(investToTeam.toString());
//        headers.set("Authorization","eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoyMX0.hjKR9MOIHW3OgUamQHGaqBRKdMWsn3qkYJvxo7BPEj8");
        headers.set("Authorization",authToken);
        headers.set("Content-Type", "application/json");
        TranInvestorRequest investorRequest=new TranInvestorRequest();
        ObjectMapper mapper = new ObjectMapper();
        investorRequest.setTran_investor(investToTeam);
        String memberRequestBody = mapper.writeValueAsString(investorRequest);
        HttpEntity<String> requestEntity = new HttpEntity<>(memberRequestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v1/tran_investors"; 
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
    public JsonNode GetJudgeById(TranInvestor tranInvestor,String authToken) throws JsonMappingException, JsonProcessingException {
//		ArrayList<TeamInvestScore> teamInvestScores=new ArrayList<>();
		
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Authorization", authToken);
	    headers.set("Content-Type", "application/json");
	    
	    Judge judge=new Judge();
	    judge.setEvent_id(tranInvestor.getEvent_id());
	    judge.setJudge_id(tranInvestor.getJudge_id());
	    judge.setTeam_id(tranInvestor.getTeam_id());
	    
	    ObjectMapper mapper = new ObjectMapper();
        String memberRequestBody = mapper.writeValueAsString(judge);

	    
	    HttpEntity<String> requestEntity = new HttpEntity<>(memberRequestBody, headers);
	    RestTemplate restTemplate = new RestTemplate();
	    JsonNode root=null;
	    String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v1/judges/get_judge_by_id"; 
	    ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);   
	    if (response.getStatusCode().is2xxSuccessful()) {
	        String responseBody = response.getBody();
	         root = mapper.readTree(responseBody);
		    } else {
	        System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
	    }
		return root;
		
	}
}

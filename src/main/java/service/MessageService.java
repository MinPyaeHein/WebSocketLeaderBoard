package service;

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

@Service
public class MessageService {
public Member getMemberById(int member_id, String authToken) throws JsonMappingException, JsonProcessingException {
	Member member=null;
	HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", authToken);
    headers.set("Content-Type", "application/json");
    String memberRequestBody = "{\"member_id\": 4}"; 
    HttpEntity<String> requestEntity = new HttpEntity<>(memberRequestBody, headers);
    RestTemplate restTemplate = new RestTemplate();
    String apiUrl = "https://stormy-hamlet-97616-f066246815d5.herokuapp.com/api/v1/members/get_member_by_id"; 
    ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
    
    if (response.getStatusCode().is2xxSuccessful()) {
        String responseBody = response.getBody();


        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);
        JsonNode memberNode = root.get("message").get("member"); // Accessing the member object within the message

        member = new Member();
        member.setMember_id(memberNode.get("id").asInt());
        member.setName(memberNode.get("name").asText());
        member.setPhone(memberNode.get("phone").asText());
        member.setActive(memberNode.get("active").asBoolean());
        member.setProfile_url(memberNode.get("profile_url").asText());
        member.setAddress(memberNode.get("address").asText());
        member.setOrg_name(memberNode.get("org_name").asText());
    } else {
        System.out.println("Failed to fetch data from the API: " + response.getStatusCode() + " " + response.getBody());
    }
	return member;
}


}

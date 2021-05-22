package POJO;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONArraySerialization {
	
	public String completeJson;
	
	@Test
	public void createJSONArrayFromPOJO() throws JsonProcessingException {
		
		JSONArrayPOJO anshul = new JSONArrayPOJO();
		anshul.setFirstName("Anshul");
		anshul.setLastName("Chauhan");
		anshul.setAge(29);
		anshul.setGender("Male");
		anshul.setSalary(123456);
		anshul.setMarried(false);
		
		
		JSONArrayPOJO mark = new JSONArrayPOJO();
		mark.setFirstName("Mark");
		mark.setLastName("Wood");
		mark.setAge(30);
		mark.setGender("Male");
		mark.setSalary(200000.3);
		mark.setMarried(true);
		
		JSONArrayPOJO kitty = new JSONArrayPOJO();
		kitty.setFirstName("Kitty");
		kitty.setLastName("Martin");
		kitty.setAge(26);
		kitty.setGender("Female");
		kitty.setSalary(300000.3);
		kitty.setMarried(false);
		
		List<JSONArrayPOJO> allemployees = new ArrayList<JSONArrayPOJO>();
		allemployees.add(anshul);
		allemployees.add(mark);
		allemployees.add(kitty);
		
		ObjectMapper objectMapper = new ObjectMapper();
		completeJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(allemployees);
		System.out.println(completeJson);
		
	}
	
	@Test
	public void getPOJOfromOject() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<JSONArrayPOJO> allemployees = objectMapper.readValue(completeJson, new TypeReference<List<JSONArrayPOJO>>() { });
		
		for (JSONArrayPOJO empployee : allemployees) {
			System.out.println("========================================================");
			System.out.println("First Name of employee : " + empployee.getFirstName());
			System.out.println("Last Name of employee : " + empployee.getLastName());
			System.out.println("Age of employee : " + empployee.getAge());
			System.out.println("Gender of employee : " + empployee.getGender());
			System.out.println("Salary of employee : " + empployee.getSalary());
			System.out.println("Marital status of employee : " + empployee.isMarried());
			System.out.println("========================================================");
		
		}
		
	}

}

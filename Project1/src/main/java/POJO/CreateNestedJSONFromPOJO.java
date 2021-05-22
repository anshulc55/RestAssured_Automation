package POJO;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateNestedJSONFromPOJO {
	
	@Test
	public void generatePOJOData() throws JsonProcessingException {
		
		CompanyDetails nestedPOJO = new CompanyDetails();
		nestedPOJO.setCompanyName("LevelUp360");
		nestedPOJO.setCompanyHOCity("Benagluru");
		nestedPOJO.setCompanyCEO("Anshul");
		
		List<String> supportedSalaryBanks = new ArrayList<>();
		supportedSalaryBanks.add("HDFC");
		supportedSalaryBanks.add("ICICI");
		supportedSalaryBanks.add("AXIS");
		nestedPOJO.setSupportedSalaryBanks(supportedSalaryBanks);
		
		List<Integer> pincodesOfCityOffice = new ArrayList<>();
		pincodesOfCityOffice.add(560037);
		pincodesOfCityOffice.add(360034);
		pincodesOfCityOffice.add(456343);
		nestedPOJO.setPincodesOfCityOffice(pincodesOfCityOffice);
		
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
		
		List<JSONArrayPOJO> employee = new ArrayList<>();
		employee.add(anshul);
		employee.add(mark);
		employee.add(kitty);
		nestedPOJO.setEmployee(employee);
		
		Contrators seema = new Contrators();
		seema.setFirstName("Seema");
		seema.setLastName("Saher");
		seema.setContractFrom("Jan-2020");
		seema.setContractTo("JAN-2025");
		
		Contrators peter = new Contrators();
		peter.setFirstName("Peter");
		peter.setLastName("Mars");
		peter.setContractFrom("Jan-2018");
		peter.setContractTo("JAN-2030");
		
		List<Contrators> contractors = new ArrayList<>();
		contractors.add(seema);
		contractors.add(seema);
		nestedPOJO.setContractors(contractors);
		
		CompanyPFDeails companyPFDeails = new CompanyPFDeails();
		companyPFDeails.setPfName("XYZ");
		companyPFDeails.setPfYear(2015);
		companyPFDeails.setPfCity("Benagluru");
		companyPFDeails.setNoOfEmployees(10);
		nestedPOJO.setCompanyPFDeails(companyPFDeails);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String nestedPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nestedPOJO);
		System.out.println(nestedPayload);
	}

}

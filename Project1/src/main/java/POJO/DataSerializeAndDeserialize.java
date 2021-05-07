package POJO;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataSerializeAndDeserialize {

	@Test
	public void createDatafromPOJO() throws JsonProcessingException {

		// Just create an object of Pojo class
		SamplePOJO employee = new SamplePOJO();

		employee.setFirstName("Anshul");
		employee.setLastName("Chauhan");
		employee.setAge(29);
		employee.setGender("Male");
		employee.setSalary(123456);
		employee.setMarried(false);

		// Converting a Java class object to a JSON payload as string
		ObjectMapper objectmapper = new ObjectMapper();
		String employeeJSON = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);

		System.out.println(employeeJSON);

	}

	@Test
	public void getPojoFromObject() throws JsonProcessingException {

		// Just create an object of Pojo class
		SamplePOJO employee = new SamplePOJO();

		employee.setFirstName("Mark");
		employee.setLastName("Wood");
		employee.setAge(27);
		employee.setGender("Male");
		employee.setSalary(45893927);
		employee.setMarried(false);

		// Converting a Java class object to a JSON payload as string
		ObjectMapper objectmapper = new ObjectMapper();
		String employeeJSON = objectmapper.writerWithDefaultPrettyPrinter().writeValueAsString(employee);
		
		// Converting json string to  class object
		SamplePOJO employeeObj = objectmapper.readValue(employeeJSON, SamplePOJO.class);
		System.out.println("FirstName - " + employeeObj.getFirstName());
		System.out.println("Last Name of employee : "+employeeObj.getLastName());
		System.out.println("Age of employee : "+employeeObj.getAge());
		System.out.println("Gender of employee : "+employeeObj.getGender());
		System.out.println("Salary of employee : "+employeeObj.getSalary());
		System.out.println("Marital status of employee : "+employeeObj.getMarried());

	}
}

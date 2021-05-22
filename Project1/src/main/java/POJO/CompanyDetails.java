package POJO;

import java.util.List;

public class CompanyDetails {
	
	private String companyName;
	private String companyHOCity;
	private String companyCEO;
	private List<String> supportedSalaryBanks;
	private List<Integer> pincodesOfCityOffice;
	List<JSONArrayPOJO> employee;
	List<Contrators> contractors;
	CompanyPFDeails companyPFDeails;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyHOCity() {
		return companyHOCity;
	}
	public void setCompanyHOCity(String companyHOCity) {
		this.companyHOCity = companyHOCity;
	}
	public String getCompanyCEO() {
		return companyCEO;
	}
	public void setCompanyCEO(String companyCEO) {
		this.companyCEO = companyCEO;
	}
	public List<String> getSupportedSalaryBanks() {
		return supportedSalaryBanks;
	}
	public void setSupportedSalaryBanks(List<String> supportedSalaryBanks) {
		this.supportedSalaryBanks = supportedSalaryBanks;
	}
	public List<Integer> getPincodesOfCityOffice() {
		return pincodesOfCityOffice;
	}
	public void setPincodesOfCityOffice(List<Integer> pincodesOfCityOffice) {
		this.pincodesOfCityOffice = pincodesOfCityOffice;
	}
	public List<JSONArrayPOJO> getEmployee() {
		return employee;
	}
	public void setEmployee(List<JSONArrayPOJO> employee) {
		this.employee = employee;
	}
	public List<Contrators> getContractors() {
		return contractors;
	}
	public void setContractors(List<Contrators> contractors) {
		this.contractors = contractors;
	}
	public CompanyPFDeails getCompanyPFDeails() {
		return companyPFDeails;
	}
	public void setCompanyPFDeails(CompanyPFDeails companyPFDeails) {
		this.companyPFDeails = companyPFDeails;
	}
	
	

}

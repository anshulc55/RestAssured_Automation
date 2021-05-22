package utility;

public class createURL {

	public final static String baseURI = "https://api.github.com";
	
	public static String getBaseURI() {
		return baseURI;
	}
	
	public static String getBaseURI(String resource) {
		return baseURI + resource;
	}
}

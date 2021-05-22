package resource;

import PojoPayloads.CreateRepoPOJO;

public class TestDataBuilder {
	CreateRepoPOJO req_payload = new CreateRepoPOJO();
	
	public CreateRepoPOJO createRepoPayload(String name, String desccription) {
		req_payload.setName(name);
	    req_payload.setDescription(desccription);
	    return req_payload;
	}

	public String getRepoName() {
		return req_payload.getName();
	}
}

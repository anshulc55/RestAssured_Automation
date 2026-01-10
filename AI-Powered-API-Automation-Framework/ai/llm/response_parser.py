import json

def parse_llm_response(response_text: str) -> dict:
	return {
		"generatedTestCode" : response_text.strip()
	}
	

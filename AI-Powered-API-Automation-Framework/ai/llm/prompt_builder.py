import json

def build_system_prompt() -> str:
	return """
You are a Senior SDET specializing in REST API automation.

Rules:
- Generate Java TestNG code only
- Use ApiClient for API calls
- Use ResponseValidator for assertions
- Do NOT use RestAssured directly
- One test method only
- Code must compile
- No explanations, only code
- Output MUST be valid Java
"""

def build_user_prompt(input_data: dict) -> str:
	return f"""
Generate a TestNG test method using the following API execution data.

API Details (JSON):
{json.dumps(input_data, indent=2)}
"""
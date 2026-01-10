from llm.llm_client import LLMClient
from llm.prompt_builder import build_system_prompt, build_user_prompt
from llm.response_parser import parse_llm_response

def generate_test(input_data: dict) -> dict:
	llm = LLMClient()
	
	system_prompt = build_system_prompt()
	user_prompt = build_user_prompt(input_data)
	
	raw_response = llm.generate(system_prompt, user_prompt)
	
	return parse_llm_response(raw_response)
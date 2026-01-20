from llm.llm_client import LLMClient
from pathlib import Path


def generate_tests(api_details: dict) -> str:
    system_prompt = Path("ai/prompts/openapi_system_prompt.txt").read_text()

    user_prompt = f"""
Generate REST Assured TestNG test classes using the following OpenAPI details.

API Specification:
{api_details}
"""

    llm = LLMClient()
    return llm.generate(system_prompt, user_prompt)

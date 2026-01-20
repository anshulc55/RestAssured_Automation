import json
import os

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
CONTRACT_PATH = os.path.join(BASE_DIR, "contracts", "generated_framework_api.json")


def load_framework_contract() -> str:
    if not os.path.exists(CONTRACT_PATH):
        raise FileNotFoundError(f"Framework API contract not found at: {CONTRACT_PATH}")

    with open(CONTRACT_PATH, "r", encoding="utf-8") as f:
        content = f.read().strip()

        if not content:
            raise ValueError(
                "generated_framework_api.json is empty. AI cannot proceed without contract."
            )
        # Validate JSON before passing to LLM
        json.loads(content)
        return content


def build_system_prompt() -> str:

    contract = load_framework_contract()
    return f"""
You are a Senior SDET specializing in REST API automation.

RULES:
- Use ONLY the methods listed in the Framework API Contract
- Generate a TEST CLASS, not a single method
- Each scenario MUST be a SEPARATE @Test method
- ONE @Test method = ONE scenario only
- DO NOT invent new method names
- DO NOT use RestAssured directly
- Always choose the closest matching method from the contract
- If headers are not provided, pass null
- Always inherite BaseTest Java class in Generated Test Class
- If query params are not provided, pass null
- Always generate ONE compilable TestNG test method
- Always generate proper Imports
- Output ONLY Java code (no markdown, no explanations)

Framework API Contract (AUTHORITATIVE):
{contract}
"""


def build_user_prompt(input_data: dict) -> str:
    return f"""
Using the rules above, generate a Java TestNG test class for the following API.

Requirements:
- One test method per scenario
- Separate methods for:
  - Positive case
  - Each negative case
  - Each corner case
- Do NOT reuse test methods
- Do NOT combine scenarios

API Details (JSON):
{json.dumps(input_data, indent=2)}
"""

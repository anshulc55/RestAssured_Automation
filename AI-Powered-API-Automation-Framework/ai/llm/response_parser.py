import json

def validate_generated_code(code: str):
    forbidden = [
        "RestAssured",
        "given(",
        "when(",
        "then(",
        "assertThat",
        "sendRequest",
        "executeRequest",
    ]

    for keyword in forbidden:
        if keyword in code:
            raise ValueError(f"Forbidden API detected in AI output: {keyword}")


def parse_llm_response(response_text: str) -> dict:
    validate_generated_code(response_text)

    return {"generatedTestCode": response_text.strip()}

	

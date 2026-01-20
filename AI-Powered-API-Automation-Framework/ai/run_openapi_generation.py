from pathlib import Path
from swagger.openapi_loader import load_openapi_spec
from swagger.spec_parser import extract_api_details
from services.openapi_test_generator import generate_tests

OUTPUT_FILE = Path("ai/runtime/generated_tests.java")


def main():
    swagger_path = "ai/petstore.yaml"

    spec = load_openapi_spec(swagger_path)
    api_details = extract_api_details(spec)

    generated_code = generate_tests(api_details)

    OUTPUT_FILE.parent.mkdir(parents=True, exist_ok=True)
    OUTPUT_FILE.write_text(generated_code)

    print("Swagger-based test generation completed")
    print(f"Output written to {OUTPUT_FILE}")


if __name__ == "__main__":
    main()

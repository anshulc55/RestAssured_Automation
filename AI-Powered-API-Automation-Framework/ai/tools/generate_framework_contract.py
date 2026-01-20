import re
import json
from pathlib import Path

JAVA_FILES = [
	"src/main/java/client/ApiClient.java",
    "src/main/java/utils/ResponseValidator.java",
    "src/test/java/tests/BaseTest.java"
]


OUTPUT_PATH = "ai/contracts/generated_framework_api.json"

METHOD_REGEX = re.compile(
    r'public\s+static\s+([\w<>]+)\s+(\w+)\s*\(([^)]*)\)'
)

def extract_methods(java_file):
    methods = {}

    content = Path(java_file).read_text()

    for return_type, method_name, params in METHOD_REGEX.findall(content):
        signature = f"{return_type} {method_name}({params.strip()})"
        methods[method_name] = {"signature": signature}

    return methods


def generate_contract():
    contract = {}

    for file_path in JAVA_FILES:
        class_name = Path(file_path).stem
        contract[class_name] = extract_methods(file_path)

    contract["Restrictions"] = {
        "forbidden": ["RestAssured", "given(", "when(", "then(", "assertThat", "ResponseSpecification", "RequestSpecification"]
    }

    Path(OUTPUT_PATH).write_text(json.dumps(contract, indent=2))

    print("Farmework Contract generated successfully")


if __name__ == "__main__":
    generate_contract()


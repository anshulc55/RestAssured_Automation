import json
import subprocess
from services.ai_test_generator import generate_test


INPUT_PATH = "ai/io/input.json"
OUTPUT_PATH = "ai/io/output.json"

subprocess.run(["python", "ai/tools/generate_framework_contract.py"])

def main():
	with open(INPUT_PATH, "r") as f:
		input_data = json.load(f)
		
	ai_result = generate_test(input_data)
	
	with open(OUTPUT_PATH, "w") as f:
		json.dump(ai_result, f, indent=2)
		
if __name__ == "__main__":
	main()
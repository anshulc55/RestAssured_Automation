from pathlib import Path
from services.analyze_failures import analyze_failure, write_output

def main():
	# Update paths as needed
    test_file = Path("src/test/java/tests/PostsPostTest.java")
    log_file = Path("logs/api-automation.log")
    testng_file = Path("test-output/testng-results.xml")

    result = analyze_failure(test_file, log_file, testng_file)
    write_output(result)

    print("AI Failure Analysis completed.")
    print("Output written to ai/runtime/ai_analysis_output.txt")

if __name__ == "__main__":
    main()
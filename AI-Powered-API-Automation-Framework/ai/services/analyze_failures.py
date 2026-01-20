from llm.llm_client import LLMClient
from services.file_loader import load_context
from services.analysis_prompt_builder import load_system_prompt, build_user_prompt
from pathlib import Path

OUTPUT_FILE = Path("ai/runtime/ai_analysis_output.txt")

def analyze_failure(test_file, log_file, testng_file) -> str:
    context = load_context(test_file, log_file, testng_file)

    system_prompt = load_system_prompt()
    user_prompt = build_user_prompt(context)

    llm = LLMClient()
    return llm.generate(system_prompt, user_prompt)
    

def write_output(text: str):
    OUTPUT_FILE.parent.mkdir(parents=True, exist_ok=True)
    OUTPUT_FILE.write_text(text)
    

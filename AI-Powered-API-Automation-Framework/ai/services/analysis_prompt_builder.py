from pathlib import Path


def load_system_prompt() -> str:
    return Path("ai/prompts/failure_system_prompt.txt").read_text()


def build_user_prompt(context: str) -> str:
    return f"""
Analyze the following failure evidence.

Provide:
1. Failure summary
2. Root cause analysis
3. Classification (TEST ISSUE / API ISSUE)
4. Suggested fix
5. Suggested updated test code (if applicable)

Evidence:
{context}
"""

from pathlib import Path


def read_file(path: Path) -> str:
    if not path.exists():
        return f"[FILE NOT FOUND: {path}]"
    return path.read_text()


def load_context(test_file, log_file, testng_file) -> str:
    return f"""
===== JAVA TEST FILE =====
{read_file(test_file)}

===== EXECUTION LOGS =====
{read_file(log_file)}

===== TESTNG RESULTS =====
{read_file(testng_file)}
"""

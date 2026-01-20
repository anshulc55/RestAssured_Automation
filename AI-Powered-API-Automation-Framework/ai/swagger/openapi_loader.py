import yaml
import json
import requests
from pathlib import Path


def load_openapi_spec(source: str) -> dict:
    if source.startswith("http"):
        response = requests.get(source)
        response.raise_for_status()
        content = response.text
    else:
        content = Path(source).read_text()

    if source.endswith((".yaml", ".yml")):
        return yaml.safe_load(content)
    return json.loads(content)

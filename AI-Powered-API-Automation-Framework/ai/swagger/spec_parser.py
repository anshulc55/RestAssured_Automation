def extract_api_details(spec: dict) -> dict:
    apis = []

    for path, methods in spec.get("paths", {}).items():
        for method, details in methods.items():
            apis.append(
                {
                    "endpoint": path,
                    "method": method.upper(),
                    "summary": details.get("summary"),
                    "parameters": details.get("parameters", []),
                    "requestBody": details.get("requestBody"),
                    "responses": details.get("responses", {}),
                }
            )

    return {
        "title": spec.get("info", {}).get("title"),
        "version": spec.get("info", {}).get("version"),
        "apis": apis,
    }

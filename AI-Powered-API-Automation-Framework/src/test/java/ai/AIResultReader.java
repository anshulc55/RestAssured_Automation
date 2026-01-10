package ai;

import java.util.Map;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AIResultReader {

	private static final String OUTPUT_PATH = "ai/io/output.json";

	public static Map<String, Object> readResult() {

		try {
			ObjectMapper mapper = new ObjectMapper();

			return mapper.readValue(new File(OUTPUT_PATH), Map.class);
		} catch (Exception e) {
			throw new RuntimeException("Failed to read AI output.json", e);
		}

	}
}

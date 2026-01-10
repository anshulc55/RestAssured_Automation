package ai;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AIProcessRunner {

	public static void runPythonAI() {
		try {

			ProcessBuilder pb = new ProcessBuilder("python3", "ai/runner.py");

			pb.redirectErrorStream(true);
			Process process = pb.start();

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {

				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println("[AI] " + line);
				}
			}

			int exitCode = process.waitFor();
			if (exitCode != 0) {
				throw new RuntimeException("Python AI process failed with exit code: " + exitCode);
			}

		} catch (Exception e) {
			throw new RuntimeException("Error runningh in Python AI", e);
		}
	}

}

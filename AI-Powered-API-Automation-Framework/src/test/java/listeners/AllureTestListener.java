package listeners;

import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class AllureTestListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		String correlationId = ThreadContext.get("correlationId");

		Allure.parameter("Correlation ID", correlationId);

	}

	@Override
	public void onTestFailure(ITestResult result) {

		Allure.parameter("Failure Reason", result.getThrowable().getMessage());

//		if (result.getThrowable() != null) {
//			Allure.addAttachment("Exception", result.getThrowable().toString());
//		}
	}

}

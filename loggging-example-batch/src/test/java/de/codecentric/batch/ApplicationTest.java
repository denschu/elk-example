package de.codecentric.batch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.OutputCapture;

public class ApplicationTest {

	@Rule
	public OutputCapture outputCapture = new OutputCapture();

	@Test
	public void testDefaultSettings() throws Exception {
		assertEquals(0, SpringApplication.exit(SpringApplication
				.run(Application.class,"duration=1")));
		String output = this.outputCapture.toString();
		assertTrue("Wrong output: " + output,
				output.contains("completed with the following parameters"));
	}


}

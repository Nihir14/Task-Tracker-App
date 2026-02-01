package com.nihir.task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.TimeZone; // Import this

@SpringBootTest
class TaskTrackerApplicationTests {

	// Add this Static Block
	static {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Test
	void contextLoads() {
	}

}
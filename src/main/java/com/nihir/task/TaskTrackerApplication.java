package com.nihir.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone; // Import this

@SpringBootApplication
public class TaskTrackerApplication {

	public static void main(String[] args) {
		// 1. FORCE TIMEZONE FIX FIRST
		// We use "UTC" because it is universally accepted by all databases.
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		System.out.println("Timezone explicitly set to UTC to avoid PSQLException");

		// 2. THEN START SPRING
		SpringApplication.run(TaskTrackerApplication.class, args);
	}
}
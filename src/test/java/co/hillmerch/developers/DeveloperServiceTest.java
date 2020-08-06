package co.hillmerch.developers;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DeveloperServiceTest {

	private static final Logger logger = LoggerFactory.getLogger( DeveloperServiceTest.class);

	@Test
	void testingGenerateDeveloperCurrentStatus() {
		Instant start = Instant.now();//Variable usada para contar el tiempo en que se tiene respuesta del método

		DeveloperService developerService = new DeveloperService();
		List<Developer> developerList = developerService.generateDeveloperCurrentStatus();
		logger.info( developerList.toString() );

		logger.info( "Tiempo de respuesta: " + Duration.between( start, Instant.now()).toString());
	}

	@Test
	void testingGenerateDeveloperCurrentStatusAsync() {
		Instant start = Instant.now();//Variable usada para contar el tiempo en que se tiene respuesta del método

		DeveloperService developerService = new DeveloperService();
		List<Developer> developerList = developerService.generateDeveloperCurrentStatusAsync();
		logger.info( developerList.toString() );

		logger.info( "Tiempo de respuesta: " + Duration.between( start, Instant.now()).toString());
	}

}